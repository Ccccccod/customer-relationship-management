/**
 * 
 */
package capstone.entity;

import java.io.Serializable;

import capstone.utils.StringSequenceIdentifierGenerator;

/**
 * Identifiable
 * For {@link StringSequenceIdentifierGenerator} and for {@code class} that has {@link String} id.
 * @author Tuna
 *
 */
public interface Identifiable<ID extends Serializable> {

	/**
	 * getter for id
	 * @return
	 */
	ID getId();
	
}
