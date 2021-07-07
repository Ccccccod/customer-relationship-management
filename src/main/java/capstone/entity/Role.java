package capstone.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import capstone.common.Constant;
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
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "Role", //
		uniqueConstraints = { //
//				@UniqueConstraint(name = "ROLE_UK", columnNames = "name") //
				})
public class Role extends NamedEntity<Long> {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "description", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String description;

	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Set<User> users = new HashSet<User>();

	public Role(String name) {
		super(name);
	}
	
	public static final String ADMIN = "ROLE_ADMIN";
	
	public static final String MODERATOR = "ROLE_MODERATOR";
	
	public static final String MEMBER = "ROLE_MEMBER";
	
}
