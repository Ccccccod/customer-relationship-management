/**
 * 
 */
package capstone.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * Gender
 * Giới tính
 * @author Tuna
 */
public class Gender extends NamedEntity<Long> {
	private static final long serialVersionUID = 1L;

	/**
	 * Tiềm năng
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "gender")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Potential> potentials;

	/**
	 * Cơ hội
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "gender")
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
	@Builder(toBuilder = true)
	public Gender(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy, User owner,
			Boolean shared, Boolean deleted, @NonNull @NotNull @NotBlank(message = "must not be empty") String name,
			Set<Potential> potentials, Set<Contact> contacts) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, owner, shared, deleted, name);
		this.potentials = potentials;
		this.contacts = contacts;
	}

	/**
	 * @param name
	 */
	public Gender(@NonNull String name) {
		super(name);
	}

}