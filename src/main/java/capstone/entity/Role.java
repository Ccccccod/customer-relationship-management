package capstone.entity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import capstone.common.Constant;
import capstone.model.Named;
import capstone.model.Permission;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Role
 * Vai trò
 * @author Tuna
 *
 */

@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "Role", //
		uniqueConstraints = { //
				@UniqueConstraint(name = "ROLE_UK", columnNames = "name") //
				})
public class Role extends BaseEntity<Long> implements Named {
	private static final long serialVersionUID = 1L;

	/**
	 * Tên
	 */
	@Column(name = "name", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String name;
	
	/**
	 * Mô tả
	 */
	@Column(name = "description", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String description;

	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Set<User> users = new HashSet<User>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "role_permission_function_action", //
			joinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) }, //
			inverseJoinColumns = { @JoinColumn(name = "permission_function_action_id", nullable = false, updatable = false) })
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<PermissionFunctionAction> permissionFunctionActions;
	
	@JsonIgnore
	public Set<Permission> getPermissions() {
		return Collections.unmodifiableSet(new LinkedHashSet<Permission>(this.permissionFunctionActions));
	}
	
	public boolean hasPermission(Permission permission) {
		return this.getPermissions().contains(permission);
	}
	
	public boolean hasPermissions(Permission... permission) {
		return this.getPermissions().containsAll(Arrays.asList(permission));
	}
	
	public boolean hasPermissions(Collection<Permission> permissions) {
		return this.getPermissions().containsAll(permissions);
	}
	
	public void addPermission(Permission permission) {
		if (permission instanceof PermissionFunctionAction) {
			this.permissionFunctionActions.add((PermissionFunctionAction) permission);
		} else {
			throw new java.lang.UnsupportedOperationException(
					"This permission is not supported: " + permission.getClass().getName());
		}
	}
	
	public static final String ADMIN = "Quản trị hệ thống";
	
	public static final String BUSINESS_STAFF = "Nhân viên kinh doanh";
	
	public static final String MEMBER = "Thành viên";

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
	 * @param description
	 * @param users
	 * @param permissionFunctionActions
	 */
	public Role(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy, User owner,
			Boolean shared, Boolean deleted, String name, String description, Set<User> users,
			Set<PermissionFunctionAction> permissionFunctionActions) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, owner, shared, deleted);
		this.name = name;
		this.description = description;
		this.users = users;
		this.permissionFunctionActions = permissionFunctionActions;
	}

	public Role(String name) {
		this.name = name;
	}
	
}
