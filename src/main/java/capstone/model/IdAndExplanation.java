/**
 * 
 */
package capstone.model;

import java.io.Serializable;

/**
 * IdAndExplanation
 * @author Tuna
 */
public interface IdAndExplanation<ID extends Serializable> extends Identifiable<ID> {
	
	String getExplanation();

}
