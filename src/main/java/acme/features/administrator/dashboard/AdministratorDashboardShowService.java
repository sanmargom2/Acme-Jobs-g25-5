
package acme.features.administrator.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.dashboards.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	// Internal Service -----------------------------------------------

	@Autowired
	AdministratorDashboardRepository repository;


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		Dashboard result = new Dashboard();

		result.setRatioOfJobsWithCulp(this.repository.ratioOfJobsWithCulp());
		result.setRatioOfApplicationsWithAnswer(this.repository.ratioOfApplicationsWithAnswer());
		result.setRatioOfApplicationsWithContrasena(this.repository.ratioOfApplicationsWithContrasena());

		return result;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ratioOfJobsWithCulp", "ratioOfApplicationsWithAnswer", "ratioOfApplicationsWithContrasena");
	}
}
