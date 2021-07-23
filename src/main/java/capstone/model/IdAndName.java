/**
 * 
 */
package capstone.model;

import java.io.Serializable;

/**
 * IdAndName
 * @author Tuna
 *
 */
public interface IdAndName<ID extends Serializable> extends Identifiable<ID>, Named {

}
