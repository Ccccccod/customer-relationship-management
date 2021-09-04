/**
 * 
 */
package capstone.repository;

import org.springframework.stereotype.Repository;

import capstone.entity.Gender;

/**
 * GenderRepository
 * @author Tuna
 */
@Repository
public interface GenderRepository extends NamedJpaRepository<Gender, Long> {

}
