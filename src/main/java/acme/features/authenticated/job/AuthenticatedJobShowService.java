
package acme.features.authenticated.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedJobShowService implements AbstractShowService<Authenticated, Job> {

	@Autowired
	AuthenticatedJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;
		boolean result;
		Job job;
		int jobId;
		Employer employer;
		Principal principal;
		
		jobId=request.getModel().getInteger("id");
		job=this.repository.findOneById(jobId);
		
		employer=job.getEmployer();
		principal=request.getPrincipal();
		result=job.isFinalMode()||!job.isFinalMode()&&employer.getUserAccount().getId()==principal.getAccountId();
    
		return result;
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "referenceNumber", "title", "deadline");
		request.unbind(entity, model, "finalMode", "salary", "moreInfo");

	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;

		Job result;
		int jobId;

		jobId = request.getModel().getInteger("id");
		result = this.repository.findOneById(jobId);

		return result;
	}

}
