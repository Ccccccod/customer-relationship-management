/**
 * 
 */
package capstone.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import capstone.i18n.I18nConfig;
import capstone.model.Permission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * PermissionFunctionAction
 * @author Tuna
 *
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "PermissionFunctionAction", //
		uniqueConstraints = { //
				@UniqueConstraint(name = "PERMISSION_FUNCTION_ACTION_UK", columnNames = { "permission_function_id",
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
		return "ROLE_" + this.permissionAction.getName() + "_" + this.permissionFunction.getName();
	}
	
	@Getter
	@Autowired
	@Qualifier("i18nNameMessageSource")
	@Transient
	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@Deprecated
	protected MessageSource messageSource;

	/**
	 * @return Vietnamese name
	 */
	@Deprecated
	public String getViName() {
		try {
			if (Objects.isNull(messageSource)) {
				messageSource = new I18nConfig().i18nNameMessageSource();
			}
			return messageSource.getMessage("permission.action." + StringUtils.lowerCase(this.permissionAction.getName()), null,
					LocaleContextHolder.getLocale())
					+ " "
					+ messageSource.getMessage(
							"permission.function." + StringUtils.lowerCase(this.permissionFunction.getName()), null,
							LocaleContextHolder.getLocale());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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
	 * @param permissionFunction
	 * @param permissionAction
	 * @param roles
	 * @param messageSource
	 */
	@Builder(toBuilder = true)
	public PermissionFunctionAction(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy,
			User updatedBy, User owner, Boolean shared, Boolean deleted, PermissionFunction permissionFunction,
			PermissionAction permissionAction, Set<Role> roles, MessageSource messageSource) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, owner, shared, deleted);
		this.permissionFunction = permissionFunction;
		this.permissionAction = permissionAction;
		this.roles = roles;
		this.messageSource = messageSource;
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
