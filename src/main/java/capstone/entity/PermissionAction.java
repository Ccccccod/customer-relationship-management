/**
 * 
 */
package capstone.entity;

import java.util.Date;
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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "PermissionAction", //
		uniqueConstraints = { //
		})
public class PermissionAction extends NamedEntity<Long> {
	private static final long serialVersionUID = 1L;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "permissionAction")
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
	public PermissionAction(Long id, Date createdAt, Date updatedAt, User createdBy, User updatedBy, String name,
			Set<PermissionFunctionAction> permissionFunctionActions) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, name);
		this.permissionFunctionActions = permissionFunctionActions;
	}

	/**
	 * @param name
	 */
	public PermissionAction(@NonNull String name) {
		super(name);
	}

}
