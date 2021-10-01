/**
 * 
 */
package capstone.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import capstone.entity.Order;
import capstone.model.IdAndExplanation;
import capstone.model.IdNameEmailPhone;

/**
 * Order Repository
 * @author Tuna
 */
@Repository
public interface OrderRepository
		extends JpaRepository<Order, Long>, BaseRepository<Order, Long>, CodedRepository<Order, Long> {

	List<Order> findByOrderDateBetween(LocalDate from, LocalDate to);
	
	List<IdAndExplanation<Long>> findIdExplanationAllBy();
	
	Optional<CustomerOnly> findCustomerIdAndNameAndEmailAndPhoneById(Long id);

	public interface CustomerOnly {
		IdNameEmailPhone<Long> getCustomer();
	}
	
	Optional<ContactOnly> findContactIdAndNameAndEmailAndPhoneById(Long id);
	
	public interface ContactOnly {
		IdNameEmailPhone<Long> getContact();
	}

}
