/**
 * 
 */
package capstone.repository;

import org.springframework.stereotype.Repository;

import capstone.entity.BusinessType;

/**
 * BusinessTypeRepository
 * @author Tuna
 */
@Repository
public interface BusinessTypeRepository
		extends NamedJpaRepository<BusinessType, Long>, BaseRepository<BusinessType, Long> {

}
