/**
 * 
 */
package capstone.dto.request;

import java.util.Set;

import capstone.entity.Classification;
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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ContactDto extends NamedDto<Long> {

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
	 */
	public ContactDto(Long id) {
		super(id);
	}

	/**
	 * @param name
	 */
	@Builder
	public ContactDto(String name) {
		super(name);
	}

}
