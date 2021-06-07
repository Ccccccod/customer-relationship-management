package capstone.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

@Entity
@Table(name = "Role", //
		uniqueConstraints = { //
				@UniqueConstraint(name = "ROLE_UK", columnNames = "Name") })
public class Role extends BaseEntity {

	@Column(name = "Name", length = 36, nullable = false)
	private String name;

	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<User> users = new HashSet<User>();

	public Role(String name) {
		super();
		this.name = name;
	}
	
	public static String ADMIN = "ROLE_ADMIN";
	
	public static String MODERATOR = "ROLE_MODERATOR";
	
	public static String MEMBER = "ROLE_MEMBER";
	
}
