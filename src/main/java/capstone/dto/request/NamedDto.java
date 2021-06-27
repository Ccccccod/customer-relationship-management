/**
 * 
 */
package capstone.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

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
	
	@NotNull(message = "Name must not be null")
	private String name;

	/**
	 * @param id
	 */
	public NamedDto(ID id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

}
