/**
 * 
 */
package capstone.repository;

import org.springframework.stereotype.Repository;

import capstone.entity.District;

/**
 * DistrictRepository
 * @author Tuna
 */
@Repository
public interface DistrictRepository extends NamedJpaRepository<District, Long> {

}
