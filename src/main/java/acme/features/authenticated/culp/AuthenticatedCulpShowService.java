
package acme.features.authenticated.culp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.culps.Culp;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedCulpShowService implements AbstractShowService<Authenticated, Culp> {

	@Autowired
	AuthenticatedCulpRepository repository;


	@Override
	public boolean authorise(final Request<Culp> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Culp> request, final Culp entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "text", "moreInfo");
	}

	@Override
	public Culp findOne(final Request<Culp> request) {
		assert request != null;
		Culp res = this.repository.findOneCulpById(request.getModel().getInteger("id"));
		return res;
	}

}
