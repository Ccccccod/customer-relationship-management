/**
 * 
 */
package capstone.entity;

import java.util.Date;
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
 * Contact
 * Liên hệ
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
@Table(name = "Contact", //
		uniqueConstraints = { //
				@UniqueConstraint(name = "CONTACT_UK", columnNames = "email") //
		})
public class Contact extends CodedNamedEntity<Long> {
	private static final long serialVersionUID = 1L;

	/**
	 * Xưng hô
	 */
	@Column(name = "vocative", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String vocative;

	/**
	 * Họ và đệm
	 */
	@Column(name = "last_name", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String lastName;
	
	/**
	 * Tên: name.
	 * In {@link NamedEntity}
	 */
	
	/**
	 * Chức danh
	 */
	@Column(name = "position", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String position;
	
	/**
	 * Phòng ban
	 */
	@Column(name = "department", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String department;
	
	/**
	 * Tổ chức
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	/**
	 * Phân loại khách hàng
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "contact_classification", //
			joinColumns = { @JoinColumn(name = "contact_id", nullable = false, updatable = false) }, //
			inverseJoinColumns = { @JoinColumn(name = "classification_id", nullable = false, updatable = false) })
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Classification> classifications;
	
	/**
	 * Phone
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
	 * Địa chỉ
	 */
	@Column(name = "address", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String address;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contact")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Opportunity> opportunities;

	/**
	 * @param id
	 * @param createdAt
	 * @param updatedAt
	 * @param createdBy
	 * @param updatedBy
	 * @param name
	 * @param code
	 * @param vocative
	 * @param lastName
	 * @param position
	 * @param department
	 * @param customer
	 * @param classifications
	 * @param phone
	 * @param email
	 * @param source
	 * @param address
	 */
	@Builder
	public Contact(Long id, Date createdAt, Date updatedAt, User createdBy, User updatedBy, String name, String code,
			String vocative, String lastName, String position, String department, Customer customer,
			Set<Classification> classifications, String phone, String email, Source source, String address) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, name, code);
		this.vocative = vocative;
		this.lastName = lastName;
		this.position = position;
		this.department = department;
		this.customer = customer;
		this.classifications = classifications;
		this.phone = phone;
		this.email = email;
		this.source = source;
		this.address = address;
	}

	/**
	 * @param name
	 * @param code
	 */
	public Contact(String name, String code) {
		super(name, code);
	}

}
