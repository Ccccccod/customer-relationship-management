/**
 * 
 */
package capstone.dto.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Coded Named Dto
 * @author Tuna
 *
 */
@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class CodedNamedDto<ID extends Serializable> extends NamedDto<ID> {
	
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
