
package acme.features.auditor.auditRecord;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.auditRecords.Status;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.features.auditor.job.AuditorJobRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuditorAuditRecordCreateService implements AbstractCreateService<Auditor, AuditRecord> {

	@Autowired
	AuditorAuditRecordRepository	repository;

	@Autowired
	AuditorJobRepository			jobRepository;


	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "moment", "status", "title", "body");
		model.setAttribute("jobId", entity.getJob().getId());
	}

	@Override
	public AuditRecord instantiate(final Request<AuditRecord> request) {
		AuditRecord result;

		result = new AuditRecord();

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);

		int jobId = request.getModel().getInteger("jobId");
		Job job = this.jobRepository.findOneJobById(jobId);

		Principal principal = request.getPrincipal();
		Auditor auditor = this.repository.findAuditorById(principal.getAccountId());

		result.setJob(job);
		result.setAuditor(auditor);
		result.setMoment(moment);
		result.setStatus(Status.DRAFT);

		return result;
	}

	@Override
	public void validate(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<AuditRecord> request, final AuditRecord entity) {
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);

		entity.setMoment(moment);
		this.repository.save(entity);
	}
}
