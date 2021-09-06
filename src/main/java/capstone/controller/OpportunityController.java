/**
 * 
 */
package capstone.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.OpportunityDto;
import capstone.entity.Opportunity;
import capstone.entity.ProductInfo;
import capstone.repository.ContactRepository;
import capstone.repository.CustomerRepository;
import capstone.repository.OpportunityRepository;
import capstone.repository.ProductInfoRepository;
import capstone.repository.SourceRepository;
import capstone.service.OpportunityService;
import capstone.service.ProductInfoService;
import capstone.service.UserService;
import lombok.Getter;

/**
 * OpportunityController
 * Cơ hội Controller
 * @author Tuna
 */
@RestController
@RequestMapping("/api/opportunity")
public class OpportunityController
		extends CRUDController<OpportunityDto, OpportunityDto, Opportunity, Opportunity, OpportunityRepository, OpportunityService, Long>
		implements IReadNameController<Opportunity, OpportunityService, Long>,
		ProductInfoedController<Opportunity, OpportunityRepository, Long> {

	@Autowired
	protected ProductInfoRepository productInfoRepository;
	
	@Autowired
	protected CustomerRepository customerRepository;

	@Autowired
	protected ContactRepository contactRepository;
	
	@Autowired
	protected SourceRepository sourceRepository;
	
	@Autowired
	protected ProductInfoService productInfoService;
	
	@Autowired
	protected UserService userService;

	@Override
	public ProductInfoService getProductInfoService() {
		return this.productInfoService;
	}

	@Override
	public Class<Opportunity> entityClass() {
		return Opportunity.class;
	}

	@Override
	public ProductInfoRepository getProductInfoRepository() {
		return this.productInfoRepository;
	}

	@Override
	public List<ProductInfo> findByProductInfoed(Opportunity t) {
		return this.productInfoRepository.findByOpportunity(t);
	}

	@Override
	public Optional<ProductInfo> findByIdAndProductInfoed(Long id, Opportunity t) {
		return this.productInfoRepository.findByIdAndOpportunity(id, t);
	}

	@Override
	public List<ProductInfo> deleteByIdAndProductInfoed(Iterable<? extends Long> ids, Opportunity t) {
		return this.productInfoRepository.deleteByIdInAndOpportunity(ids, t);
	}
	
	@Autowired
	protected OpportunityRepository opportunityRepository;

	@Override
	public OpportunityRepository getRepository() {
		return opportunityRepository;
	}
	
	@Getter
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public UserService getUserService() {
		return userService;
	}

	@Autowired
	private OpportunityService opportunityService;
	@Override
	public OpportunityService getService() {
		return opportunityService;
	}
	
//	@GetMapping("{opportunityId}/product")
//	public ResponseEntity<List<ProductInfo>> getAllProductInfo(
//			@PathVariable(value = "opportunityId") Long opportunityId) throws ResourceNotFoundException {
//		Opportunity opportunity = this.repository.findById(opportunityId).orElseThrow(
//				() -> new ResourceNotFoundException("Opportunity not found for this id: " + opportunityId));
//		return ResponseEntity.ok(this.productInfoRepository.findByOpportunity(opportunity));
//	}
//
//	@GetMapping("{opportunityId}/product/{productInfoId}")
//	public ResponseEntity<ProductInfo> getProductInfo(@PathVariable(value = "opportunityId") Long opportunityId,
//			@PathVariable(value = "productInfoId") Long productInfoId) throws ResourceNotFoundException {
//		Opportunity opportunity = this.repository.findById(opportunityId).orElseThrow(
//				() -> new ResourceNotFoundException("Opportunity not found for this id: " + opportunityId));
//		return ResponseEntity.ok(this.productInfoRepository.findByIdAndOpportunity(productInfoId, opportunity)
//				.orElseThrow(() -> new ResourceNotFoundException("ProductInfo not found for this id: " + productInfoId
//						+ " and this OpportunityId: " + opportunityId)));
//	}
//	
//	@PostMapping("{opportunityId}/product")
//	public ResponseEntity<ProductInfo> createProductInfo(@PathVariable(value = "opportunityId") Long opportunityId,
//			@RequestBody ProductInfoDto productInfoDto) throws ResourceNotFoundException {
//		Opportunity opportunity = this.repository.findById(opportunityId).orElseThrow(
//				() -> new ResourceNotFoundException("Opportunity not found for this id: " + opportunityId));
//		ProductInfo productInfo = this.productInfoService.generateFromProductInfoDto(productInfoDto);
//		opportunity.addToProductInfo(productInfo);
//		opportunity = this.repository.saveAndFlush(opportunity);
//		
//		return ResponseEntity.ok(productInfo);
//	}
//
//	@PostMapping("{opportunityId}/product/{productId}")
//	public ResponseEntity<ProductInfo> createProductInfo(@PathVariable(value = "opportunityId") Long opportunityId,
//			@PathVariable(value = "productId") Long productId) throws ResourceNotFoundException {
//		ProductInfo productInfo = this.productInfoService.create(opportunityId, repository, Opportunity.class, productId);
//		return ResponseEntity.ok(productInfo);
//	}
//	
//	@PutMapping("{opportunityId}/product/{productInfoId}")
//	public ResponseEntity<ProductInfo> updateProductInfo(@PathVariable(value = "opportunityId") Long opportunityId,
//			@PathVariable(value = "productInfoId") Long productInfoId, @RequestBody ProductInfoDto dto)
//			throws ResourceNotFoundException {
//		Opportunity opportunity = this.repository.findById(opportunityId).orElseThrow(
//				() -> new ResourceNotFoundException("Opportunity not found for this id: " + opportunityId));
//		ProductInfo productInfo = this.productInfoRepository.findByIdAndOpportunity(productInfoId, opportunity).orElseThrow(
//				() -> new ResourceNotFoundException("ProductInfo not found for this id: " + productInfoId + " and this OpportunityId: " + opportunityId));
//
//		// Update
//		// Code can not be updated
//		productInfo.setExplanation(dto.getExplanation());
//		productInfo.setUnit(dto.getUnit());
//		productInfo.setAmount(dto.getAmount());
//		productInfo.setPrice(dto.getPrice());
//		productInfo.setDiscount(dto.getDiscount());
//		productInfo.setVat(dto.getVat());
//		this.productInfoRepository.saveAndFlush(productInfo);
//
//		return ResponseEntity.ok(productInfo);
//	}
//	
//	@Transactional
//	@DeleteMapping("{opportunityId}/product")
//	public ResponseEntity<?> deleteProductInfo(@PathVariable(value = "opportunityId") Long opportunityId,
//			@RequestBody List<Long> ids) throws ResourceNotFoundException {
//		Opportunity opportunity = this.repository.findById(opportunityId).orElseThrow(
//				() -> new ResourceNotFoundException("Opportunity not found for this id: " + opportunityId));
//		List<ProductInfo> response = this.productInfoRepository.deleteByIdInAndOpportunity(ids, opportunity);
//		return ResponseEntity.ok(response);
//	}
//
//	@Transactional
//	@DeleteMapping("{opportunityId}/product/{productInfoId}")
//	public ResponseEntity<?> deleteProductInfo1(@PathVariable(value = "opportunityId") Long opportunityId,
//			@PathVariable(value = "productInfoId") List<Long> ids) throws ResourceNotFoundException {
//		return deleteProductInfo(opportunityId, ids);
//	}

}
