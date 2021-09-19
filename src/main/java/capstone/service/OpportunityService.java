/**
 * 
 */
package capstone.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.dto.request.OpportunityDto;
import capstone.entity.Contact;
import capstone.entity.Country;
import capstone.entity.Customer;
import capstone.entity.District;
import capstone.entity.Opportunity;
import capstone.entity.OpportunityPhase;
import capstone.entity.ProductInfo;
import capstone.entity.Province;
import capstone.entity.Ward;
import capstone.exception.ResourceNotFoundException;
import capstone.model.IdAndName;
import capstone.repository.OpportunityRepository;
import capstone.repository.OpportunityRepository.ContactOnly;
import capstone.repository.OpportunityRepository.CustomerOnly;
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
		opportunity.removeAllProductInfos();
		Set<ProductInfo> productInfo = this.productInfoService.generateFromProductInfoDto(d.getProductInfoDtos());
		productInfo.forEach(p -> p.setId(null));
		opportunity.setToProductInfos(productInfo);
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
		opportunity.removeAllProductInfos();
		Set<ProductInfo> productInfo = this.productInfoService.generateFromProductInfoDto(d.getProductInfoDtos());
//		productInfo.forEach(p -> p.setId(null));
		opportunity.setToProductInfos(productInfo);
		return opportunity;
	}

	@Override
	protected Class<Opportunity> entityClass() {
		return Opportunity.class;
	}
	
	public IdAndName<Long> getCustomer(Long id) throws ResourceNotFoundException {
		Session session = null;
		try {
			session = enableDeletedFilter(false);
			return this.repository.findCustomerIdAndNameById(id).map(CustomerOnly::getCustomer).orElseThrow(
					() -> new ResourceNotFoundException("Customer not found for OpportunityId: " + id, Customer.class));
		} finally {
			disableDeletedFilter(session);
		}
	}
	
	public IdAndName<Long> getContact(Long id) throws ResourceNotFoundException {
		Session session = null;
		try {
			session = enableDeletedFilter(false);
			return this.repository.findContactIdAndNameById(id).map(ContactOnly::getContact).orElseThrow(
					() -> new ResourceNotFoundException("Contact not found for OpportunityId: " + id, Contact.class));
		} finally {
			disableDeletedFilter(session);
		}
	}

}
