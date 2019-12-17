
package acme.features.worker.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.applications.TypeStatus;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.features.authenticated.job.AuthenticatedJobRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class WorkerApplicationCreateService implements AbstractCreateService<Worker, Application> {

	@Autowired
	WorkerApplicationRepository	repository;

	@Autowired
	AuthenticatedJobRepository	jobRepository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "worker.authorityName", "job.title", "moment", "status");
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "statement", "skills", "qualifications");
	}

	@Override
	public Application instantiate(final Request<Application> request) {
		Application result;

		result = new Application();
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);

		int jobId = request.getModel().getInteger("jobId");
		Job j = this.jobRepository.findOneById(jobId);

		Principal principal = request.getPrincipal();
		Worker w = this.repository.findByUserAccountId(principal.getAccountId());

		result.setMoment(moment);
		result.setJob(j);
		result.setWorker(w);
		result.setStatus(TypeStatus.PENDING);

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//		int jobId;
		//		Job j;
		//
		//		jobId = request.getModel().getInteger("id");
		//		j = this.jobRepository.findOneById(jobId);
		//
		//		Boolean isValid;
		//		if (!errors.hasErrors("finalMode")) {
		//			isValid = j.getFinalMode() == true;
		//			errors.state(request, isValid, "finalMode", "worker.application.form.error.finalMode");
		//		}
		//
		//		Boolean isValid2;
		//		if (!errors.hasErrors("deadline")) {
		//			Date fecha = new Date(System.currentTimeMillis() - 1);
		//			isValid2 = j.getDeadline().after(fecha);
		//			errors.state(request, isValid2, "deadline", "worker.application.form.error.deadline");
		//		}

	}

	@Override
	public void create(final Request<Application> request, final Application entity) {

		this.repository.save(entity);
	}

}
