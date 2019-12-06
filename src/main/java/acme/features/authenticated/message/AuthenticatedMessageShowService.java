
package acme.features.authenticated.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messages.Message;
import acme.features.authenticated.messageThread.AuthenticatedMessageThreadRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedMessageShowService implements AbstractShowService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageThreadRepository	repository;

	@Autowired
	AuthenticatedMessageRepository			repo;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		boolean result = false;
		int messageId;
		Message message;
		Principal principal;

		messageId = request.getModel().getInteger("id");

		message = this.repo.findOneMessageById(messageId);
		principal = request.getPrincipal();
		for (Authenticated a : message.getMessageThread().getMembers()) {
			if (a.getUserAccount().getId() == principal.getAccountId()) {
				result = true;
			}
		}

		return result;
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "tags", "body");
	}

	@Override
	public Message findOne(final Request<Message> request) {
		assert request != null;

		Message result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repo.findOneMessageById(id);

		return result;
	}

}
