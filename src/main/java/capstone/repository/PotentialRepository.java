/**
 * 
 */
package capstone.repository;

import org.springframework.stereotype.Repository;

import capstone.entity.Potential;

/**
 * PotentialRepository
 * @author Tuna
 */
@Repository
public interface PotentialRepository extends NamedJpaRepository<Potential, Long>, BaseRepository<Potential, Long>,
		CodedRepository<Potential, Long> {

}
