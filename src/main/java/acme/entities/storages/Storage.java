
package acme.entities.storages;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.Authenticated;
import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Storage extends UserRole {

	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	private String				firm;

	@NotBlank
	private String				responsibilityStatement;

	@NotNull
	private StorageStatus		status;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Authenticated		authenticated;
}
