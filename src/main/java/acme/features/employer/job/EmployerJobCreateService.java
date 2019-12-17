package acme.features.employer.job;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.features.administrator.customisation.AdministratorCustomisationRepository;
import acme.features.employer.duty.EmployerDutyRepository;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerJobCreateService implements AbstractCreateService<Employer, Job> {

	@Autowired
	EmployerJobRepository employerJobRepository;

	@Autowired
	EmployerDutyRepository employerDutyRepository;

	@Autowired
	AdministratorCustomisationRepository customisationRepository;

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

		request.bind(entity, errors);
	}

	@Override
	public void unbind(Request<Job> request, Job entity, Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "referenceNumber", "title", "deadline", "salary", "description", "finalMode",
				"moreInfo");

	}

	@Override
	public Job instantiate(Request<Job> request) {
		// TODO Auto-generated method stub
		Job result;

		result = new Job();

		return result;

	}

	@Override
	public void validate(Request<Job> request, Job entity, Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;
		entity.setFinalMode(false);
		Boolean isValid = true;

		if (isValid == false) {
			errors.state(request, isValid, "finalMode", "employer.job.form.error.finalMode");
		}
	}

	@Override
	public void create(Request<Job> request, Job entity) {
		// TODO Auto-generated method stub
		this.employerJobRepository.save(entity);
	}

}
