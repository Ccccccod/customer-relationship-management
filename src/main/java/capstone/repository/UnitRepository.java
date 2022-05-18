/**
 * 
 */
package capstone.repository;

import org.springframework.stereotype.Repository;

import capstone.entity.Unit;

/**
 * UnitRepository
 * @author Tuna
 */
@Repository
public interface UnitRepository extends NamedJpaRepository<Unit, Long>, BaseRepository<Unit, Long> {

}
