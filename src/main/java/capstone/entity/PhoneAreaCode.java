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

import capstone.model.Named;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * PhoneAreaCode
 * @author Tuna
 */
@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "PhoneAreaCode", //
		uniqueConstraints = { //
		})
public class PhoneAreaCode extends NamedEntity<Long> implements Named {
	private static final long serialVersionUID = 1L;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "phoneAreaCode")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Potential> potentials;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "phoneAreaCode")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Contact> contacts;

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
	 * @param potentials
	 * @param contacts
	 */
	public PhoneAreaCode(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy,
			User owner, Boolean shared, Boolean deleted, String name, Set<Potential> potentials,
			Set<Contact> contacts) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, owner, shared, deleted, name);
		this.potentials = potentials;
		this.contacts = contacts;
	}

	/**
	 * @param name
	 */
	public PhoneAreaCode(String name) {
		super(name);
	}

}
