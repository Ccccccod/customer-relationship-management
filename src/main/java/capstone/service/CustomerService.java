/**
 * 
 */
package capstone.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import capstone.dto.request.CustomerDto;
import capstone.entity.Country;
import capstone.entity.Customer;
import capstone.entity.District;
import capstone.entity.Province;
import capstone.entity.Ward;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.CustomerRepository;
import capstone.service.iservice.INamedService;

/**
 * Customer Service
 * @author Tuna
 */
@Service
public class CustomerService extends CodedService<CustomerDto, CustomerDto, Customer, Customer, CustomerRepository, Long>
		implements INamedService<Customer, CustomerRepository, Long> {

//	@Autowired
//	private SourceRepository sourceRepository;
//	
//	@Autowired
//	private ClassificationRepository classificationRepository;
//	
//	@Autowired 
//	private FieldRepository fieldRepository;
//	
//	@Autowired
//	private TypeRepository typeRepository;
//	
//	@Autowired
//	private CareerRepository careerRepository;
//	
//	@Autowired
//	private IncomeRepository incomeRepository;

	@Override
	protected Class<Customer> entityClass() {
		return Customer.class;
	}

	@Override
	protected Customer entityToResponse(Customer entity) {
		return entity;
	}

	@Override
	protected Customer createDtoToEntity(CustomerDto d, Customer entity) throws ResourceNotFoundException {
		Ward ward = wardService.getEntityById(d.getWardId());
		District district = Optional.ofNullable(ward).map(Ward::getDistrict)
				.orElse(districtService.getEntityById(d.getDistrictId()));
		Province province = Optional.ofNullable(district).map(District::getProvince)
				.orElse(provinceService.getEntityById(d.getProvinceId()));
		Country country = Optional.ofNullable(province).map(Province::getCountry)
				.orElse(countryService.getEntityById(d.getCountryId()));
		return entity.toBuilder()
				.id(d.getId())
				.code(d.getCode())
				.shortName(d.getShortName())
				.name(d.getName())
				.taxCode(d.getTaxCode())
				.phone(d.getPhone())
				.email(d.getEmail())
				.source(sourceService.getEntityById(d.getSourceId()))
				.classifications(classificationService.getEntitiesById(d.getClassificationIds()))
				.fields(fieldService.getEntitiesById(d.getFieldIds()))
				.type(typeService.getEntityById(d.getTypeId()))
				.careers(careerService.getEntitiesById(d.getCareerIds()))
				// Address
				.country(country)
				.province(province)
				.district(district)
				.ward(ward)
				.address(d.getAddress())
				.bankAccount(d.getBankAccount())
				.bank(d.getBank())
				.foundedDate(d.getFoundedDate())
				.customerSince(d.getCustomerSince())
				.income(incomeService.getEntityById(d.getIncomeId()))
				.website(d.getWebsite())
				.build();
	}

	@Override
	protected Customer updateDtoToEntity(CustomerDto updateDto, Customer entity) throws ResourceNotFoundException {
		return createDtoToEntity(updateDto, entity);
	}

}
