/**
 * 
 */
package capstone.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	@NotNull
	private String phone;

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

	/**
	 * @param id
	 * @param vocative
	 * @param lastName
	 * @param name
	 * @param department
	 * @param position
	 * @param phone
	 * @param sourceId
	 * @param email
	 * @param customerId
	 * @param taxCode
	 * @param address
	 */
	@Builder(toBuilder = true)
	public PotentialDto(Long id, String vocative, String lastName, @NotBlank String name, String department,
			String position, @NotNull String phone, Long sourceId, String email, Long customerId, String taxCode,
			String address) {
		super(id);
		this.vocative = vocative;
		this.lastName = lastName;
		this.name = name;
		this.department = department;
		this.position = position;
		this.phone = phone;
		this.sourceId = sourceId;
		this.email = email;
		this.customerId = customerId;
		this.taxCode = taxCode;
		this.address = address;
	}
	
}
