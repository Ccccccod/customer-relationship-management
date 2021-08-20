/**
 * 
 */
package capstone.entity;

import java.time.LocalDateTime;
import java.util.Set;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import capstone.common.Constant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Customer
 * Khách hàng
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
@Table(name = "Customer", //
		uniqueConstraints = { //
				@UniqueConstraint(name = "CUSTOMER_UK", columnNames = "email"), //
				@UniqueConstraint(name = "CUSTOMER_UK", columnNames = "tax_code") //
		})
public class Customer extends CodedNamedEntity<Long> {
	private static final long serialVersionUID = 1L;

	/**
	 * Tên viết tắt
	 */
	@Column(name = "short_name", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String shortName;

	/**
	 * Mã số thuế
	 */
	@Column(name = "tax_code", unique = true, nullable = false)
	private String taxCode;

	/**
	 * Điện thoại
	 */
	@Column(name = "phone")
	private String phone;

	/**
	 * Email
	 */
	@Column(name = "email", unique = true, nullable = false)
	private String email;

	/**
	 * Nguồn gốc
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "source_id")
	private Source source;

	/**
	 * Phân loại khách hàng
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "customer_classification", //
			joinColumns = { @JoinColumn(name = "customer_id", nullable = false, updatable = false) }, //
			inverseJoinColumns = { @JoinColumn(name = "classification_id", nullable = false, updatable = false) })
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Classification> classifications;

	/**
	 * Lĩnh vực
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "customer_field", //
			joinColumns = { @JoinColumn(name = "customer_id", nullable = false, updatable = false) }, //
			inverseJoinColumns = { @JoinColumn(name = "field_id", nullable = false, updatable = false) })
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Field> fields;

	/**
	 * Loại hình
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_id")
	private Type type;

	/**
	 * Ngành nghề
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "customer_career", //
			joinColumns = { @JoinColumn(name = "customer_id", nullable = false, updatable = false) }, //
			inverseJoinColumns = { @JoinColumn(name = "career_id", nullable = false, updatable = false) })
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Career> careers;

	/**
	 * Địa chỉ
	 */
	@Column(name = "address", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String address;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Contact> contacts;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Opportunity> opportunities;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Order> orders;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Invoice> invoices;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Potential> potentials;

	/**
	 * @param id
	 * @param createdAt
	 * @param updatedAt
	 * @param createdBy
	 * @param updatedBy
	 * @param name
	 * @param code
	 * @param shortName
	 * @param taxCode
	 * @param phone
	 * @param email
	 * @param source
	 * @param classifications
	 * @param fields
	 * @param type
	 * @param careers
	 * @param address
	 * @param contacts
	 * @param opportunities
	 * @param orders
	 * @param invoices
	 * @param potentials
	 */
	@Builder(toBuilder = true)
	public Customer(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy,
			String name, String code, String shortName, String taxCode, String phone, String email, Source source,
			Set<Classification> classifications, Set<Field> fields, Type type, Set<Career> careers, String address,
			Set<Contact> contacts, Set<Opportunity> opportunities, Set<Order> orders, Set<Invoice> invoices,
			Set<Potential> potentials) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, name, code);
		this.shortName = shortName;
		this.taxCode = taxCode;
		this.phone = phone;
		this.email = email;
		this.source = source;
		this.classifications = classifications;
		this.fields = fields;
		this.type = type;
		this.careers = careers;
		this.address = address;
		this.contacts = contacts;
		this.opportunities = opportunities;
		this.orders = orders;
		this.invoices = invoices;
		this.potentials = potentials;
	}

	/**
	 * @param name
	 * @param code
	 */
	public Customer(String name, String code) {
		super(name, code);
	}

}
