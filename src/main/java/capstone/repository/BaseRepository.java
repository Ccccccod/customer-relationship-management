/**
 * 
 */
package capstone.repository;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringJoiner;

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

	@Query("UPDATE #{#entityName} e SET e.deleted = true WHERE e.id IN ?1")
	@Modifying
	@Transactional
	int softDeleteById(String ids);

	@Transactional
	default int softDeleteById(ID id) {
		return softDeleteById(Arrays.asList(id));
	}

	@Transactional
	default int softDeleteById(Iterable<? extends ID> ids) {
		StringJoiner stringJoiner = new StringJoiner(",", "(", ")");
		ids.forEach(id -> stringJoiner.add(id.toString()));
		return softDeleteById(stringJoiner.toString());
	}

	@Transactional
	default int softDelete(T entity) {
		return softDeleteById(entity.getId());
	}

	@Transactional
	default int softDelete(Iterable<? extends T> entities) {
		LinkedList<ID> list = new LinkedList<ID>();
		entities.forEach(entity -> list.add(entity.getId()));
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

}
