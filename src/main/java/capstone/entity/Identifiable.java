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
public interface Identifiable<T extends Serializable> {

	/**
	 * getter for id
	 * @return
	 */
	T getId();
	
}
