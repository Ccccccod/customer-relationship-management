/**
 * 
 */
package capstone.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import capstone.common.Constant;
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
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "gender")
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
	
	// Created & Updated
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "createdBy", cascade={CascadeType.ALL})
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Customer> customersCreated;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "updatedBy", cascade={CascadeType.ALL})
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Customer> customersUpdated;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "createdBy")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Potential> potentialsCreated;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "updatedBy")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Potential> potentialsUpdated;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "createdBy")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Contact> contactsCreated;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "updatedBy")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Contact> contactsUpdated;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "createdBy", cascade={CascadeType.PERSIST})
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<ProductType> productTypesCreated;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "updatedBy", cascade={CascadeType.PERSIST})
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<ProductType> productTypesUpdated;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "createdBy")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Product> productsCreated;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "updatedBy")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Product> productsUpdated;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "createdBy", cascade={CascadeType.PERSIST})
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Opportunity> opportunitiesCreated;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "updatedBy", cascade={CascadeType.PERSIST})
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Opportunity> opportunitysUpdated;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "createdBy")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Order> ordersCreated;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "updatedBy")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Order> ordersUpdated;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "createdBy")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Invoice> invoicesCreated;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "updatedBy")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Invoice> invoicesUpdated;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "createdBy")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<User> usersCreated;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "updatedBy")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<User> usersUpdated;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "createdBy")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Role> rolesCreated;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "updatedBy")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Role> rolesUpdated;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "createdBy")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<ProductInfo> productInfosCreated;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "updatedBy")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<ProductInfo> productInfosUpdated;

	/**
	 * @param id
	 * @param createdAt
	 * @param updatedAt
	 * @param createdBy
	 * @param updatedBy
	 * @param owner
	 * @param shared
	 * @param deleted
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
	 * @param customersCreated
	 * @param customersUpdated
	 * @param potentialsCreated
	 * @param potentialsUpdated
	 * @param contactsCreated
	 * @param contactsUpdated
	 * @param productTypesCreated
	 * @param productTypesUpdated
	 * @param productsCreated
	 * @param productsUpdated
	 * @param opportunitiesCreated
	 * @param opportunitysUpdated
	 * @param ordersCreated
	 * @param ordersUpdated
	 * @param invoicesCreated
	 * @param invoicesUpdated
	 * @param usersCreated
	 * @param usersUpdated
	 * @param rolesCreated
	 * @param rolesUpdated
	 * @param productInfosCreated
	 * @param productInfosUpdated
	 */
	@Builder(toBuilder = true)
	public User(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy, User owner,
			Boolean shared, Boolean deleted, String username, String password, String email, Set<Role> roles,
			String lastName, String name, String phone, LocalDate dateOfBirth, Gender gender, String address,
			Set<Customer> customersCreated, Set<Customer> customersUpdated, Set<Potential> potentialsCreated,
			Set<Potential> potentialsUpdated, Set<Contact> contactsCreated, Set<Contact> contactsUpdated,
			Set<ProductType> productTypesCreated, Set<ProductType> productTypesUpdated, Set<Product> productsCreated,
			Set<Product> productsUpdated, Set<Opportunity> opportunitiesCreated, Set<Opportunity> opportunitysUpdated,
			Set<Order> ordersCreated, Set<Order> ordersUpdated, Set<Invoice> invoicesCreated,
			Set<Invoice> invoicesUpdated, Set<User> usersCreated, Set<User> usersUpdated, Set<Role> rolesCreated,
			Set<Role> rolesUpdated, Set<ProductInfo> productInfosCreated, Set<ProductInfo> productInfosUpdated) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, owner, shared, deleted);
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
		this.customersCreated = customersCreated;
		this.customersUpdated = customersUpdated;
		this.potentialsCreated = potentialsCreated;
		this.potentialsUpdated = potentialsUpdated;
		this.contactsCreated = contactsCreated;
		this.contactsUpdated = contactsUpdated;
		this.productTypesCreated = productTypesCreated;
		this.productTypesUpdated = productTypesUpdated;
		this.productsCreated = productsCreated;
		this.productsUpdated = productsUpdated;
		this.opportunitiesCreated = opportunitiesCreated;
		this.opportunitysUpdated = opportunitysUpdated;
		this.ordersCreated = ordersCreated;
		this.ordersUpdated = ordersUpdated;
		this.invoicesCreated = invoicesCreated;
		this.invoicesUpdated = invoicesUpdated;
		this.usersCreated = usersCreated;
		this.usersUpdated = usersUpdated;
		this.rolesCreated = rolesCreated;
		this.rolesUpdated = rolesUpdated;
		this.productInfosCreated = productInfosCreated;
		this.productInfosUpdated = productInfosUpdated;
	}
	
	public User(String username, String email, String password) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

}
