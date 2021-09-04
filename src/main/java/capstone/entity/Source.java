/**
 * 
 */
package capstone.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * Nguồn gốc
 * @author Tuna
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "Source", //
		uniqueConstraints = { //
				})
public class Source extends NamedEntity<Long> {
	private static final long serialVersionUID = 1L;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "source")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Customer> customers;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "source")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Contact> contacts;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "source")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Opportunity> opportunities;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "source")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Potential> potentials;

	/**
	 * @param id
	 * @param createdAt
	 * @param updatedAt
	 * @param createdBy
	 * @param updatedBy
	 * @param owner
	 * @param shared
	 * @param deleted
	 * @param name
	 * @param customers
	 * @param contacts
	 * @param opportunities
	 * @param potentials
	 */
	@Builder(toBuilder = true)
	public Source(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy, User owner,
			Boolean shared, Boolean deleted, @NonNull @NotNull @NotBlank(message = "must not be empty") String name,
			Set<Customer> customers, Set<Contact> contacts, Set<Opportunity> opportunities, Set<Potential> potentials) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, owner, shared, deleted, name);
		this.customers = customers;
		this.contacts = contacts;
		this.opportunities = opportunities;
		this.potentials = potentials;
	}

	public Source(String name) {
		super(name);
	}

}
