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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Nguồn gốc
 * @author Tuna
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

	/**
	 * @param id
	 * @param createdAt
	 * @param updatedAt
	 * @param createdBy
	 * @param updatedBy
	 * @param name
	 * @param customers
	 * @param contacts
	 * @param opportunities
	 */
	@Builder
	Source(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy, String name,
			Set<Customer> customers, Set<Contact> contacts, Set<Opportunity> opportunities) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, name);
		this.customers = customers;
		this.contacts = contacts;
		this.opportunities = opportunities;
	}

	public Source(String name) {
		super(name);
	}

}
