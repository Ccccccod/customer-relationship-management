/**
 * 
 */
package capstone.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import capstone.entity.Order;

/**
 * Order Repository
 * @author Tuna
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	List<Order> findByOrderDateBetween(LocalDate from, LocalDate to);

}
