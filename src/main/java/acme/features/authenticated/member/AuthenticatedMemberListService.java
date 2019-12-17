
package acme.features.authenticated.member;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.members.Member;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedMemberListService implements AbstractListService<Authenticated, Member> {

	@Autowired
	AuthenticatedMemberRepository repository;


	@Override
	public boolean authorise(final Request<Member> request) {
		assert request != null;

		Member member;
		member = this.repository.findMemberInThread(request.getModel().getInteger("id"), request.getPrincipal().getActiveRoleId());

		return member.isAuthor();

	}

	@Override
	public void unbind(final Request<Member> request, final Member entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "authenticated", "authenticated.userAccount.username", "messageThread", "messageThread.title", "messageThread.id");
	}

	@Override
	public Collection<Member> findMany(final Request<Member> request) {
		assert request != null;

		Collection<Member> members;

		members = this.repository.findManyByThreadId(request.getModel().getInteger("id"));

		return members;

	}

}
