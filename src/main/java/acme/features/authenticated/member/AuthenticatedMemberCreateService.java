
package acme.features.authenticated.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.members.Member;
import acme.entities.messageThreads.MessageThread;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMemberCreateService implements AbstractCreateService<Authenticated, Member> {

	@Autowired
	AuthenticatedMemberRepository repository;


	@Override
	public boolean authorise(final Request<Member> request) {
		assert request != null;

		Member member;

		member = this.repository.findMemberInThread(request.getModel().getInteger("thread.id"), request.getPrincipal().getActiveRoleId());
		return member.isAuthor();
	}

	@Override
	public void bind(final Request<Member> request, final Member entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Authenticated user;
		MessageThread messageThread;

		messageThread = this.repository.findOneMessageThreadById(request.getModel().getInteger("thread.id"));
		user = this.repository.findUserByUsername(request.getModel().getString("authenticated.userAccount.username"));
		entity.setAuthenticated(user);
		entity.setMessageThread(messageThread);

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Member> request, final Member entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "authenticated", "thread", "thread.title", "thread.id");
	}

	@Override
	public Member instantiate(final Request<Member> request) {
		Member member;

		member = new Member();
		MessageThread messageThread;

		messageThread = this.repository.findOneMessageThreadById(request.getModel().getInteger("thread.id"));
		member.setMessageThread(messageThread);

		return member;
	}

	@Override
	public void validate(final Request<Member> request, final Member entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Authenticated authenticated;
		authenticated = this.repository.findUserByUsername(request.getModel().getString("authenticated.userAccount.username"));

		errors.state(request, authenticated != null, "authenticated.userAccount.username", "authenticated.member.error.invalidUsername");

		if (authenticated != null) {
			errors.state(request, this.repository.findMemberInThread(request.getModel().getInteger("thread.id"), authenticated.getId()) == null, "authenticated.userAccount.username", "authenticated.member.error.duplicated");
		}
	}

	@Override
	public void create(final Request<Member> request, final Member entity) {
		assert entity != null;

		this.repository.save(entity);

	}

	public void createFromMessageThread(final int authenticatedId, final MessageThread messageThread) {
		Member member = new Member();
		member.setAuthenticated(this.repository.findUserById(authenticatedId));
		member.setAuthor(true);
		member.setMessageThread(messageThread);

		this.repository.save(member);

	}

}
