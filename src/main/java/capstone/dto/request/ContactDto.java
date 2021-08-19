/**
 * 
 */
package capstone.dto.request;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import capstone.common.enums.Gender;
import capstone.common.enums.MaritalStatus;
import capstone.dto.request.deserializer.LocalDateDeserializer;
import capstone.dto.response.serializer.LocalDateSerializer;
import capstone.dto.validatation.annotation.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Contact Dto
 * @author Tuna
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ContactDto extends CodedNamedDto<Long> {

	/**
	 * Xưng hô
	 */
	private String vocative;

	/**
	 * Họ và đệm
	 */
	private String lastName;

	/**
	 * Chức danh
	 */
	private String position;

	/**
	 * Phòng ban
	 */
	private String department;
	
	/**
	 * Tổ chức
	 */
	private Long customerId;
	
	/**
	 * Phân loại khách hàng
	 */
	private Set<Long> classificationIds;

	/**
	 * Điện thoại
	 */
	private String phone;
	
	/**
	 * Điện thoại cơ quan
	 */
	private String officePhone;
	
	/**
	 * Điện thoại khác
	 */
	private String otherPhone;

	/**
	 * Email
	 */
	@Email
	private String email;
	
	/**
	 * Email cơ quan
	 */
	@Email
	private String officeEmail;

	/**
	 * Nguồn gốc
	 */
	private Long sourceId;
	
	/**
	 * Địa chỉ
	 */
	private String address;
	
	// Other Information
	// Thông tin khác
	
	/**
	 * Ngày sinh
	 */
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate dateOfBirth;

	/**
	 * Giới tính
	 */
	@JsonProperty("genderId") //
	@JsonAlias("genderId")
	private Gender gender;

	/**
	 * Tình trạng hôn nhân
	 */
	@JsonProperty("maritalStatusId") //
	@JsonAlias("maritalStatusId")
	private MaritalStatus maritalStatus;
	
	/**
	 * facebook
	 */
	private String facebook;

	/**
	 * @param id
	 * @param name
	 * @param code
	 * @param vocative
	 * @param lastName
	 * @param position
	 * @param department
	 * @param customerId
	 * @param classificationIds
	 * @param phone
	 * @param officePhone
	 * @param otherPhone
	 * @param email
	 * @param officeEmail
	 * @param sourceId
	 * @param address
	 * @param dateOfBirth
	 * @param gender
	 * @param maritalStatus
	 * @param facebook
	 */
	@Builder
	public ContactDto(Long id, String name, String code, String vocative, String lastName, String position,
			String department, Long customerId, Set<Long> classificationIds, String phone, String officePhone,
			String otherPhone, String email, String officeEmail, Long sourceId, String address, LocalDate dateOfBirth,
			Gender gender, MaritalStatus maritalStatus, String facebook) {
		super(id, name, code);
		this.vocative = vocative;
		this.lastName = lastName;
		this.position = position;
		this.department = department;
		this.customerId = customerId;
		this.classificationIds = classificationIds;
		this.phone = phone;
		this.officePhone = officePhone;
		this.otherPhone = otherPhone;
		this.email = email;
		this.officeEmail = officeEmail;
		this.sourceId = sourceId;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.maritalStatus = maritalStatus;
		this.facebook = facebook;
	}

}
