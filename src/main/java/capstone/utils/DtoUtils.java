/**
 * 
 */
package capstone.utils;

import java.io.Serializable;
import java.util.function.Supplier;

import capstone.exception.ResourceNotFoundException;
import capstone.model.Identifiable;

/**
 * @author Tuna
 *
 */
public class DtoUtils {

	/**
	 * Throw {@link ResourceNotFoundException}
	 * @param <ID> type of id
	 * @param id id used to find resource
	 * @throws ResourceNotFoundException {@link ResourceNotFoundException}
	 */
	public static final <ID extends Serializable> void throwResourceNotFoundException(ID id)
			throws ResourceNotFoundException {
		throw new ResourceNotFoundException("Entity not found for this id: " + id);
	}

	/**
	 * Throw {@link ResourceNotFoundException}
	 * @param <ID> type of id
	 * @param id id used to find resource
	 * @throws ResourceNotFoundException {@link ResourceNotFoundException}
	 */
	public static final <ID extends Serializable> Supplier<ResourceNotFoundException> resourceNotFoundExceptionSupplier(
			ID id) {
		return () -> new ResourceNotFoundException("Entity not found for this id: " + id);
	}

	/**
	 * Return {@link Supplier} that throw {@link ResourceNotFoundException}
	 * @param <T>
	 * @param <ID> type of id
	 * @param clazz &ltT&gt's class
	 * @param id id used to find resource
	 * @return {@link Supplier} that throw {@link ResourceNotFoundException}
	 */
	public static final <T extends Object & Identifiable<ID>, ID extends Serializable> //
	Supplier<ResourceNotFoundException> resourceNotFoundExceptionSupplier(Class<T> clazz, ID id) {
		return () -> new ResourceNotFoundException("Entity " + clazz.getSimpleName() + " not found for this id: " + id);
	}

}
