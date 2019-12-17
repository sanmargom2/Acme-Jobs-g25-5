package acme.features.employer.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.auditRecords.AuditRecord;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.features.auditor.auditRecord.AuditorAuditRecordRepository;
import acme.features.employer.application.EmployerApplicationRepository;
import acme.features.employer.duty.EmployerDutyRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class EmployerJobDeleteService implements AbstractDeleteService<Employer, Job> {

	@Autowired
	EmployerJobRepository employerJobRepository;

	@Autowired
	AuditorAuditRecordRepository auditorRepository;

	@Autowired
	EmployerDutyRepository employerDutyRepository;

	@Autowired
	EmployerApplicationRepository employerAppRepository;

	@Override
	public boolean authorise(Request<Job> request) {
		// TODO Auto-generated method stub
		assert request != null;
		boolean result;
		int jobId;
		Job job;
		Employer employer;
		Principal principal;

		jobId = request.getModel().getInteger("id");
		job = this.employerJobRepository.findOneById(jobId);
		employer = job.getEmployer();
		principal = request.getPrincipal();
		result = employer.getUserAccount().getId() == principal.getAccountId();

		return result;
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

		request.unbind(entity, model, "referenceNumber", "title", "finalMode");
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
		int jobId = entity.getId();

		Collection<AuditRecord> auditRecords = this.auditorRepository.findAuditRecordsByJob(jobId);
		Collection<Duty> duties = this.employerDutyRepository.findManyDuty(jobId);
		Collection<Application> apps = this.employerAppRepository.findAppsByJob(jobId);

		this.employerAppRepository.deleteAll(apps);
		this.auditorRepository.deleteAll(auditRecords);
		this.employerDutyRepository.deleteAll(duties);
		this.employerJobRepository.delete(entity);

	}

}
