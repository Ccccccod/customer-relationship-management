/**
 * 
 */
package capstone.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import capstone.common.Constant;
import capstone.common.enums.Gender;
import capstone.dto.request.deserializer.LocalDateDeserializer;
import capstone.dto.response.serializer.LocalDateSerializer;
import capstone.model.Named;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * User account
 * Người dùng
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
@Table(name = "[User]", //
		uniqueConstraints = { //
				@UniqueConstraint(name = "USER_UK", columnNames = "username"), //
				@UniqueConstraint(name = "USER_UK", columnNames = "email"), //
		})
public class User extends BaseEntity<Long> implements Named {
	private static final long serialVersionUID = 1L;
	
	@JsonAlias("username")
	@JsonProperty("username")
	@Column(name = "username", nullable = false, updatable = false, unique = true)
	private String username;

	@JsonProperty(access = Access.WRITE_ONLY) // Ignore in case this user is added to response
	@Column(name = "password", length = 128, nullable = false)
	private String password;

	@Column(name = "email", length = 320, nullable = false, updatable = false, unique = true)
	private String email;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), //
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Role> roles = new HashSet<>();

	/**
	 * Họ và đệm
	 */
	@Column(name = "last_name", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String lastName;

	/**
	 * Họ và đệm
	 */
	@Column(name = "name", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String name;
	
	@Column(name = "phone")
	private String phone;
	
	/**
	 * Ngày sinh
	 */
	@Column(name = "date_of_birth")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate dateOfBirth;

	/**
	 * Giới tính
	 */
	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "address", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String address;
	
	/**
	 * Họ và tên
	 * @return
	 */
	public String getFullName() {
		return (this.lastName != null ? this.lastName : "") + ' ' + (this.name != null ? this.name : "");
	}

	/**
	 * @param id
	 * @param createdAt
	 * @param updatedAt
	 * @param createdBy
	 * @param updatedBy
	 * @param username
	 * @param password
	 * @param email
	 * @param roles
	 * @param lastName
	 * @param name
	 * @param phone
	 * @param dateOfBirth
	 * @param gender
	 * @param address
	 */
	@Builder(toBuilder = true)
	public User(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy,
			String username, String password, String email, Set<Role> roles, String lastName, String name, String phone,
			LocalDate dateOfBirth, Gender gender, String address) {
		super(id, createdAt, updatedAt, createdBy, updatedBy);
		this.username = username;
		this.password = password;
		this.email = email;
		this.roles = roles;
		this.lastName = lastName;
		this.name = name;
		this.phone = phone;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.address = address;
	}
	
	public User(String username, String email, String password) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

}
