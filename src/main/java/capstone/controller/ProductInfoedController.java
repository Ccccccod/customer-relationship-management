/**
 * 
 */
package capstone.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

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
import capstone.entity.ProductInfo;
import capstone.exception.ResourceNotFoundException;
import capstone.model.ProductInfoed;
import capstone.model.Repositoried;
import capstone.repository.ProductInfoRepository;
import capstone.service.ProductInfoService;

/**
 * ProductInfoedController
 * @author Tuna
 *
 */
public interface ProductInfoedController<T extends ProductInfoed, //
		Repository extends JpaRepository<T, ID>, //
		ID extends Serializable> //
		extends Repositoried<Repository> {
	
	ProductInfoService getProductInfoService();
	
	Class<T> entityClass();
	
	ProductInfoRepository getProductInfoRepository();
	
	List<ProductInfo> findByProductInfoed(T t);
	
	Optional<ProductInfo> findByIdAndProductInfoed(Long id, T t);
	
	List<ProductInfo> deleteByIdAndProductInfoed(Iterable<? extends Long> ids, T t);

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
		T productInfoed = getRepository().findById(productInfoedId).orElseThrow(() -> new ResourceNotFoundException(
				entityClass().getName() + " not found for this id: " + productInfoedId));
		ProductInfo productInfo = getProductInfoService().generateFromProductInfoDto(productInfoDto);
		productInfoed.addToProductInfo(productInfo);
		getRepository().saveAndFlush(productInfoed);
		return ResponseEntity.ok(productInfo);
	}
	
	@PostMapping("{productInfoedId}/product/{productId}")
	default ResponseEntity<ProductInfo> createProductInfo(@PathVariable(value = "productInfoedId") ID productInfoedId,
			@PathVariable(value = "productId") Long productId) throws ResourceNotFoundException {
		T productInfoed = getRepository().findById(productInfoedId).orElseThrow(() -> new ResourceNotFoundException(
				entityClass().getName() + " not found for this id: " + productInfoedId));
		ProductInfo productInfo = getProductInfoService().generateFromProduct(productId);
		productInfoed.addToProductInfo(productInfo);
		getRepository().saveAndFlush(productInfoed);
		return ResponseEntity.ok(productInfo);
	}
	
	@PutMapping("{productInfoedId}/product/{productInfoId}")
	default ResponseEntity<ProductInfo> updateProductInfo(@PathVariable(value = "productInfoedId") ID productInfoedId,
			@PathVariable(value = "productInfoId") Long productInfoId, @RequestBody ProductInfoDto dto)
			throws ResourceNotFoundException {
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
		getProductInfoRepository().saveAndFlush(productInfo);

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
	@DeleteMapping("{productInfoedId}/product/{productInfofId}")
	default ResponseEntity<?> deleteProductInfo1(@PathVariable(value = "opportunityId") ID opportunityId,
			@PathVariable(value = "productInfoId") List<Long> ids) throws ResourceNotFoundException {
		return deleteProductInfo(opportunityId, ids);
	}

}
