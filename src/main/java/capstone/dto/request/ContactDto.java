/**
 * 
 */
package capstone.dto.request;

import java.util.Set;

import javax.validation.constraints.NotNull;

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
@Builder
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
	 * Email
	 */
	@NotNull
	@Email
	private String email;

	/**
	 * Nguồn gốc
	 */
	private Long sourceId;
	
	/**
	 * Địa chỉ
	 */
	private String address;

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
	 * @param email
	 * @param sourceId
	 * @param address
	 */
	@Builder
	public ContactDto(Long id, String name,
			String code, String vocative, String lastName, String position,
			String department, Long customerId, Set<Long> classificationIds, String phone, String email, Long sourceId,
			String address) {
		super(id, name, code);
		this.vocative = vocative;
		this.lastName = lastName;
		this.position = position;
		this.department = department;
		this.customerId = customerId;
		this.classificationIds = classificationIds;
		this.phone = phone;
		this.email = email;
		this.sourceId = sourceId;
		this.address = address;
	}

}
