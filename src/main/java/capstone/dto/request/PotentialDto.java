/**
 * 
 */
package capstone.dto.request;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import capstone.common.enums.Gender;
import capstone.dto.request.deserializer.LocalDateDeserializer;
import capstone.dto.response.serializer.LocalDateSerializer;
import capstone.dto.validatation.annotation.Email;
import capstone.model.Named;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * PotentialDto
 * Tiềm năng Dto
 * @author Tuna
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PotentialDto extends BaseDto<Long> implements Named {

	/**
	 * Xưng hô
	 */
	private String vocative;

	/**
	 * Họ và đệm
	 */
	private String lastName;
	
	/**
	 * Tên
	 */
	@NotBlank
	private String name;
	
	/**
	 * Phòng ban
	 */
	private String department;
	
	/**
	 * Chức danh
	 */
	private String position;
	
	/**
	 * Phone
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
	 * Nguồn gốc
	 */
	private Long sourceId;
	
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
	 * Tổ chức
	 */
	private Long customerId;

	/**
	 * Mã số thuế
	 */
	private String taxCode;
	
	/**
	 * Địa chỉ
	 */
	private String address;
	
	// Personal information
	// Thông tin cá nhân

	/**
	 * Giới tính
	 */
	@JsonProperty("genderId") //
	@JsonAlias("genderId")
	private Gender gender;
	
	/**
	 * Ngày sinh
	 */
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate dateOfBirth;
	
	/**
	 * facebook
	 */
	private String facebook;

	/**
	 * @param id
	 * @param vocative
	 * @param lastName
	 * @param name
	 * @param department
	 * @param position
	 * @param phone
	 * @param officePhone
	 * @param otherPhone
	 * @param sourceId
	 * @param email
	 * @param officeEmail
	 * @param customerId
	 * @param taxCode
	 * @param address
	 * @param gender
	 * @param dateOfBirth
	 * @param facebook
	 */
	@Builder(toBuilder = true)
	public PotentialDto(Long id, String vocative, String lastName, @NotBlank String name, String department,
			String position, String phone, String officePhone, String otherPhone, Long sourceId, String email,
			String officeEmail, Long customerId, String taxCode, String address, Gender gender, LocalDate dateOfBirth,
			String facebook) {
		super(id);
		this.vocative = vocative;
		this.lastName = lastName;
		this.name = name;
		this.department = department;
		this.position = position;
		this.phone = phone;
		this.officePhone = officePhone;
		this.otherPhone = otherPhone;
		this.sourceId = sourceId;
		this.email = email;
		this.officeEmail = officeEmail;
		this.customerId = customerId;
		this.taxCode = taxCode;
		this.address = address;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.facebook = facebook;
	}
	
}
