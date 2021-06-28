/**
 * 
 */
package capstone.repository;

import org.springframework.stereotype.Repository;

import capstone.entity.Contact;

/**
 * Contact Repository
 * @author Tuna
 *
 */
@Repository
public interface ContactRepository extends NamedJpaRepository<Contact, Long>{

}
