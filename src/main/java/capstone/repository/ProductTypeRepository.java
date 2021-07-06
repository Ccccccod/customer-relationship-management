/**
 * 
 */
package capstone.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import capstone.entity.ProductType;

/**
 * @author Tuna
 *
 */
@Repository
public interface ProductTypeRepository extends NamedJpaRepository<ProductType, Long>{
	
	List<ProductType> findByProductType(ProductType productType);

}
