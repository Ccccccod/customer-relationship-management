/**
 * 
 */
package capstone.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.dto.request.OrderDto;
import capstone.entity.Contact;
import capstone.entity.Country;
import capstone.entity.Customer;
import capstone.entity.District;
import capstone.entity.Order;
import capstone.entity.ProductInfo;
import capstone.entity.Province;
import capstone.entity.Ward;
import capstone.exception.ResourceNotFoundException;
import capstone.model.IdAndName;
import capstone.repository.OrderRepository;
import capstone.repository.OrderRepository.ContactOnly;
import capstone.repository.OrderRepository.CustomerOnly;
import capstone.service.iservice.IReadNameService;

/**
 * OrderService
 * @author tuna
 */
@Service
public class OrderService extends CodedService<OrderDto, OrderDto, Order, Order, OrderRepository, Long>
		implements IReadNameService {
	
	@Autowired
	protected ProductInfoService productInfoService;
	
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
	protected Order createDtoToEntity(OrderDto d, Order order) throws ResourceNotFoundException {
		Ward ward = wardService.getEntityById(d.getWardId());
		District district = Optional.ofNullable(ward).map(Ward::getDistrict)
				.orElse(districtService.getEntityById(d.getDistrictId()));
		Province province = Optional.ofNullable(district).map(District::getProvince)
				.orElse(provinceService.getEntityById(d.getProvinceId()));
		Country country = Optional.ofNullable(province).map(Province::getCountry)
				.orElse(countryService.getEntityById(d.getCountryId()));
		order = order.toBuilder()
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
		order.removeAllProductInfos();
		Set<ProductInfo> productInfos = this.productInfoService.generateFromProductInfoDto(d.getProductInfoDtos());
		productInfos.forEach(p -> p.setId(null));
		order.setToProductInfos(productInfos);
		return order;
	}

	@Override
	protected Order updateDtoToEntity(OrderDto d, Order order) throws ResourceNotFoundException {
		Ward ward = wardService.getEntityById(d.getWardId());
		District district = Optional.ofNullable(ward).map(Ward::getDistrict)
				.orElse(districtService.getEntityById(d.getDistrictId()));
		Province province = Optional.ofNullable(district).map(District::getProvince)
				.orElse(provinceService.getEntityById(d.getProvinceId()));
		Country country = Optional.ofNullable(province).map(Province::getCountry)
				.orElse(countryService.getEntityById(d.getCountryId()));
		order = order.toBuilder()
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
		order.removeAllProductInfos();
		Set<ProductInfo> productInfos = this.productInfoService.generateFromProductInfoDto(d.getProductInfoDtos());
//		productInfos.forEach(p -> p.setId(null));
		order.setToProductInfos(productInfos);
		return order;
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
		Session session = null;
		try {
			session = enableDeletedFilter(false);
			return this.repository.findCustomerIdAndNameById(id).map(CustomerOnly::getCustomer).orElseThrow(
					() -> new ResourceNotFoundException("Customer not found for OrderId: " + id, Customer.class));
		} finally {
			disableDeletedFilter(session);
		}
	}
	
	public IdAndName<Long> getContact(Long id) throws ResourceNotFoundException {
		Session session = null;
		try {
			session = enableDeletedFilter(false);
			return this.repository.findContactIdAndNameById(id).map(ContactOnly::getContact).orElseThrow(
					() -> new ResourceNotFoundException("Contact not found for OrderId: " + id, Contact.class));
		} finally {
			disableDeletedFilter(session);
		}
	}

}
