/**
 * 
 */
package capstone.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.dto.request.OpportunityDto;
import capstone.entity.Country;
import capstone.entity.Customer;
import capstone.entity.District;
import capstone.entity.Opportunity;
import capstone.entity.OpportunityPhase;
import capstone.entity.Province;
import capstone.entity.Ward;
import capstone.exception.ResourceNotFoundException;
import capstone.model.IdAndName;
import capstone.repository.OpportunityRepository;
import capstone.service.iservice.INamedService;

/**
 * OpportunityService
 * @author Tuna
 */
@Service
public class OpportunityService
		extends CodedService<OpportunityDto, OpportunityDto, Opportunity, Opportunity, OpportunityRepository, Long>
		implements INamedService<Opportunity, OpportunityRepository, Long> {

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
		Ward ward = wardService.getEntityById(d.getWardId());
		District district = Optional.ofNullable(ward).map(Ward::getDistrict)
				.orElse(districtService.getEntityById(d.getDistrictId()));
		Province province = Optional.ofNullable(district).map(District::getProvince)
				.orElse(provinceService.getEntityById(d.getProvinceId()));
		Country country = Optional.ofNullable(province).map(Province::getCountry)
				.orElse(countryService.getEntityById(d.getCountryId()));
		opportunity = opportunity.toBuilder()
				.id(d.getId())
//				.code(d.getCode())
				.name(d.getName())
				.customer(customerService.getEntityById(d.getCustomerId()))
                .contact(contactService.getEntityById(d.getContactId()))
                .opportunityPhase(opportunityPhaseService.getEntityById(d.getOpportunityPhaseId()))   
                .successRate(d.getSuccessRate())
                .expectedEndDate(d.getExpectedEndDate())
                .source(sourceService.getEntityById(d.getSourceId()))
				// Address
				.country(country)
				.province(province)
				.district(district)
				.ward(ward)
				.address(d.getAddress())
				.build();
		opportunity.setToProductInfos(this.productInfoService.generateFromProductInfoDto(d.getProductInfoDtos()));
		return opportunity;
	}

	@Override
	protected Opportunity updateDtoToEntity(OpportunityDto d, Opportunity opportunity)
			throws ResourceNotFoundException {
		Ward ward = wardService.getEntityById(d.getWardId());
		District district = Optional.ofNullable(ward).map(Ward::getDistrict)
				.orElse(districtService.getEntityById(d.getDistrictId()));
		Province province = Optional.ofNullable(district).map(District::getProvince)
				.orElse(provinceService.getEntityById(d.getProvinceId()));
		Country country = Optional.ofNullable(province).map(Province::getCountry)
				.orElse(countryService.getEntityById(d.getCountryId()));
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
				// Address
				.country(country)
				.province(province)
				.district(district)
				.ward(ward)
				.address(d.getAddress())
				.build();
		// ProductInfo is not changed. It should not changeable in update controller
		return opportunity;
	}

	@Override
	protected Class<Opportunity> entityClass() {
		return Opportunity.class;
	}
	
	public IdAndName<Long> getCustomer(Long id) throws ResourceNotFoundException {
		Customer customer = this.getById(id).getCustomer();
		return customer;
	}

}
