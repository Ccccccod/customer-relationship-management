/**
 * 
 */
package capstone.repository;

import java.util.List;
<<<<<<< HEAD
import java.util.Optional;
=======
>>>>>>> d86532243c5141ae5ae782a22357ea8ae0e6b4c3

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
<<<<<<< HEAD
	
	Optional<ProductInfo> findByIdAndOpportunity(Long id, Opportunity opportunity);
	
	void deleteByIdAndOpportunity(Long id, Opportunity opportunity);

	List<ProductInfo> deleteByIdInAndOpportunity(Iterable<? extends Long> ids, Opportunity opportunity);
	
=======

>>>>>>> d86532243c5141ae5ae782a22357ea8ae0e6b4c3
}
