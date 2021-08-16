/**
 * 
 */
package capstone.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.common.enums.OpportunityPhase;
import capstone.entity.Opportunity;
import capstone.repository.OpportunityRepository;

/**
 * OpportunityService
 * @author Tuna
 *
 */
@Service
public class OpportunityService {

	@Autowired
	private OpportunityRepository repository;

	public List<Opportunity> findByExpectedEndDateBetween(LocalDate from, LocalDate to) {
		return repository.findByExpectedEndDateBetween(from, to);
	}

	public List<Opportunity> findByOpportunityPhaseAndExpectedEndDateBetween(OpportunityPhase opportunityPhase,
			LocalDate from, LocalDate to) {
		return repository.findByOpportunityPhaseAndExpectedEndDateBetween(opportunityPhase, from, to);
	}

}
