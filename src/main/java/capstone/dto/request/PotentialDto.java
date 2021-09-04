/**
 * 
 */
package capstone.dto.request;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import capstone.dto.request.deserializer.IdDeserializable;
import capstone.dto.request.deserializer.IdsDeserializable;
import capstone.dto.request.deserializer.LocalDateDeserializer;
import capstone.dto.validatation.annotation.Email;
import capstone.model.Coded;
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
public class PotentialDto extends BaseDto<Long> implements Named, Coded {
	
	/**
	 * Mã tiềm năng
	 */
	@NotNull
	private String code;

	/**
	 * Xưng hô
	 */
	@JsonDeserialize(using = IdDeserializable.class)
	@JsonAlias("vocative")
	private Long vocativeId;

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
	@JsonDeserialize(using = IdDeserializable.class)
	@JsonAlias("department")
	private Long departmentId;
	
	/**
	 * Chức danh
	 */
	@JsonDeserialize(using = IdDeserializable.class)
	@JsonAlias("position")
	private Long positionId;
	
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
	 * Loại tiềm năng
	 */
	@JsonDeserialize(using = IdsDeserializable.class)
	@JsonAlias("classifications")
	private Set<Long> classificationIds;

	/**
	 * Nguồn gốc
	 */
	@JsonDeserialize(using = IdDeserializable.class)
	@JsonAlias("source")
	private Long sourceId;
	
	/**
	 * Không gọi điện
	 */
	private Boolean notCallPhone;
	
	/**
	 * Không gửi email
	 */
	private Boolean notSendEmail;
	
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
	private String customer;

	/**
	 * Mã số thuế
	 */
	private String taxCode;
	
	/** 
	 * Mã số thuế tổ chức
	 */
	private String customerTaxCode;
	
	/**
	 * Địa chỉ
	 */
	private String address;
	
	// Personal information
	// Thông tin cá nhân

	/**
	 * Giới tính
	 */
	@JsonDeserialize(using = IdDeserializable.class)
	@JsonAlias("gender")
	private Long genderId;
	
	/**
	 * Ngày sinh
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate dateOfBirth;
	
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
	 * Ngày thành lập
	 */
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate foundedDate;
	
	/**
	 * Loại hình doanh nghiệp
	 */
	@JsonDeserialize(using = IdDeserializable.class)
	@JsonAlias("businessType")
	private Long businessTypeId;

	/**
	 * @param id
	 * @param code
	 * @param vocativeId
	 * @param lastName
	 * @param name
	 * @param departmentId
	 * @param positionId
	 * @param phone
	 * @param officePhone
	 * @param otherPhone
	 * @param classificationIds
	 * @param sourceId
	 * @param notCallPhone
	 * @param notSendEmail
	 * @param email
	 * @param officeEmail
	 * @param customer
	 * @param taxCode
	 * @param customerTaxCode
	 * @param address
	 * @param genderId
	 * @param dateOfBirth
	 * @param facebook
	 * @param bankAccount
	 * @param bank
	 * @param foundedDate
	 * @param businessTypeId
	 */
	@Builder(toBuilder = true)
	public PotentialDto(Long id, @NotNull String code, Long vocativeId, String lastName, @NotBlank String name,
			Long departmentId, Long positionId, String phone, String officePhone, String otherPhone,
			Set<Long> classificationIds, Long sourceId, Boolean notCallPhone, Boolean notSendEmail, String email,
			String officeEmail, String customer, String taxCode, String customerTaxCode, String address, Long genderId,
			LocalDate dateOfBirth, String facebook, String bankAccount, String bank, LocalDate foundedDate,
			Long businessTypeId) {
		super(id);
		this.code = code;
		this.vocativeId = vocativeId;
		this.lastName = lastName;
		this.name = name;
		this.departmentId = departmentId;
		this.positionId = positionId;
		this.phone = phone;
		this.officePhone = officePhone;
		this.otherPhone = otherPhone;
		this.classificationIds = classificationIds;
		this.sourceId = sourceId;
		this.notCallPhone = notCallPhone;
		this.notSendEmail = notSendEmail;
		this.email = email;
		this.officeEmail = officeEmail;
		this.customer = customer;
		this.taxCode = taxCode;
		this.customerTaxCode = customerTaxCode;
		this.address = address;
		this.genderId = genderId;
		this.dateOfBirth = dateOfBirth;
		this.facebook = facebook;
		this.bankAccount = bankAccount;
		this.bank = bank;
		this.foundedDate = foundedDate;
		this.businessTypeId = businessTypeId;
	}
	
}
