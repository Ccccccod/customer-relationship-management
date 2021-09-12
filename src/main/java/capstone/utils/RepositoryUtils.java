/**
 * 
 */
package capstone.utils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Id;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import capstone.exception.ResourceExistedException;
import capstone.model.Identifiable;

/**
 * RepositoryUtils
 * @author Tuna
 */
public final class RepositoryUtils {

	private RepositoryUtils() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}
	
	private static class FilterDisabler implements AutoCloseable {
		private final Supplier<Void> disabler;
		private FilterDisabler(Session session, String filterName) {
			super();
			this.disabler = () -> {
	        	if (session != null) {
	        		session.disableFilter(filterName);
	        		session.close();
	        	}
	        	return null;
	        };
		}
		public void disable() {
			disabler.get();
		}
		@Override
		public void close() throws Exception {
			disable();
		}
	}

	/**
	 * Enable a {@link Filter} with a parameter
	 * @param entityManager
	 * @param filterName
	 * @param parameter
	 * @param parameterValue
	 * @return
	 */
	public static FilterDisabler enableFilter(EntityManager entityManager, String filterName, String parameter,
			Object parameterValue) {
		Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter(filterName);
        filter.setParameter(parameter, parameterValue);
        return new FilterDisabler(session, filterName);
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
				.filter(field -> field.getAnnotation(Id.class) == null)
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
				throw new ResourceExistedException("An entity of " + clazz.getSimpleName() + " already exist",
						field.getName(), value);
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
				.filter(field -> field.getAnnotation(Id.class) == null)
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
				throw new ResourceExistedException("An entity of " + clazz.getSimpleName() + " already exist",
						field.getName(), value);
			}
		}
	}

	public static <T> void checkExistedFields(T entity, QueryByExampleExecutor<T> repository, Class<T> clazz)
			throws InstantiationException, IllegalAccessException, ResourceExistedException {
		// Get all unique fields
		List<Field> fields = FieldUtils.getFieldsListWithAnnotation(clazz, Column.class).stream()
				// Ignore id
				.filter(field -> field.getAnnotation(Id.class) == null)
				.collect(Collectors.toList());
		
		// Check if an entity already exist
		for (Field field : fields) {
			// Create new instance to make an Example to check
			T instance = clazz.newInstance();
			field.setAccessible(true);
			Object value = field.get(entity);
			// Allow null
			if (value == null)
				continue;
			field.set(instance, value);
			Example<T> example = Example.of(instance);
			// Query
			if (repository.exists(example)) {
				throw new ResourceExistedException("An entity already exist", field.getName(), value);
			}
		}
	}

}
