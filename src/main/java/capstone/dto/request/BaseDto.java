/**
 * 
 */
package capstone.dto.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import capstone.model.Identifiable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * BaseDto
 * @author Tuna
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class BaseDto<ID extends Serializable> implements Identifiable<ID> {
	
	@JsonIgnore
	protected ID id;

}
