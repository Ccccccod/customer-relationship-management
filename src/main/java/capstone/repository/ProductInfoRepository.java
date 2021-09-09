/**
 * 
 */
package capstone.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import capstone.entity.Invoice;
import capstone.entity.Opportunity;
import capstone.entity.Order;
import capstone.entity.ProductInfo;

/**
 * ProductInfoRepository
 * @author Tuna
 */
@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Long>, BaseRepository<ProductInfo, Long> {
	
	List<ProductInfo> findByOpportunity(Opportunity opportunity);
	
	Optional<ProductInfo> findByIdAndOpportunity(Long id, Opportunity opportunity);

	List<ProductInfo> deleteByIdInAndOpportunity(Iterable<? extends Long> ids, Opportunity opportunity);
	
	List<ProductInfo> findByOrder(Order order);
	
	Optional<ProductInfo> findByIdAndOrder(Long id, Order order);

	List<ProductInfo> deleteByIdInAndOrder(Iterable<? extends Long> ids, Order order);
	
	List<ProductInfo> findByInvoice(Invoice invoice);
	
	Optional<ProductInfo> findByIdAndInvoice(Long id, Invoice invoice);

	List<ProductInfo> deleteByIdInAndInvoice(Iterable<? extends Long> ids, Invoice invoice);
	
}
