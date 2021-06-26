/**
 * 
 */
package capstone.dto.request;

import java.util.List;

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
public class CustomerDto extends BaseDto<Long> {
	
	@NotNull(message = "Name must not be null")
	private String name;
	
	private String shortName;

	@NotNull(message = "taxCode must not be null")
	private String taxCode;
	
	private String phone;

	@NotNull(message = "Email must not be null")
	@Email
	private String email;
	
	private Long sourceId;
	
	private List<Long> classificationIds;
	
	private List<Long> fieldIds;
	
	private Long typeId;

	private List<Long> careerIds;
	
	private String address;
	
}
