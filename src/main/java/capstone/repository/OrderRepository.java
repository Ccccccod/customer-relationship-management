/**
 * 
 */
package capstone.repository;

import org.springframework.stereotype.Repository;

import capstone.entity.Order;

/**
 * Order Repository
 * @author Tuna
 *
 */
@Repository
public interface OrderRepository extends NamedJpaRepository<Order, Long>{

}
