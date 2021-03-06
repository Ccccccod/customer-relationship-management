/**
 * 
 */
package capstone.dto.request;

import java.io.Serializable;

import capstone.model.Identifiable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * BaseDto
 * @author Tuna
 */
@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class BaseDto<ID extends Serializable> implements Identifiable<ID> {
	
	protected ID id;

}
