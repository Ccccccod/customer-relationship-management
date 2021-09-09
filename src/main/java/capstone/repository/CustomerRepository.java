/**
 * 
 */
package capstone.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import capstone.entity.Customer;

/**
 * Repository for {@link Customer}
 * @author Tuna
 */
@Repository
public interface CustomerRepository extends NamedJpaRepository<Customer, Long>, BaseRepository<Customer, Long> {
	
	List<Customer> findByNameIgnoreCase(String name);
	
	Boolean existsByTaxCode(String taxCode);
	
	Boolean existsByEmail(String email);

}
