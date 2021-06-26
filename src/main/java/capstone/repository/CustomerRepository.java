/**
 * 
 */
package capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import capstone.entity.Customer;

/**
 * Repository for {@link Customer}
 * 
 * @author Tuna
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Boolean existsByTaxCode(String taxCode);
	
	Boolean existsByEmail(String email);

}
