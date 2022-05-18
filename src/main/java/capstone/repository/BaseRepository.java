/**
 * 
 */
package capstone.repository;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import capstone.entity.BaseEntity;

/**
 * BaseRepository
 * @author Tuna
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity<ID>, ID extends Serializable> extends JpaRepository<T, ID> {

	@Transactional
	default int softDeleteById(ID id) {
		return softDeleteById(Arrays.asList(id));
	}

	@Query("UPDATE #{#entityName} e SET e.deleted = true WHERE e.id IN ?1")
	@Modifying
	@Transactional
	int softDeleteById(Iterable<? extends ID> ids);

	@Transactional
	default int softDelete(T entity) {
		return softDeleteById(entity.getId());
	}

	@Transactional
	default int softDelete(Iterable<? extends T> entities) {
		List<ID> list = StreamSupport.stream(entities.spliterator(), false).map(T::getId).collect(Collectors.toList());
		return softDeleteById(list);
	}

	@Query("UPDATE #{#entityName} e SET e.deleted = true")
	@Transactional
	@Modifying
	int softDeleteAll();
	
	@Override
	@Transactional
	default void deleteAllById(Iterable<? extends ID> ids) {
		softDeleteById(ids);
	}
	
	@Override
	default void deleteById(ID id) {
		deleteAllById(Arrays.asList(id));
	}

}
