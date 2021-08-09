/**
 * 
 */
package capstone.repository;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.Column;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import capstone.exception.ResourceExistedException;
import capstone.model.Identifiable;

/**
 * RepositoryUtils
 * @author Tuna
 *
 */
public class RepositoryUtils {

	/**
	 * This is a utility class and cannot be instantiated
	 */
	private RepositoryUtils() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}
	
	/**
	 * Check if an entity has any fields that will violate duplicate constraint if it's saved
	 * @param <T> the entity's type 
	 * @param <ID> the entity's id's type
	 * @param entity the entity that needs to be checked
	 * @param repository repository to be used to query
	 * @param clazz entity's class
	 * @throws ResourceExistedException when the entity will violate duplicate constraint if it's saved 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static <T extends Identifiable<ID>, ID extends Serializable> void checkExistedFields(T entity,
			JpaRepository<T, ID> repository, Class<T> clazz)
			throws ResourceExistedException, InstantiationException, IllegalAccessException {
		// Get all unique fields
		List<Field> fields = new LinkedList<>();
		for (Class<?> class1 = entity.getClass(); !Objects.isNull(class1); class1 = class1.getSuperclass()) {
			fields.addAll(Arrays.asList(class1.getDeclaredFields()));
		}
		// Remove fields that don't need to be unique
		fields = fields.stream()
				.filter(Objects::nonNull)
				// Ignore id
				.filter(field -> !field.getName().toLowerCase().equals("id"))
				.filter(f -> Arrays.stream(f.getDeclaredAnnotationsByType(Column.class)).anyMatch(Column::unique))
				.collect(Collectors.toList());
		
		// Check if an entity already exist
		for (Field field : fields) {
			// Create new instance to make an Example to check
			T instance = clazz.newInstance();
			field.setAccessible(true);
			Object value = field.get(entity);
			field.set(instance, value);
			Example<T> example = Example.of(instance);
			// Query
			if (repository.exists(example)) {
				throw new ResourceExistedException("An entity already exist", field.getName(), value);
			}
		}
	}
	
	/**
	 * Check if an entity has any fields that will violate duplicate constraint if it's updated
	 * @param <T> the entity's type 
	 * @param <ID> the entity's id's type
	 * @param entity the entity that needs to be checked
	 * @param id id of the entity
	 * @param repository repository to be used to query
	 * @param clazz entity's class
	 * @throws ResourceExistedException when the entity will violate duplicate constraint if it's saved 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static <T extends Identifiable<ID>, ID extends Serializable> void checkExistedFields(T entity, ID id,
			JpaRepository<T, ID> repository, Class<T> clazz)
			throws ResourceExistedException, InstantiationException, IllegalAccessException {
		// Get all unique fields
		List<Field> fields = new LinkedList<>();
		for (Class<?> class1 = entity.getClass(); !Objects.isNull(class1); class1 = class1.getSuperclass()) {
			fields.addAll(Arrays.asList(class1.getDeclaredFields()));
		}
		// Remove fields that don't need to be unique
		fields = fields.stream()
				.filter(Objects::nonNull)
				// Ignore id
				.filter(field -> !field.getName().toLowerCase().equals("id"))
				.filter(f -> Arrays.stream(f.getDeclaredAnnotationsByType(Column.class)).anyMatch(Column::unique))
				.collect(Collectors.toList());
		
		// Check if an entity already exist
		for (Field field : fields) {
			// Create new instance to make an Example to check
			T instance = clazz.newInstance();
			field.setAccessible(true);
			Object value = field.get(entity);
			field.set(instance, value);
			instance.setId(id);
			// if the updating entity's field has the value, it's ok 
			Example<T> example = Example.of(instance);
			// Query
			if (repository.exists(example)) {
				continue;
			}
			// else if other entity's field has the value, it is not ok
			instance.setId(null);
			example = Example.of(instance);
			if (repository.exists(example)) {
				throw new ResourceExistedException("An entity already exist", field.getName(), value);
			}
		}
	}

}
