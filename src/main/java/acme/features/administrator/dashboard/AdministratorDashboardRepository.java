
package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select 1.0 * count(j.culp) / (select count (i) from Job i) from Job j where j.culp != null")
	Double ratioOfJobsWithCulp();

	@Query("select 1.0 * count(a) / (select count (b) from Application b) from Application a where a.answer != null")
	Double ratioOfApplicationsWithAnswer();

	@Query("select 1.0 * count(a) / (select count (b) from Application b) from Application a where a.contrasena != null")
	Double ratioOfApplicationsWithContrasena();
}
