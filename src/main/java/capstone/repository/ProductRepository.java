/**
 * 
 */
package capstone.repository;

import org.springframework.stereotype.Repository;

import capstone.entity.Product;

/**
 * Product Repository
 * @author Tuna
 */
@Repository
public interface ProductRepository extends NamedJpaRepository<Product, Long>, BaseRepository<Product, Long> {
	
}
