/**
 * 
 */
package capstone.repository;

import org.springframework.stereotype.Repository;

import capstone.entity.PhoneAreaCode;

/**
 * PhoneAreaCodeRepository
 * @author Tuna
 */
@Repository
public interface PhoneAreaCodeRepository
		extends NamedJpaRepository<PhoneAreaCode, Long>, BaseRepository<PhoneAreaCode, Long> {

}
