/**
 * 
 */
package capstone.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import capstone.entity.ProductType;

/**
 * ProductTypeRepository
 * @author Tuna
 */
@Repository
public interface ProductTypeRepository
		extends NamedJpaRepository<ProductType, Long>, BaseRepository<ProductType, Long> {
	
	List<ProductType> findByProductType(ProductType productType);

}
