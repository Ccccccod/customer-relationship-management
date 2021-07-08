/**
 * 
 */
package capstone.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * User account
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
@Table(name = "[User]", //
		uniqueConstraints = { //
				@UniqueConstraint(name = "USER_UK", columnNames = "name"), //
				@UniqueConstraint(name = "USER_UK", columnNames = "email"), //
		})
public class User extends NamedEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "password", length = 128, nullable = false)
	private String password;

	@Column(name = "email", length = 320, nullable = false)
	private String email;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), //
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Role> roles = new HashSet<>();

	/**
	 * @param id
	 * @param createdAt
	 * @param updatedAt
	 * @param createdBy
	 * @param updatedBy
	 * @param name
	 * @param name2
	 * @param password
	 * @param email
	 * @param roles
	 */
	@Builder
	public User(Long id, Date createdAt, Date updatedAt, User createdBy, User updatedBy, String name, String name2,
			String password, String email, Set<Role> roles) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, name);
		name = name2;
		this.password = password;
		this.email = email;
		this.roles = roles;
	}

	/**
	 * @param name
	 * @param email
	 * @param password
	 */
	public User(String name, String email, String password) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
	}

}
