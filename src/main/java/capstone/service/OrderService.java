/**
 * 
 */
package capstone.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import capstone.dto.request.OrderDto;
import capstone.entity.Order;
import capstone.exception.ResourceNotFoundException;
import capstone.model.IdAndExplanation;
import capstone.repository.OrderRepository;
import capstone.service.iservice.IReadNameService;

/**
 * OrderService
 * @author tuna
 */
@Service
public class OrderService extends AbstractService<OrderDto, OrderDto, Order, Order, OrderRepository, Long>
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
	
	@Override
	public List<?> getAllName() throws ResourceNotFoundException {
		List<IdAndExplanation<Long>> findIdExplanationAllBy = getRepository().findIdExplanationAllBy();
		return findIdExplanationAllBy;
	};

}
