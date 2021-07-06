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
 * Coded Named Dto
 * @author Tuna
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class CodedNamedDto<ID extends Serializable> extends NamedDto<ID> {
	
	@NotNull(message = "Code must not be null")
	private String code;

	/**
	 * @param id
	 * @param name
	 * @param code
	 */
	public CodedNamedDto(ID id, @NotNull(message = "Name must not be null") String name,
			@NotNull(message = "Code must not be null") String code) {
		super(id, name);
		this.code = code;
	}
	
}
