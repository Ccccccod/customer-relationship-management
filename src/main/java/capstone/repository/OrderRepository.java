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
	
//	@Override
//	default Boolean existsByName(String name) {
//		return existsByExplanation(name);
//	}
//	
//	@Override
//	default Optional<Order> findFirstByName(String name) {
//		return this.findFirstByExplanation(name);
//	}
//	
//	@Override
//	default List<Order> findByName(String name) {
//		return findByExplanation(name);
//	}
//	
//	@Override
//	default List<IdAndName<Long>> findIdNameAllBy() {
//		return this.findIdNameAllBy();
//	}
//	
//	Boolean existsByExplanation(String explanation);
//	
//	Optional<Order> findFirstByExplanation(String explanation);
//	
//	List<Order> findByExplanation(String explanation);
//	
//	List<IdAndName<Long>> findIdExplanationAllBy();

}
