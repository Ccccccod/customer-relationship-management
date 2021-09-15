/**
 * 
 */
package capstone.entity;

import java.time.LocalDate;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import capstone.common.Constant;
import capstone.dto.request.deserializer.LocalDateDeserializer;
import capstone.dto.response.serializer.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Contact
 * Liên hệ
 * @author Tuna
 */
@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "Contact", //
		uniqueConstraints = { //
//				@UniqueConstraint(name = "CONTACT_UK", columnNames = "email") //
		})
public class Contact extends CodedNamedEntity<Long> {
	private static final long serialVersionUID = 1L;

	/**
	 * Xưng hô
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vocative_id")
	private Vocative vocative;

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
		String lastName = this.lastName != null ? this.lastName : "";
		String name = this.name != null ? this.name : "";
		return (lastName + " " + name).trim();
	}
	
	/**
	 * Phòng ban
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "department_id")
	private Department department;
	
	/**
	 * Chức danh
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "position_id")
	private Position position;
	
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
	 * Không gọi điện
	 */
	@Column(name = "not_call_phone")
	private Boolean notCallPhone;
	
	/**
	 * Không gửi email
	 */
	@Column(name = "not_send_email")
	private Boolean notSendEmail;
	
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
	
	// Thông tin địa chỉ
	
	/**
	 * Quốc gia 
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "country_id")
	private Country country;
	
	/**
	 * Tỉnh
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "province_id")
	private Province province;
	
	/**
	 * Huyện
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "district_id")
	private District district;
	
	/**
	 * Xã, Phường
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ward_id")
	private Ward ward;
	
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
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "gender")
	private Gender gender;

	/**
	 * Tình trạng hôn nhân
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "marital_status")
	private MaritalStatus maritalStatus;
	
	/**
	 * facebook
	 */
	@Column(name = "facebook")
	private String facebook;

	/**
	 * Tài khoản ngân hàng
	 */
	@Column(name = "bank_account")
	private String bankAccount;
	
	/**
	 * Mở tại ngân hàng
	 */
	@Column(name = "bank", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String bank;

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
	 * @param owner
	 * @param shared
	 * @param deleted
	 * @param name
	 * @param code
	 * @param vocative
	 * @param lastName
	 * @param department
	 * @param position
	 * @param customer
	 * @param classifications
	 * @param notCallPhone
	 * @param notSendEmail
	 * @param phone
	 * @param officePhone
	 * @param otherPhone
	 * @param email
	 * @param officeEmail
	 * @param source
	 * @param country
	 * @param province
	 * @param district
	 * @param ward
	 * @param address
	 * @param dateOfBirth
	 * @param gender
	 * @param maritalStatus
	 * @param facebook
	 * @param bankAccount
	 * @param bank
	 * @param opportunities
	 * @param orders
	 * @param invoices
	 */
	public Contact(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy,
			User owner, Boolean shared, Boolean deleted, String name, String code, Vocative vocative, String lastName,
			Department department, Position position, Customer customer, Set<Classification> classifications,
			Boolean notCallPhone, Boolean notSendEmail, String phone, String officePhone, String otherPhone,
			String email, String officeEmail, Source source, Country country, Province province, District district,
			Ward ward, String address, LocalDate dateOfBirth, Gender gender, MaritalStatus maritalStatus,
			String facebook, String bankAccount, String bank, Set<Opportunity> opportunities, Set<Order> orders,
			Set<Invoice> invoices) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, owner, shared, deleted, name, code);
		this.vocative = vocative;
		this.lastName = lastName;
		this.department = department;
		this.position = position;
		this.customer = customer;
		this.classifications = classifications;
		this.notCallPhone = notCallPhone;
		this.notSendEmail = notSendEmail;
		this.phone = phone;
		this.officePhone = officePhone;
		this.otherPhone = otherPhone;
		this.email = email;
		this.officeEmail = officeEmail;
		this.source = source;
		this.country = country;
		this.province = province;
		this.district = district;
		this.ward = ward;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.maritalStatus = maritalStatus;
		this.facebook = facebook;
		this.bankAccount = bankAccount;
		this.bank = bank;
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
