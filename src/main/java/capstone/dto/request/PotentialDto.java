/**
 * 
 */
package capstone.dto.request;

import javax.validation.constraints.NotNull;

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
	@NotNull
	private String email;

	/**
	 * Mã số thuế
	 */
	@NotNull
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
	 * @param taxCode
	 * @param address
	 */
	@Builder
	public PotentialDto(Long id, String vocative, String lastName, String name, String department, String position,
			@NotNull String phone, Long sourceId, @NotNull String email, @NotNull String taxCode, String address) {
		super(id);
		this.vocative = vocative;
		this.lastName = lastName;
		this.name = name;
		this.department = department;
		this.position = position;
		this.phone = phone;
		this.sourceId = sourceId;
		this.email = email;
		this.taxCode = taxCode;
		this.address = address;
	}
	
}
