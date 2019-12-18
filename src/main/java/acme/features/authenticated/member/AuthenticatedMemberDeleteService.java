
package acme.features.authenticated.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.members.Member;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedMemberDeleteService implements AbstractDeleteService<Authenticated, Member> {

	@Autowired
	AuthenticatedMemberRepository repository;


	@Override
	public boolean authorise(final Request<Member> request) {
		assert request != null;

		Member member;
		member = this.repository.findMemberInThread(this.repository.findMemberById(request.getModel().getInteger("id")).getMessageThread().getId(), request.getPrincipal().getActiveRoleId());

		return member.isAuthor();
	}

	@Override
	public void bind(final Request<Member> request, final Member entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Member> request, final Member entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "authenticated", "thread");

	}

	@Override
	public Member findOne(final Request<Member> request) {
		assert request != null;

		Member member;
		int id;

		id = request.getModel().getInteger("id");
		member = this.repository.findMemberById(id);
		return member;
	}

	@Override
	public void validate(final Request<Member> request, final Member entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<Member> request, final Member entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);

	}

}
