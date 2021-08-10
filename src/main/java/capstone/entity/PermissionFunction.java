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
	 * @param name
	 * @param permissionFunctionActions
	 */
	@Builder
	public PermissionFunction(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy,
			String name, Set<PermissionFunctionAction> permissionFunctionActions) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, name);
		this.permissionFunctionActions = permissionFunctionActions;
	}

	/**
	 * @param name
	 */
	public PermissionFunction(@NonNull String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

}
