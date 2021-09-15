/**
 * 
 */
package capstone.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import capstone.dto.request.OrderDto;
import capstone.entity.Country;
import capstone.entity.Customer;
import capstone.entity.District;
import capstone.entity.Order;
import capstone.entity.Province;
import capstone.entity.Ward;
import capstone.exception.ResourceNotFoundException;
import capstone.model.IdAndName;
import capstone.repository.OrderRepository;
import capstone.service.iservice.IReadNameService;

/**
 * OrderService
 * @author tuna
 */
@Service
public class OrderService extends CodedService<OrderDto, OrderDto, Order, Order, OrderRepository, Long>
		implements IReadNameService {
	
	public List<Order> findByOrderDateBetween(LocalDate from, LocalDate to) {
		return getRepository().findByOrderDateBetween(from, to);
	}

	@Override
	protected Class<Order> entityClass() {
		return Order.class;
	}

	@Override
	protected Order entityToResponse(Order entity) {
		return entity;
	}

	@Override
	protected Order createDtoToEntity(OrderDto d, Order entity) throws ResourceNotFoundException {
		Ward ward = wardService.getEntityById(d.getWardId());
		District district = Optional.ofNullable(ward).map(Ward::getDistrict)
				.orElse(districtService.getEntityById(d.getDistrictId()));
		Province province = Optional.ofNullable(district).map(District::getProvince)
				.orElse(provinceService.getEntityById(d.getProvinceId()));
		Country country = Optional.ofNullable(province).map(Province::getCountry)
				.orElse(countryService.getEntityById(d.getCountryId()));
		return entity.toBuilder()
				.id(d.getId())
//				.code(d.getCode())
				.orderDate(d.getOrderDate())
				.customer(customerService.getEntityById(d.getCustomerId()))
				.contact(contactService.getEntityById(d.getContactId()))
				.opportunity(opportunityService.getEntityById(d.getOpportunityId()))
				.explanation(d.getExplanation())
				.liquidationDeadline(d.getLiquidationDeadline())
				.deliveryDeadline(d.getDeliveryDeadline())
				.receivedMoney(d.getReceivedMoney())
				// Address
				.country(country)
				.province(province)
				.district(district)
				.ward(ward)
				.address(d.getAddress())
				.build();
	}

	@Override
	protected Order updateDtoToEntity(OrderDto updateDto, Order entity) throws ResourceNotFoundException {
		return this.createDtoToEntity(updateDto, entity);

	}
	
	@Override
	public List<?> getAllName() throws ResourceNotFoundException {
		List<IdAndName<Long>> findIdExplanationAllBy = getRepository().findIdExplanationAllBy().stream().map(i -> new IdAndName<Long>(){
			@Override
			public Long getId() {
				return i.getId();
			}
			@Override
			public void setId(Long id) {
				i.setId(id);
			}
			@Override
			public String getName() {
				return i.getExplanation();
			}
		}).collect(Collectors.toList());
		return findIdExplanationAllBy;
	};
	
	public IdAndName<Long> getCustomer(Long id) throws ResourceNotFoundException {
		Customer customer = this.getById(id).getCustomer();
		return customer;
	}

}
