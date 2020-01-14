
package acme.entities.dashboards;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Dashboard extends DomainEntity {

	//Serialisation indetifier ------------------------

	private static final long	serialVersionUID	= 1L;

	//Atributes ---------------------------------------

	@NotNull
	private Double				ratioOfJobsWithCulp;

	@NotNull
	private Double				ratioOfApplicationsWithAnswer;

	@NotNull
	private Double				ratioOfApplicationsWithContrasena;

}
