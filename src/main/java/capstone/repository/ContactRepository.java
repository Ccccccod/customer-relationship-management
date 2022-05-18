/**
 * 
 */
package capstone.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import capstone.entity.Contact;
import capstone.entity.Customer;

/**
 * Contact Repository
 * @author Tuna
 */
@Repository
public interface ContactRepository
		extends NamedJpaRepository<Contact, Long>, BaseRepository<Contact, Long>, CodedRepository<Contact, Long> {
	
	List<Contact> findByCustomer(Customer customer);

}
