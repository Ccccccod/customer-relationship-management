/**
 * 
 */
package capstone.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.dto.request.OrderDto;
import capstone.entity.Order;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.OrderRepository;

/**
 * OrderService
 * @author tuna
 */
@Service
public class OrderService extends AbstractService< OrderDto,  OrderDto,  Order,  Order,  OrderRepository, Long>{
	
	@Autowired
	private OrderRepository repository;
	
	public List<Order> findByOrderDateBetween(LocalDate from, LocalDate to) {
		return repository.findByOrderDateBetween(from, to);
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
		return entity.toBuilder()
				.id(d.getId())
				.code(d.getCode())
				.orderDate(d.getOrderDate())
				.customer(customerService.getEntityById(d.getCustomerId()))
				.contact(contactService.getEntityById(d.getContactId()))
				.opportunity(opportunityService.getEntityById(d.getOpportunityId()))
				.explanation(d.getExplanation())
				.liquidationDeadline(d.getLiquidationDeadline())
				.deliveryDeadline(d.getDeliveryDeadline())
				.receivedMoney(d.getReceivedMoney())
				.build();
	}

	@Override
	protected Order updateDtoToEntity(OrderDto updateDto, Order entity) throws ResourceNotFoundException {
		return this.createDtoToEntity(updateDto, entity);

	}

}
