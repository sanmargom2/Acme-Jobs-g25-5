package acme.features.authenticated.worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Worker;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedWorkerUpdateService implements AbstractUpdateService<Authenticated, Worker> {

	@Autowired
	private AuthenticatedWorkerRepository repository;

	@Override
	public boolean authorise(Request<Worker> request) {
		// TODO Auto-generated method stub
		assert request != null;

		return true;
	}

	@Override
	public void bind(Request<Worker> request, Worker entity, Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(Request<Worker> request, Worker entity, Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "qualifications", "skills");

	}

	@Override
	public Worker findOne(Request<Worker> request) {
		// TODO Auto-generated method stub
		assert request != null;

		Worker result;
		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		result = this.repository.findOneWorkerByUserAccountId(userAccountId);

		return result;
	}

	@Override
	public void validate(Request<Worker> request, Worker entity, Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(Request<Worker> request, Worker entity) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		this.repository.save(entity);

	}

	@Override
	public void onSuccess(final Request<Worker> request, final Response<Worker> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}

	}
}
