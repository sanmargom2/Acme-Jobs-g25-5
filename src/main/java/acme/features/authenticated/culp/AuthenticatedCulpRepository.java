
package acme.features.authenticated.culp;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.culps.Culp;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedCulpRepository extends AbstractRepository {

	@Query("select p from Culp p where p.id=?1")
	Culp findOneCulpById(int id);

}
