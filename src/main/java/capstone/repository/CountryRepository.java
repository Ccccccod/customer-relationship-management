/**
 * 
 */
package capstone.repository;

import org.springframework.stereotype.Repository;

import capstone.entity.Country;

/**
 * CountryRepository
 * @author Tuna
 */
@Repository
public interface CountryRepository extends NamedJpaRepository<Country, Long> {

}
