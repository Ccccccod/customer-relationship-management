/**
 * 
 */
package capstone.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import capstone.entity.Order;

/**
 * Order Repository
 * @author Tuna
 *
 */
@Repository
public interface OrderRepository extends NamedJpaRepository<Order, Long> {
	
	List<Order> findByOrderDateBetween(LocalDate from, LocalDate to);

}
