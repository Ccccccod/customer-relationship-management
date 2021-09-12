/**
 * 
 */
package capstone.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import capstone.entity.District;
import capstone.entity.Ward;

/**
 * WardRepository
 * @author Tuna
 */
@Repository
public interface WardRepository extends NamedJpaRepository<Ward, Long> {
	
	List<Ward> findByDistrict(District district);

}
