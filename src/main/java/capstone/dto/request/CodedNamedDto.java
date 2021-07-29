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
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class CodedNamedDto<ID extends Serializable> extends NamedDto<ID> {
	
	@NotNull
	private String code;

	/**
	 * @param id
	 * @param name
	 * @param code
	 */
	public CodedNamedDto(ID id, String name,
			String code) {
		super(id, name);
		this.code = code;
	}
	
}
