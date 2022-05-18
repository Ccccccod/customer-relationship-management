/**
 * 
 */
package capstone.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import capstone.entity.Country;
import capstone.entity.Province;

/**
 * ProvinceRepository
 * @author Tuna
 */
@Repository
public interface ProvinceRepository extends NamedJpaRepository<Province, Long> {
	
	List<Province> findByCountry(Country country);

}
