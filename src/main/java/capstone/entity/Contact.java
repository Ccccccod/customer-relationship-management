/**
 * 
 */
package capstone.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import capstone.common.Constant;
import capstone.common.enums.Gender;
import capstone.common.enums.MaritalStatus;
import capstone.dto.request.deserializer.LocalDateDeserializer;
import capstone.dto.response.serializer.LocalDateSerializer;
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
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
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
	
	/*
	 * Họ và Tên
	 */
	@JsonProperty
	public String getFullName() {
		return lastName + " " + name;
	}
	
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
	 * Điện thoại cơ quan
	 */
	@Column(name = "office_phone")
	private String officePhone;
	
	/**
	 * Điện thoại khác
	 */
	@Column(name = "other_phone")
	private String otherPhone;
	
	/**
	 * Email
	 */
	@Column(name = "email")
	private String email;
	
	/**
	 * Email cơ quan
	 */
	@Column(name = "office_email")
	private String officeEmail;

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
	
	// Other Information
	// Thông tin khác
	
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

	/**
	 * Tình trạng hôn nhân
	 */
	@Column(name = "marital_status")
	@Enumerated(EnumType.STRING)
	private MaritalStatus maritalStatus;
	
	/**
	 * facebook
	 */
	@Column(name = "facebook", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String facebook;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contact")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Opportunity> opportunities;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contact")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Order> orders;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "buyer")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Invoice> invoices;

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
	 * @param officePhone
	 * @param otherPhone
	 * @param email
	 * @param officeEmail
	 * @param source
	 * @param address
	 * @param dateOfBirth
	 * @param gender
	 * @param maritalStatus
	 * @param facebook
	 * @param opportunities
	 * @param orders
	 * @param invoices
	 */
	@Builder(toBuilder = true)
	public Contact(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy,
			String name, String code, String vocative, String lastName, String position, String department,
			Customer customer, Set<Classification> classifications, String phone, String officePhone, String otherPhone,
			String email, String officeEmail, Source source, String address, LocalDate dateOfBirth, Gender gender,
			MaritalStatus maritalStatus, String facebook, Set<Opportunity> opportunities, Set<Order> orders,
			Set<Invoice> invoices) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, name, code);
		this.vocative = vocative;
		this.lastName = lastName;
		this.position = position;
		this.department = department;
		this.customer = customer;
		this.classifications = classifications;
		this.phone = phone;
		this.officePhone = officePhone;
		this.otherPhone = otherPhone;
		this.email = email;
		this.officeEmail = officeEmail;
		this.source = source;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.maritalStatus = maritalStatus;
		this.facebook = facebook;
		this.opportunities = opportunities;
		this.orders = orders;
		this.invoices = invoices;
	}

	/**
	 * @param name
	 * @param code
	 */
	public Contact(String name, String code) {
		super(name, code);
	}

}
