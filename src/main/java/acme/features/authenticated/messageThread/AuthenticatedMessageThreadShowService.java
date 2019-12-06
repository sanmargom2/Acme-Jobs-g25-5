
package acme.features.authenticated.messageThread;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThreads.MessageThread;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedMessageThreadShowService implements AbstractShowService<Authenticated, MessageThread> {

	@Autowired
	AuthenticatedMessageThreadRepository repository;


	@Override
	public boolean authorise(final Request<MessageThread> request) {
		assert request != null;

		boolean result = false;
		int messageThreadId;
		MessageThread messageThread;
		Principal principal;

		messageThreadId = request.getModel().getInteger("id");
		messageThread = this.repository.findOneMessageThreadById(messageThreadId);
		principal = request.getPrincipal();

		for (Authenticated a : messageThread.getMembers()) {
			if (a.getUserAccount().getId() == principal.getAccountId()) {
				result = true;
			}
		}

		return result;
	}

	@Override
	public void unbind(final Request<MessageThread> request, final MessageThread entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		Collection<String> membersCollection = this.repository.findMembers(request.getModel().getInteger("id"));
		String members = "";
		for (String s : membersCollection) {
			members += s + ", ";
		}
		request.unbind(entity, model, "title", "moment");
		if (request.isMethod(HttpMethod.GET)) {

			model.setAttribute("members", members);
		} else {
			request.transfer(model, "members");
		}

	}

	@Override
	public MessageThread findOne(final Request<MessageThread> request) {

		assert request != null;

		MessageThread result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneMessageThreadById(id);

		return result;
	}

}
