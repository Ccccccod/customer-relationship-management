/**
 * 
 */
package capstone.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import capstone.model.Permission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Tuna
 *
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "PermissionFunctionAction", //
		uniqueConstraints = { //
				@UniqueConstraint(name = "USER_UK", columnNames = { "permission_function_id",
						"permission_action_id" })
		})
public class PermissionFunctionAction extends BaseEntity<Long> implements Permission {
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "permission_function_id", nullable = false)
	@JsonIgnore
	private PermissionFunction permissionFunction;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "permission_action_id", nullable = false)
	@JsonIgnore
	private PermissionAction permissionAction;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "permissionFunctionActions")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Role> roles;

	@JsonProperty("name")
	@Override
	public String getValue() {
		return this.permissionAction.getName() + "_" + this.permissionFunction.getName();
	}

	/**
	 * @param id
	 * @param createdAt
	 * @param updatedAt
	 * @param createdBy
	 * @param updatedBy
	 */
	@Builder
	public PermissionFunctionAction(Long id, Date createdAt, Date updatedAt, User createdBy, User updatedBy) {
		super(id, createdAt, updatedAt, createdBy, updatedBy);
	}

	/**
	 * @param permissionFunction
	 * @param permissionAction
	 */
	public PermissionFunctionAction(PermissionFunction permissionFunction, PermissionAction permissionAction) {
		this.permissionFunction = permissionFunction;
		this.permissionAction = permissionAction;
	}

}
