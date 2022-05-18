/**
 * 
 */
package capstone.utils;

import java.io.Serializable;
import java.util.function.Supplier;

import capstone.exception.ResourceNotFoundException;
import capstone.model.Identifiable;

/**
 * DtoUtils
 * @author Tuna
 */
public class DtoUtils {

	/**
	 * Throw {@link ResourceNotFoundException}
	 * @param <ID> type of id
	 * @param id id used to find resource
	 * @throws ResourceNotFoundException {@link ResourceNotFoundException}
	 */
	public static final <ID extends Serializable> void throwResourceNotFoundException(ID id, String entityName)
			throws ResourceNotFoundException {
		throw new ResourceNotFoundException("Entity not found for this id: " + id, entityName);
	}

	/**
	 * Throw {@link ResourceNotFoundException}
	 * @param <ID> type of id
	 * @param id id used to find resource
	 * @throws ResourceNotFoundException {@link ResourceNotFoundException}
	 */
	public static final <T, ID extends Serializable> Supplier<ResourceNotFoundException> resourceNotFoundExceptionSupplier(
			ID id, Class<T> cls) {
		return () -> new ResourceNotFoundException("Entity not found for this id: " + id, cls.getSimpleName());
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
		return () -> new ResourceNotFoundException("Entity not found for this id: " + id, clazz.getSimpleName());
	}

}
