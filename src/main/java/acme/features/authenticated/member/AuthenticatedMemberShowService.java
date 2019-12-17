
package acme.features.authenticated.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.members.Member;
import acme.entities.messageThreads.MessageThread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedMemberShowService implements AbstractShowService<Authenticated, Member> {

	@Autowired
	AuthenticatedMemberRepository repository;


	@Override
	public boolean authorise(final Request<Member> request) {
		assert request != null;

		Member member;
		MessageThread messageThread;

		messageThread = this.repository.findMemberById(request.getModel().getInteger("id")).getMessageThread();
		member = this.repository.findMemberInThread(messageThread.getId(), request.getPrincipal().getActiveRoleId());

		return member.isAuthor();
	}

	@Override
	public void unbind(final Request<Member> request, final Member entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "authenticated.userAccount.username", "messageThread.title");
	}

	@Override
	public Member findOne(final Request<Member> request) {
		assert request != null;

		Member result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findMemberById(id);

		return result;
	}

}
