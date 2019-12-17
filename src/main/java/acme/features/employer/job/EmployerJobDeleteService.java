package acme.features.employer.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;


@Service
public class EmployerJobDeleteService implements AbstractDeleteService<Employer, Job> {

	@Autowired
	EmployerJobRepository employerJobRepository;

	@Override
	public boolean authorise(Request<Job> request) {
		// TODO Auto-generated method stub
		assert request != null;

		return true;
	}

	@Override
	public void bind(Request<Job> request, Job entity, Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "referenceNumber", "title", "deadline");
	}

	@Override
	public void unbind(Request<Job> request, Job entity, Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "referenceNumber", "title", "deadline", "finalMode", "salary", "description",
				"moreInfo");
	}

	@Override
	public Job findOne(Request<Job> request) {
		// TODO Auto-generated method stub
		assert request != null;

		Job result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.employerJobRepository.findOneById(id);

		return result;
	}

	@Override
	public void validate(Request<Job> request, Job entity, Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(Request<Job> request, Job entity) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;

		this.employerJobRepository.delete(entity);

	}

}
