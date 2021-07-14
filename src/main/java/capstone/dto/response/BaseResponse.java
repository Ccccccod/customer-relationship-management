/**
 * 
 */
package capstone.dto.response;

import java.io.Serializable;
import java.util.Date;

import capstone.entity.User;
import capstone.model.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Tuna
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<ID extends Serializable> implements Identifiable<ID>{
	
	private ID id;
	
	private Date createdAt;

	private Date updatedAt;
	
	private User createdBy;

	private User updatedBy;

}
