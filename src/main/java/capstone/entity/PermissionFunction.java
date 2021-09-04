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
@Table(name = "PermissionFunction", //
		uniqueConstraints = { //
		})
public class PermissionFunction extends NamedEntity<Long> {
	private static final long serialVersionUID = 1L;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "permissionFunction")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<PermissionFunctionAction> permissionFunctionActions;

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
	 * @param permissionFunctionActions
	 */
	@Builder(toBuilder = true)
	public PermissionFunction(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy,
			User owner, Boolean shared, Boolean deleted,
			@NonNull @NotNull @NotBlank(message = "must not be empty") String name,
			Set<PermissionFunctionAction> permissionFunctionActions) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, owner, shared, deleted, name);
		this.permissionFunctionActions = permissionFunctionActions;
	}

	/**
	 * @param name
	 */
	public PermissionFunction(@NonNull String name) {
		super(name);
	}

}
