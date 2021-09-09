/**
 * 
 */
package capstone.repository;

import org.springframework.stereotype.Repository;

import capstone.entity.Position;

/**
 * PositionRepository
 * @author Tuna
 */
@Repository
public interface PositionRepository extends NamedJpaRepository<Position, Long>, BaseRepository<Position, Long> {

}
