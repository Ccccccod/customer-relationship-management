/**
 * 
 */
package capstone.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.dto.request.OpportunityDto;
import capstone.entity.Opportunity;
import capstone.entity.OpportunityPhase;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.OpportunityRepository;

/**
 * OpportunityService
 * @author Tuna
 */
@Service
public class OpportunityService
		extends AbstractService<OpportunityDto, OpportunityDto, Opportunity, Opportunity, OpportunityRepository, Long> {

	@Autowired
	private OpportunityRepository repository;
	
	@Autowired
	protected ProductInfoService productInfoService;

	public List<Opportunity> findByExpectedEndDateBetween(LocalDate from, LocalDate to) {
		return repository.findByExpectedEndDateBetween(from, to);
	}

	public List<Opportunity> findByOpportunityPhaseAndExpectedEndDateBetween(OpportunityPhase opportunityPhase,
			LocalDate from, LocalDate to) {
		return repository.findByOpportunityPhaseAndExpectedEndDateBetween(opportunityPhase, from, to);
	}

	@Override
	protected Opportunity entityToResponse(Opportunity entity) {
		return entity;
	}

	@Override
	protected Opportunity createDtoToEntity(OpportunityDto d, Opportunity opportunity)
			throws ResourceNotFoundException {
		opportunity = opportunity.toBuilder()
				.id(d.getId())
				.code(d.getCode())
				.name(d.getName())
				.customer(customerService.getEntityById(d.getCustomerId()))
                .contact(contactService.getEntityById(d.getContactId()))
                .opportunityPhase(opportunityPhaseService.getEntityById(d.getOpportunityPhaseId()))   
                .successRate(d.getSuccessRate())
                .expectedEndDate(d.getExpectedEndDate())
                .source(sourceService.getEntityById(d.getSourceId()))
				.build();
		opportunity.setToProductInfos(this.productInfoService.generateFromProductInfoDto(d.getProductInfoDtos()));
		return opportunity;
	}

	@Override
	protected Opportunity updateDtoToEntity(OpportunityDto d, Opportunity opportunity)
			throws ResourceNotFoundException {
		opportunity = opportunity.toBuilder()
				.id(d.getId())
				.code(d.getCode())
				.name(d.getName())
				.customer(customerService.getEntityById(d.getCustomerId()))
                .contact(contactService.getEntityById(d.getContactId()))
                .opportunityPhase(opportunityPhaseService.getEntityById(d.getOpportunityPhaseId()))   
                .successRate(d.getSuccessRate())
                .expectedEndDate(d.getExpectedEndDate())
                .source(sourceService.getEntityById(d.getSourceId()))
				.build();
		// ProductInfo is not changed. It should not changeable in update controller
		return opportunity;
	}

	@Override
	protected Class<Opportunity> entityClass() {
		return Opportunity.class;
	}

}
