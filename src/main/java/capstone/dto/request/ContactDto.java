/**
 * 
 */
package capstone.dto.request;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import capstone.dto.request.deserializer.IdDeserializer;
import capstone.dto.request.deserializer.IdSetDeserializer;
import capstone.dto.request.deserializer.LocalDateDeserializer;
import capstone.dto.response.serializer.LocalDateSerializer;
import capstone.dto.validatation.annotation.Email;
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
 * Contact Dto
 * @author Tuna
 *
 */
@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ContactDto extends BaseDto<Long> implements Coded, Named {
	
	/**
	 * Mã cơ hội
	 */
	private String code;

	/**
	 * Xưng hô
	 */
	@JsonDeserialize(using = IdDeserializer.class)
	@JsonAlias("vocative")
	private Long vocativeId;

	/**
	 * Họ và đệm
	 */
	private String lastName;
	
	/**
	 * Tên
	 */
	private String name;

	/**
	 * Phòng ban
	 */
	@JsonDeserialize(using = IdDeserializer.class)
	@JsonAlias("department")
	private Long departmentId;

	/**
	 * Chức danh
	 */
	@JsonDeserialize(using = IdDeserializer.class)
	@JsonAlias("position")
	private Long positionId;
	
	/**
	 * Tổ chức
	 */
	@JsonDeserialize(using = IdDeserializer.class)
	@JsonAlias("customer")
	private Long customerId;
	
	/**
	 * Phân loại khách hàng
	 */
	@JsonDeserialize(using = IdSetDeserializer.class)
	@JsonAlias("classifications")
	private Set<Long> classificationIds;
	
	/**
	 * Không gọi điện
	 */
	private Boolean notCallPhone;
	
	/**
	 * Không gửi email
	 */
	private Boolean notSendEmail;

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
	@JsonDeserialize(using = IdDeserializer.class)
	@JsonAlias("source")
	private Long sourceId;
	
	// Address information
	// Thông tin địa chỉ
	
	/**
	 * Quốc gia 
	 */
	@JsonDeserialize(using = IdDeserializer.class)
	@JsonAlias("country")
	private Long countryId;
	
	/**
	 * Tỉnh
	 */
	@JsonDeserialize(using = IdDeserializer.class)
	@JsonAlias("province")
	private Long provinceId;
	
	/**
	 * Huyện
	 */
	@JsonDeserialize(using = IdDeserializer.class)
	@JsonAlias("district")
	private Long districtId;
	
	/**
	 * Xã, Phường
	 */
	@JsonDeserialize(using = IdDeserializer.class)
	@JsonAlias("ward")
	private Long wardId;
	
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
	@JsonDeserialize(using = IdDeserializer.class)
	@JsonAlias("gender")
	private Long genderId;

	/**
	 * Tình trạng hôn nhân
	 */
	@JsonDeserialize(using = IdDeserializer.class)
	@JsonAlias("maritalStatusId")
	private Long maritalStatusId;
	
	/**
	 * facebook
	 */
	private String facebook;

	/**
	 * Tài khoản ngân hàng
	 */
	private String bankAccount;
	
	/**
	 * Mở tại ngân hàng
	 */
	private String bank;

	/**
	 * @param id
	 * @param code
	 * @param vocativeId
	 * @param lastName
	 * @param name
	 * @param departmentId
	 * @param positionId
	 * @param customerId
	 * @param classificationIds
	 * @param notCallPhone
	 * @param notSendEmail
	 * @param phone
	 * @param officePhone
	 * @param otherPhone
	 * @param email
	 * @param officeEmail
	 * @param sourceId
	 * @param countryId
	 * @param provinceId
	 * @param districtId
	 * @param wardId
	 * @param address
	 * @param dateOfBirth
	 * @param genderId
	 * @param maritalStatusId
	 * @param facebook
	 * @param bankAccount
	 * @param bank
	 */
	public ContactDto(Long id, String code, Long vocativeId, String lastName, String name, Long departmentId,
			Long positionId, Long customerId, Set<Long> classificationIds, Boolean notCallPhone, Boolean notSendEmail,
			String phone, String officePhone, String otherPhone, String email, String officeEmail, Long sourceId,
			Long countryId, Long provinceId, Long districtId, Long wardId, String address, LocalDate dateOfBirth,
			Long genderId, Long maritalStatusId, String facebook, String bankAccount, String bank) {
		super(id);
		this.code = code;
		this.vocativeId = vocativeId;
		this.lastName = lastName;
		this.name = name;
		this.departmentId = departmentId;
		this.positionId = positionId;
		this.customerId = customerId;
		this.classificationIds = classificationIds;
		this.notCallPhone = notCallPhone;
		this.notSendEmail = notSendEmail;
		this.phone = phone;
		this.officePhone = officePhone;
		this.otherPhone = otherPhone;
		this.email = email;
		this.officeEmail = officeEmail;
		this.sourceId = sourceId;
		this.countryId = countryId;
		this.provinceId = provinceId;
		this.districtId = districtId;
		this.wardId = wardId;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.genderId = genderId;
		this.maritalStatusId = maritalStatusId;
		this.facebook = facebook;
		this.bankAccount = bankAccount;
		this.bank = bank;
	}

}
