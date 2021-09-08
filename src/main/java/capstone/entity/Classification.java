/**
 * 
 */
package capstone.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Phân loại khách hàng
 * @author Tuna
 */

@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "Classification", //
		uniqueConstraints = { //
		})
public class Classification extends NamedEntity<Long> {
	private static final long serialVersionUID = 1L;

	/**
	 * Tổ chức
	 */
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "classifications")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Customer> customers;

	/**
	 * Liên hệ
	 */
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "classifications")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Contact> contacts;

	/**
	 * Tiềm năng
	 */
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "classifications")
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
	 * @param potentials
	 */
	public Classification(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy,
			User owner, Boolean shared, Boolean deleted,
			@NonNull @NotNull @NotBlank(message = "must not be empty") String name, Set<Customer> customers,
			Set<Contact> contacts, Set<Potential> potentials) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, owner, shared, deleted, name);
		this.customers = customers;
		this.contacts = contacts;
		this.potentials = potentials;
	}

	public Classification(String name) {
		super(name);
	}

}