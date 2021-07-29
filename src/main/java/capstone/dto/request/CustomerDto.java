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
 * Khách hàng dto
 * @author Tuna
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class CustomerDto extends CodedNamedDto<Long> {
	
	private String shortName;

	@NotNull
	private String taxCode;
	
	private String phone;

	@NotNull
	@Email
	private String email;
	
	/**
	 * Nguon goc
	 */
	private Long sourceId;
	
	/**
	 * Phan loai khach hang
	 */
	private Set<Long> classificationIds;
	
	private Set<Long> fieldIds;
	
	private Long typeId;

	private Set<Long> careerIds;
	
	private String address;

	/**
	 * @param id
	 * @param name
	 * @param code
	 * @param shortName
	 * @param taxCode
	 * @param phone
	 * @param email
	 * @param sourceId
	 * @param classificationIds
	 * @param fieldIds
	 * @param typeId
	 * @param careerIds
	 * @param address
	 */
	@Builder
	public CustomerDto(Long id, String name, String code, String shortName, String taxCode, String phone, String email,
			Long sourceId, Set<Long> classificationIds, Set<Long> fieldIds, Long typeId, Set<Long> careerIds,
			String address) {
		super(id, name, code);
		this.shortName = shortName;
		this.taxCode = taxCode;
		this.phone = phone;
		this.email = email;
		this.sourceId = sourceId;
		this.classificationIds = classificationIds;
		this.fieldIds = fieldIds;
		this.typeId = typeId;
		this.careerIds = careerIds;
		this.address = address;
	}
	
}
