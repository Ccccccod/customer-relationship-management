/**
 * 
 */
package capstone.repository;

import java.util.List;

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

}
