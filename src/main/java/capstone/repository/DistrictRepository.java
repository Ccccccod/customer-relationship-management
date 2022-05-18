/**
 * 
 */
package capstone.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import capstone.entity.District;
import capstone.entity.Province;

/**
 * DistrictRepository
 * @author Tuna
 */
@Repository
public interface DistrictRepository extends NamedJpaRepository<District, Long> {
	
	List<District> findByProvince(Province province);

}
