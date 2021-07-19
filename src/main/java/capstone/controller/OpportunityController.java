/**
 * 
 */
package capstone.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.transaction.annotation.Transactional;
=======
>>>>>>> d86532243c5141ae5ae782a22357ea8ae0e6b4c3
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.OpportunityDto;
import capstone.dto.request.ProductInfoDto;
import capstone.entity.Contact;
import capstone.entity.Customer;
import capstone.entity.Opportunity;
import capstone.entity.OpportunityPhase;
import capstone.entity.ProductInfo;
import capstone.entity.ProductInfo.ProductInfoBuilder;
import capstone.entity.Source;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.ContactRepository;
import capstone.repository.CustomerRepository;
import capstone.repository.OpportunityPhaseRepository;
import capstone.repository.ProductInfoRepository;
import capstone.repository.SourceRepository;
import capstone.service.AbstractService;

/**
 * OpportunityController
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/opportunity")
public class OpportunityController extends AbstractDtoEntityController<OpportunityDto, Opportunity, Long> {
	
	@Autowired
	protected ProductInfoRepository productInfoRepository;
	
	@Autowired
	protected CustomerRepository customerRepository;

	@Autowired
	protected ContactRepository contactRepository;

	@Autowired
	protected OpportunityPhaseRepository opportunityPhaseRepository;
	
	@Autowired
	protected SourceRepository sourceRepository;

	@Override
	protected Opportunity dtoToEntity(OpportunityDto dto) throws ResourceNotFoundException {
		Opportunity opportunity = Opportunity.builder()
				.name(dto.getName())
				.customer(AbstractService.findEntityById(customerRepository, dto.getCustomerId(), Customer.class))
				.contact(AbstractService.findEntityById(contactRepository, dto.getContactId(), Contact.class))
				.moneyAmount(dto.getMoneyAmount())
				.opportunityPhase(AbstractService.findEntityById(opportunityPhaseRepository, dto.getOpportunityPhaseId(), OpportunityPhase.class))
				.successRate(dto.getSuccessRate())
				.expectedEndDate(dto.getExpectedEndDate())
				.expectedTurnOver(dto.getExpectedTurnOver())
				.source(AbstractService.findEntityById(sourceRepository, dto.getSourceId(), Source.class))
				.build();
		if (Objects.isNull(opportunity.getExpectedTurnOver())) {
			opportunity.setExpectedTurnOver(opportunity.getMoneyAmount() * opportunity.getSuccessRate() / 100);
		}
		// ProductInfo
		if (Objects.nonNull(dto.getProductInfoDtos())) {
			opportunity.setProductInfos(dto.getProductInfoDtos().stream()
					.map(dto1 -> this.productDtoToProductInfo(dto1)
							.opportunity(opportunity)
							.build())
					.collect(Collectors.toSet()));
		}
		return opportunity;
	}
	
	@Override
	protected Opportunity updateEntity(OpportunityDto dto, Opportunity entity) throws ResourceNotFoundException {
		entity.setName(dto.getName());
		entity.setCustomer(AbstractService.findEntityById(customerRepository, dto.getCustomerId(), Customer.class));
		entity.setContact(AbstractService.findEntityById(contactRepository, dto.getContactId(), Contact.class));
		entity.setMoneyAmount(dto.getMoneyAmount());
		entity.setOpportunityPhase(AbstractService.findEntityById(opportunityPhaseRepository, dto.getOpportunityPhaseId(), OpportunityPhase.class));
		entity.setSuccessRate(dto.getSuccessRate());
		entity.setExpectedEndDate(dto.getExpectedEndDate());
		entity.setExpectedTurnOver(dto.getExpectedTurnOver());
		entity.setSource(AbstractService.findEntityById(sourceRepository, dto.getSourceId(), Source.class));
		if (Objects.isNull(entity.getExpectedTurnOver())) {
			entity.setExpectedTurnOver(entity.getMoneyAmount() * entity.getSuccessRate() / 100);
		}
		// ProductInfo is not changed. It should not changeable in update controller
		return entity;
	}
	
	private ProductInfoBuilder productDtoToProductInfo(ProductInfoDto dto) {
		return ProductInfo.builder()
				.productCode(dto.getProductCode())
				.explanation(dto.getExplanation())
				.unit(dto.getUnit())
				.amount(dto.getAmount())
				.price(dto.getPrice())
				.discount(dto.getDiscount())
				.vat(dto.getVat());
	}
	
<<<<<<< HEAD
	@GetMapping("{opportunityId}/product")
	public ResponseEntity<List<ProductInfo>> getAllProductInfo(
			@PathVariable(value = "opportunityId") Long opportunityId) throws ResourceNotFoundException {
		Opportunity opportunity = this.repository.findById(opportunityId).orElseThrow(
				() -> new ResourceNotFoundException("Opportunity not found for this id: " + opportunityId));
		return ResponseEntity.ok(this.productInfoRepository.findByOpportunity(opportunity));
	}

	@GetMapping("{opportunityId}/product/{productInfoId}")
	public ResponseEntity<ProductInfo> getProductInfo(@PathVariable(value = "opportunityId") Long opportunityId,
			@PathVariable(value = "productInfoId") Long productInfoId) throws ResourceNotFoundException {
		Opportunity opportunity = this.repository.findById(opportunityId).orElseThrow(
				() -> new ResourceNotFoundException("Opportunity not found for this id: " + opportunityId));
		return ResponseEntity.ok(this.productInfoRepository.findByIdAndOpportunity(productInfoId, opportunity)
				.orElseThrow(() -> new ResourceNotFoundException("ProductInfo not found for this id: " + productInfoId
						+ " and this OpportunityId: " + opportunityId)));
	}
	
	@PostMapping("{opportunityId}/product")
	public ResponseEntity<ProductInfo> createProductInfo(@PathVariable(value = "opportunityId") Long opportunityId,
			@RequestBody ProductInfoDto productInfoDto) throws ResourceNotFoundException {
		Opportunity opportunity = this.repository.findById(opportunityId).orElseThrow(
				() -> new ResourceNotFoundException("Opportunity not found for this id: " + opportunityId));
=======
	@GetMapping("{id}/product")
	public ResponseEntity<List<ProductInfo>> getAllProductInfo(@PathVariable(value = "id") Long opportunityId) throws ResourceNotFoundException {
		Opportunity opportunity = this.repository.findById(opportunityId).orElseThrow(() -> new ResourceNotFoundException("Opportunity not found for this id: " + opportunityId));
		return ResponseEntity.ok(this.productInfoRepository.findByOpportunity(opportunity));
	}
	
	@PostMapping("{id}/product")
	public ResponseEntity<ProductInfo> createProduct(@PathVariable(value = "id") Long opportunityId,
			@RequestBody ProductInfoDto productInfoDto) throws ResourceNotFoundException {
		Opportunity opportunity = this.repository.findById(opportunityId).orElseThrow(() -> new ResourceNotFoundException("Opportunity not found for this id: " + opportunityId));
>>>>>>> d86532243c5141ae5ae782a22357ea8ae0e6b4c3
		ProductInfo productInfo = this.productDtoToProductInfo(productInfoDto).build();
		opportunity.addToProductInfos(productInfo);
		opportunity = this.repository.saveAndFlush(opportunity);
		
		return ResponseEntity.ok(productInfo);
	}
	
<<<<<<< HEAD
	@PutMapping("{opportunityId}/product/{productInfoId}")
	public ResponseEntity<ProductInfo> updateProductInfo(@PathVariable(value = "opportunityId") Long opportunityId,
			@PathVariable(value = "productInfoId") Long productInfoId, @RequestBody ProductInfoDto dto)
			throws ResourceNotFoundException {
		Opportunity opportunity = this.repository.findById(opportunityId).orElseThrow(
				() -> new ResourceNotFoundException("Opportunity not found for this id: " + opportunityId));
		ProductInfo productInfo = this.productInfoRepository.findByIdAndOpportunity(productInfoId, opportunity).orElseThrow(
				() -> new ResourceNotFoundException("ProductInfo not found for this id: " + productInfoId + " and this OpportunityId: " + opportunityId));

=======
	@PutMapping("/product/{id}")
	public ResponseEntity<ProductInfo> updateProduct(@PathVariable(value = "id") Long id, @RequestBody ProductInfoDto dto)
			throws ResourceNotFoundException {
		ProductInfo productInfo = this.productInfoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ProductInfo not found for this id: " + id));
		
>>>>>>> d86532243c5141ae5ae782a22357ea8ae0e6b4c3
		// Update
		// Code can not be updated
		productInfo.setExplanation(dto.getExplanation());
		productInfo.setUnit(dto.getUnit());
		productInfo.setAmount(dto.getAmount());
		productInfo.setPrice(dto.getPrice());
		productInfo.setDiscount(dto.getDiscount());
		productInfo.setVat(dto.getVat());
		this.productInfoRepository.saveAndFlush(productInfo);

		return ResponseEntity.ok(productInfo);
	}
	
<<<<<<< HEAD
	@Transactional
	@DeleteMapping("{opportunityId}/product")
	public ResponseEntity<?> deleteProductInfo(@PathVariable(value = "opportunityId") Long opportunityId,
			@RequestBody List<Long> ids) throws ResourceNotFoundException {
		Opportunity opportunity = this.repository.findById(opportunityId).orElseThrow(
				() -> new ResourceNotFoundException("Opportunity not found for this id: " + opportunityId));
		List<ProductInfo> response = this.productInfoRepository.deleteByIdInAndOpportunity(ids, opportunity);
		return ResponseEntity.ok(response);
	}

	@Transactional
	@DeleteMapping("{opportunityId}/product/{productInfoId}")
	public ResponseEntity<?> deleteProductInfo1(@PathVariable(value = "opportunityId") Long opportunityId,
			@PathVariable(value = "productInfoId") List<Long> ids) throws ResourceNotFoundException {
		return deleteProductInfo(opportunityId, ids);
=======
	@DeleteMapping("/product")
	public ResponseEntity<?> deleteProduct(@RequestBody List<Long> ids) {
		this.productInfoRepository.deleteAllById(ids);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<?> deleteProduct1(@RequestParam List<Long> ids) {
		return deleteProduct(ids);
>>>>>>> d86532243c5141ae5ae782a22357ea8ae0e6b4c3
	}

}
