/**
 * 
 */
package capstone.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import capstone.common.Constant;
import capstone.common.enums.Gender;
import capstone.dto.request.deserializer.LocalDateDeserializer;
import capstone.dto.response.serializer.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * Potential
 * Tiềm năng
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
@Table(name = "Potential", //
		uniqueConstraints = { //
		})
public class Potential extends NamedEntity<Long> {
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
	 * Phòng ban
	 */
	@Column(name = "department", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String department;
	
	/**
	 * Chức danh
	 */
	@Column(name = "position", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String position;
	
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
	 * Nguồn gốc
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "source_id")
	private Source source;
	
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
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	/**
	 * Mã số thuế
	 */
	@Column(name = "tax_code")
	private String taxCode;
	
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
	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
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
	@Column(name = "address", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String facebook;

	/**
	 * @param id
	 * @param createdAt
	 * @param updatedAt
	 * @param createdBy
	 * @param updatedBy
	 * @param name
	 * @param vocative
	 * @param lastName
	 * @param department
	 * @param position
	 * @param phone
	 * @param officePhone
	 * @param otherPhone
	 * @param source
	 * @param email
	 * @param officeEmail
	 * @param customer
	 * @param taxCode
	 * @param address
	 * @param gender
	 * @param dateOfBirth
	 * @param facebook
	 */
	@Builder(toBuilder = true)
	public Potential(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy,
			String name, String vocative, String lastName, String department, String position, String phone,
			String officePhone, String otherPhone, Source source, String email, String officeEmail, Customer customer,
			String taxCode, String address, Gender gender, LocalDate dateOfBirth, String facebook) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, name);
		this.vocative = vocative;
		this.lastName = lastName;
		this.department = department;
		this.position = position;
		this.phone = phone;
		this.officePhone = officePhone;
		this.otherPhone = otherPhone;
		this.source = source;
		this.email = email;
		this.officeEmail = officeEmail;
		this.customer = customer;
		this.taxCode = taxCode;
		this.address = address;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.facebook = facebook;
	}

	/**
	 * @param name
	 */
	public Potential(@NonNull String name) {
		super(name);
	}

}
