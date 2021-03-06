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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import capstone.common.Constant;
import capstone.common.annotation.UniqueOrNull;
import capstone.dto.request.deserializer.LocalDateDeserializer;
import capstone.dto.response.serializer.LocalDateSerializer;
import capstone.model.Coded;
import capstone.model.Named;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Potential
 * Tiềm năng
 * @author Tuna
 */
@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "Potential", //
		uniqueConstraints = { //
		})
public class Potential extends BaseEntity<Long> implements Coded, Named {
	private static final long serialVersionUID = 1L;

	/**
	 * Mã tiềm năng
	 */
	@UniqueOrNull
	@Column(name = "code", nullable = false)
	private String code;

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
	 * Tên
	 */
	@Column(name = "name", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String name;
	
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
	 * Điện thoại
	 */
	@Column(name = "phone")
	private String phone;
	
	/**
	 * ma vung dien thoai co quan
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "phone_area_code_id")
	private PhoneAreaCode phoneAreaCode;
	
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
	 * Loại tiềm năng
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "potential_classification", //
			joinColumns = { @JoinColumn(name = "potential_id", nullable = false, updatable = false) }, //
			inverseJoinColumns = { @JoinColumn(name = "classification_id", nullable = false, updatable = false) })
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Classification> classifications;

	/**
	 * Nguồn gốc
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "source_id")
	private Source source;
	
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
	 * Tổ chức
	 */
	@Column(name = "customer_id", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String customer;

	/**
	 * Mã số thuế
	 */
	@Column(name = "tax_code")
	private String taxCode;

	/**
	 * Mã số thuế
	 */
	@Column(name = "customer_tax_code")
	private String customerTaxCode;
	
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
	
	// Personal information
	// Thông tin cá nhân

	/**
	 * Giới tính
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "gender_id")
	private Gender gender;
	
	/**
	 * Ngày sinh
	 */
	@Column(name = "date_of_birth")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate dateOfBirth;
	
	/**
	 * facebook
	 */
	@Column(name = "facebook")
	private String facebook;
	
	// Organization information
	// Thông tin tổ chức

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
	
	/**
	 * Ngày thành lập
	 */
	@Column(name = "founded_date")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate foundedDate;
	
	/**
	 * Loại hình doanh nghiệp
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "business_type_id")
	private BusinessType businessType;

	/**
	 * Lĩnh vực
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "potential_field", //
			joinColumns = { @JoinColumn(name = "potential_id", nullable = false, updatable = false) }, //
			inverseJoinColumns = { @JoinColumn(name = "field_id", nullable = false, updatable = false) })
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Field> fields;

	/**
	 * Ngành nghề
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "potential_career", //
			joinColumns = { @JoinColumn(name = "potential_id", nullable = false, updatable = false) }, //
			inverseJoinColumns = { @JoinColumn(name = "career_id", nullable = false, updatable = false) })
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Career> careers;
	
	/**
	 * Thu nhập
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "income_id")
	private Income income;
	
	/**
	 * website
	 */
	@Column(name = "website")
	private String website;

	/**
	 * @param id
	 * @param createdAt
	 * @param updatedAt
	 * @param createdBy
	 * @param updatedBy
	 * @param owner
	 * @param shared
	 * @param deleted
	 * @param code
	 * @param vocative
	 * @param lastName
	 * @param name
	 * @param department
	 * @param position
	 * @param phone
	 * @param phoneAreaCode
	 * @param officePhone
	 * @param otherPhone
	 * @param classifications
	 * @param source
	 * @param notCallPhone
	 * @param notSendEmail
	 * @param email
	 * @param officeEmail
	 * @param customer
	 * @param taxCode
	 * @param customerTaxCode
	 * @param country
	 * @param province
	 * @param district
	 * @param ward
	 * @param address
	 * @param gender
	 * @param dateOfBirth
	 * @param facebook
	 * @param bankAccount
	 * @param bank
	 * @param foundedDate
	 * @param businessType
	 * @param fields
	 * @param careers
	 * @param income
	 * @param website
	 */
	public Potential(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy,
			User owner, Boolean shared, Boolean deleted, String code, Vocative vocative, String lastName, String name,
			Department department, Position position, String phone, PhoneAreaCode phoneAreaCode, String officePhone,
			String otherPhone, Set<Classification> classifications, Source source, Boolean notCallPhone,
			Boolean notSendEmail, String email, String officeEmail, String customer, String taxCode,
			String customerTaxCode, Country country, Province province, District district, Ward ward, String address,
			Gender gender, LocalDate dateOfBirth, String facebook, String bankAccount, String bank,
			LocalDate foundedDate, BusinessType businessType, Set<Field> fields, Set<Career> careers, Income income,
			String website) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, owner, shared, deleted);
		this.code = code;
		this.vocative = vocative;
		this.lastName = lastName;
		this.name = name;
		this.department = department;
		this.position = position;
		this.phone = phone;
		this.phoneAreaCode = phoneAreaCode;
		this.officePhone = officePhone;
		this.otherPhone = otherPhone;
		this.classifications = classifications;
		this.source = source;
		this.notCallPhone = notCallPhone;
		this.notSendEmail = notSendEmail;
		this.email = email;
		this.officeEmail = officeEmail;
		this.customer = customer;
		this.taxCode = taxCode;
		this.customerTaxCode = customerTaxCode;
		this.country = country;
		this.province = province;
		this.district = district;
		this.ward = ward;
		this.address = address;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.facebook = facebook;
		this.bankAccount = bankAccount;
		this.bank = bank;
		this.foundedDate = foundedDate;
		this.businessType = businessType;
		this.fields = fields;
		this.careers = careers;
		this.income = income;
		this.website = website;
	}

	/**
	 * @param name
	 */
	public Potential(String name) {
		this.name = name;
	}

}
