/**
 * 
 */
package capstone.model;

import java.io.Serializable;

/**
 * IdAndName
 * @author Tuna
 */
public interface IdAndName<ID extends Serializable> extends Identifiable<ID>, Named {

	static <ID extends Serializable> IdAndName<ID> newInstance(ID id, String name) {
		return new IdAndName<ID>() {

			@Override
			public ID getId() {
				return id;
			}

			@Override
			public String getName() {
				return name;
			}
		};
	}

}
