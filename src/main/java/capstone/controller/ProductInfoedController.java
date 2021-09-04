/**
 * 
 */
package capstone.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import capstone.dto.request.ProductInfoDto;
import capstone.entity.BaseEntity;
import capstone.entity.ProductInfo;
import capstone.exception.ResourceNotFoundException;
import capstone.model.ProductInfoed;
import capstone.model.Repositoried;
import capstone.repository.ProductInfoRepository;
import capstone.service.ProductInfoService;
import capstone.service.UserService;

/**
 * ProductInfoedController
 * @author Tuna
 *
 */
public interface ProductInfoedController<T extends BaseEntity<ID> & ProductInfoed, //
		Repository extends JpaRepository<T, ID>, //
		ID extends Serializable> //
		extends Repositoried<Repository> {

	/**
	 * @return the logger
	 */
	Logger getLogger();
	
	/**
	 * @return autowired {@link ProductInfoService}
	 */
	ProductInfoService getProductInfoService();
	
	/**
	 * @return {@link Class} of T
	 */
	Class<T> entityClass();
	
	/**
	 * @return autowired {@link ProductInfoRepository}
	 */
	ProductInfoRepository getProductInfoRepository();
	
	/**
	 * Call {@link ProductInfoRepository} to find by t
	 * @param t t to find by
	 * @return results
	 */
	List<ProductInfo> findByProductInfoed(T t);
	
	/**
	 * Call {@link ProductInfoRepository} to find by id and t
	 * @param id
	 * @param t
	 * @return
	 */
	Optional<ProductInfo> findByIdAndProductInfoed(Long id, T t);
	
	List<ProductInfo> deleteByIdAndProductInfoed(Iterable<? extends Long> ids, T t);
	
	UserService getUserService();

	@GetMapping("{productInfoedId}/product")
	default ResponseEntity<List<ProductInfo>> getAllProductInfo(@PathVariable(value = "productInfoedId") ID productInfoedId)
			throws ResourceNotFoundException {
		T productInfoed = getRepository().findById(productInfoedId).orElseThrow(() -> new ResourceNotFoundException(
				entityClass().getName() + " not found for this id: " + productInfoedId));
		List<ProductInfo> productInfos = findByProductInfoed(productInfoed);
		return ResponseEntity.ok(productInfos);
	}

	@GetMapping("{productInfoedId}/product/{productInfoId}")
	default ResponseEntity<ProductInfo> getProductInfo(@PathVariable(value = "productInfoedId") ID productInfoedId,
			@PathVariable(value = "productInfoId") Long productInfoId) throws ResourceNotFoundException {
		T productInfoed = getRepository().findById(productInfoedId).orElseThrow(() -> new ResourceNotFoundException(
				entityClass().getName() + " not found for this id: " + productInfoedId));
		ProductInfo productInfo = findByIdAndProductInfoed(productInfoId, productInfoed).orElseThrow(() -> new ResourceNotFoundException(
				"ProductInfo not found for this id: " + productInfoId + " and this OpportunityId: " + productInfoedId));
		return ResponseEntity.ok(productInfo);
	}

	@PostMapping("{productInfoedId}/product")
	default ResponseEntity<ProductInfo> createProductInfo(@PathVariable(value = "productInfoedId") ID productInfoedId,
			@RequestBody ProductInfoDto productInfoDto) throws ResourceNotFoundException {
		getLogger().debug("createProductInfo() with body {} of type {}", productInfoDto, productInfoDto.getClass());
		
		T productInfoed = getRepository().findById(productInfoedId).orElseThrow(() -> new ResourceNotFoundException(
				entityClass().getName() + " not found for this id: " + productInfoedId));
		ProductInfo productInfo = getProductInfoService().generateFromProductInfoDto(productInfoDto);
		productInfoed.addToProductInfo(productInfo);
		
		productInfoed.setUpdatedBy(this.getUserService().getCurrentUser());
		productInfo.setCreatedBy(this.getUserService().getCurrentUser());
		getRepository().saveAndFlush(productInfoed);
		
		return ResponseEntity.ok(productInfo);
	}
	
	@PostMapping("{productInfoedId}/product/{productId}")
	default ResponseEntity<ProductInfo> createProductInfo(@PathVariable(value = "productInfoedId") ID productInfoedId,
			@PathVariable(value = "productId") Long productId) throws ResourceNotFoundException {
		getLogger().debug("createProductInfo() with productInfoId#{}", productId);
		
		T productInfoed = getRepository().findById(productInfoedId).orElseThrow(() -> new ResourceNotFoundException(
				entityClass().getName() + " not found for this id: " + productInfoedId));
		ProductInfo productInfo = getProductInfoService().generateFromProduct(productId);
		productInfoed.addToProductInfo(productInfo);

		productInfoed.setUpdatedBy(this.getUserService().getCurrentUser());
		productInfo.setCreatedBy(this.getUserService().getCurrentUser());
		productInfo = getProductInfoRepository().save(productInfo);
		getRepository().saveAndFlush(productInfoed);
		getProductInfoRepository().flush();
		
		return ResponseEntity.ok(productInfo);
	}
	
	@PutMapping("{productInfoedId}/product/{productInfoId}")
	default ResponseEntity<ProductInfo> updateProductInfo(@PathVariable(value = "productInfoedId") ID productInfoedId,
			@PathVariable(value = "productInfoId") Long productInfoId, @RequestBody ProductInfoDto dto)
			throws ResourceNotFoundException {
		getLogger().debug("updateProductInfo() of id#{}, productInfoedId ({}) #{} with body {} of type {}",
				productInfoId, productInfoedId.getClass(), productInfoedId, dto, dto.getClass());
		
		T productInfoed = getRepository().findById(productInfoedId).orElseThrow(() -> new ResourceNotFoundException(
				entityClass().getName() + " not found for this id: " + productInfoedId));
		ProductInfo productInfo = findByIdAndProductInfoed(productInfoId, productInfoed)
				.orElseThrow(() -> new ResourceNotFoundException("ProductInfo not found for this id: " + productInfoId
						+ " and this " + entityClass().getName() + "Id: " + productInfoedId));

		// Update
		// Code can not be updated
		productInfo.setExplanation(dto.getExplanation());
		productInfo.setUnit(dto.getUnit());
		productInfo.setAmount(dto.getAmount());
		productInfo.setPrice(dto.getPrice());
		productInfo.setDiscount(dto.getDiscount());
		productInfo.setVat(dto.getVat());

		productInfoed.setUpdatedBy(this.getUserService().getCurrentUser());
		productInfo.setUpdatedBy(this.getUserService().getCurrentUser());
		getProductInfoRepository().saveAndFlush(productInfo);
		getLogger().debug("updated enitity: {}", productInfo);

		return ResponseEntity.ok(productInfo);
	}
	
	@Transactional
	@DeleteMapping("{productInfoedId}/product")
	default ResponseEntity<?> deleteProductInfo(@PathVariable(value = "productInfoedId") ID productInfoedId,
			@RequestBody List<Long> ids) throws ResourceNotFoundException {
		T productInfoed = getRepository().findById(productInfoedId).orElseThrow(() -> new ResourceNotFoundException(
				entityClass().getName() + " not found for this id: " + productInfoedId));
		List<ProductInfo> response = deleteByIdAndProductInfoed(ids, productInfoed);
		return ResponseEntity.ok(response);
	}

	@Transactional
	@DeleteMapping("{productInfoedId}/product/{productInfoId}")
	default ResponseEntity<?> deleteProductInfo1(@PathVariable(value = "productInfoedId") ID productInfoedId,
			@PathVariable(value = "productInfoId") List<Long> ids) throws ResourceNotFoundException {
		return deleteProductInfo(productInfoedId, ids);
	}

}
