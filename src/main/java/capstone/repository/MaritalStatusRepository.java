/**
 * 
 */
package capstone.repository;

import org.springframework.stereotype.Repository;

import capstone.entity.MaritalStatus;

/**
 * MaritalStatusRepository
 * @author Tuna
 */
@Repository
public interface MaritalStatusRepository extends NamedJpaRepository<MaritalStatus, Long> {

}
