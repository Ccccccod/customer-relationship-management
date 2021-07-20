/**
 * 
 */
package capstone.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import capstone.entity.Opportunity;
import capstone.entity.ProductInfo;

/**
 * @author Tuna
 *
 */
@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Long> {
	
	List<ProductInfo> findByOpportunity(Opportunity opportunity);
	
	Optional<ProductInfo> findByIdAndOpportunity(Long id, Opportunity opportunity);
	
	void deleteByIdAndOpportunity(Long id, Opportunity opportunity);

	List<ProductInfo> deleteByIdInAndOpportunity(Iterable<? extends Long> ids, Opportunity opportunity);
	
}
