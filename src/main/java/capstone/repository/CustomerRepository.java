/**
 * 
 */
package capstone.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import capstone.entity.Customer;
import capstone.model.IdAndName;
import capstone.model.IdNameEmailPhoneTaxCode;

/**
 * Repository for {@link Customer}
 * @author Tuna
 */
@Repository
public interface CustomerRepository
		extends NamedJpaRepository<Customer, Long>, BaseRepository<Customer, Long>, CodedRepository<Customer, Long> {

	List<IdAndName<Long>> findByNameIgnoreCase(String name);
	
	List<IdNameEmailPhoneTaxCode<Long>> findIdNameEmailPhoneAllBy();
	
	Boolean existsByTaxCode(String taxCode);
	
	Boolean existsByEmail(String email);

}
