package acme.entities.duties;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Range;

import acme.entities.jobs.Job;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Duty extends DomainEntity {

	/**/
	private static final long serialVersionUID = 1L;

	@NotBlank
	private String title;

	@NotBlank
	private String description;

	@NotNull
	@Range(min = 0, max = 100)
	private Integer percentage;

	@NotNull
	@Valid
	@ManyToOne(optional=false)
	private Job job;
}
