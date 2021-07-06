/**
 * 
 */
package capstone.dto.request;

import java.util.Set;

import javax.validation.constraints.NotNull;

import capstone.dto.validatation.Email;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Tuna
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class CustomerDto extends CodedNamedDto<Long> {
	
	private String shortName;

	@NotNull(message = "taxCode must not be null")
	private String taxCode;
	
	private String phone;

	@NotNull(message = "Email must not be null")
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
	
}
