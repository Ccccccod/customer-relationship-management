/**
 * 
 */
package capstone.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
 * Ngành nghề
 * @author Tuna
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "Career", //
		uniqueConstraints = { //
		})
public class Career extends NamedEntity<Long>{
	private static final long serialVersionUID = 1L;

	/**
	 * Ngành nghề
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "field_id")
	private Field field;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "careers")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Customer> customers;
	
	public Long getFieldId() {
		if (field == null) return null;
		return field.getId();
	}

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
	 * @param field
	 * @param customers
	 */
	@Builder(toBuilder = true)
	public Career(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy, User owner,
			Boolean shared, Boolean deleted, @NonNull @NotNull @NotBlank(message = "must not be empty") String name,
			Field field, Set<Customer> customers) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, owner, shared, deleted, name);
		this.field = field;
		this.customers = customers;
	}
	
	public Career(Field field, String name) {
		super(name);
		this.field = field;
	}
	
	public Career(String name, Field field) {
		super(name);
		this.field = field;
	}
	
	public Career(String name) {
		super(name);
	}

}
