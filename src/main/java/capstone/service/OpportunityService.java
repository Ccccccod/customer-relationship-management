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
import capstone.exception.ResourceNotFoundException;
import capstone.repository.ContactRepository;
import capstone.repository.CustomerRepository;
import capstone.repository.OpportunityPhaseRepository;
import capstone.repository.OpportunityRepository;
import capstone.repository.SourceRepository;

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
	private CustomerRepository customerRepository;

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private OpportunityPhaseRepository opportunityPhaseRepository;

	@Autowired
	private SourceRepository sourceRepository;

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
	protected Opportunity createDtoToEntity(OpportunityDto d, Opportunity entity)
			throws ResourceNotFoundException {
		return entity.toBuilder()
				.id(d.getId())
				.code(d.getCode())
				.name(d.getName())
				.build();
	}

	@Override
	protected Opportunity updateDtoToEntity(OpportunityDto updateDto, Opportunity entity)
			throws ResourceNotFoundException {
		return this.createDtoToEntity(updateDto, entity);
	}

	@Override
	protected Class<Opportunity> entityClass() {
		return Opportunity.class;
	}

}
