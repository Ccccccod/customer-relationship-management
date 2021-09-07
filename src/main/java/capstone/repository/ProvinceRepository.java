/**
 * 
 */
package capstone.repository;

import org.springframework.stereotype.Repository;

import capstone.entity.Province;

/**
 * ProvinceRepository
 * @author Tuna
 */
@Repository
public interface ProvinceRepository extends NamedJpaRepository<Province, Long> {

}
