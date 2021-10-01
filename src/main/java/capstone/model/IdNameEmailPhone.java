/**
 * 
 */
package capstone.model;

import java.io.Serializable;

/**
 * IdNameEmailPhone
 * @author Tuna
 */
public interface IdNameEmailPhone<ID extends Serializable> extends IdAndName<ID> {

	String getEmail();
	
	String getPhone();
	
}
