/**
 * 
 */
package capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import capstone.entity.Career;

/**
 * Repository for {@link Career}
 * @author Tuna
 *
 */
@Repository
public interface CareerRepository extends JpaRepository<Career, Long>{

}
