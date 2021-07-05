/**
 * 
 */
package capstone.dto.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import capstone.entity.Identifiable;
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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public abstract class BaseDto<ID extends Serializable> implements Identifiable<ID> {
	
	private ID id;
	
	@JsonIgnore
	public boolean isNew() {
		return this.id == null;
	}

}
