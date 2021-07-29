/**
 * 
 */
package capstone.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Named DTO
 * @author Tuna
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class NamedDto<ID extends Serializable> extends BaseDto<ID> {
	
	@NotBlank
	private String name;

	/**
	 * @param id
	 * @param name
	 */
	public NamedDto(ID id, String name) {
		super(id);
		this.name = name;
	}

}
