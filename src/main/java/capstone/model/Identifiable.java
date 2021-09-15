/**
 * 
 */
package capstone.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import capstone.utils.StringSequenceIdentifierGenerator;

/**
 * Identifiable
 * For {@link StringSequenceIdentifierGenerator} and for {@code class} that has {@link String} id.
 * @author Tuna
 */
public interface Identifiable<ID extends Serializable> {

	/**
	 * getter for id
	 * @return
	 */
	ID getId();
	
	/**
	 * Setter for id
	 * 
	 * @param id
	 */
	default void setId(ID id) {
	};
	
	@JsonIgnore
	default	boolean isNew() {
		return this.getId() == null;
	}

	static <ID extends Serializable> Identifiable<ID> newInstance(ID id) {
		return () -> id;
	}
	
}
