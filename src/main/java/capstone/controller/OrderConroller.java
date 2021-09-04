/**
 * 
 */
package capstone.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.OrderDto;
import capstone.entity.Contact;
import capstone.entity.Customer;
import capstone.entity.Opportunity;
import capstone.entity.Order;
import capstone.entity.ProductInfo;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.ContactRepository;
import capstone.repository.CustomerRepository;
import capstone.repository.InvoiceRepository;
import capstone.repository.OpportunityRepository;
import capstone.repository.OrderRepository;
import capstone.repository.ProductInfoRepository;
import capstone.service.AbstractService;
import capstone.service.ProductInfoService;

/**
 * OrderController
 * Đơn hàng controller
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/order")
public class OrderConroller extends AbstractDtoEntityController<OrderDto, Order, OrderRepository, Long>
		implements IReadNameController<Order, OrderRepository, Long>,
		ProductInfoedController<Order, OrderRepository, Long> {
	
	@Autowired
	protected CustomerRepository customerRepository;

	@Autowired
	protected ContactRepository contactRepository;

	@Autowired
	protected OpportunityRepository opportunityRepository;

	@Autowired
	protected ProductInfoRepository productInfoRepository;
	
	@Autowired
	protected ProductInfoService productInfoService;
	
	@Autowired
	private InvoiceRepository invoiceRepository;

	@Override
	protected Order dtoToEntity(OrderDto dto, Order order) throws ResourceNotFoundException {
		return order.toBuilder()
				.code(dto.getCode())
				.name(dto.getName())
				.orderDate(dto.getOrderDate())
				.customer(AbstractService.findEntityById(customerRepository, dto.getCustomerId(), Customer.class))
				.contact(AbstractService.findEntityById(contactRepository, dto.getContactId(), Contact.class))
				.opportunity(AbstractService.findEntityById(opportunityRepository, dto.getOpportunityId(), Opportunity.class))
				.orderValue(dto.getOrderValue())
				.liquidationValue(dto.getLiquidationValue())
				.liquidationDeadline(dto.getLiquidationDeadline())
				.deliveryDeadline(dto.getDeliveryDeadline())
				.paid(dto.getPaid())
				.build();
	}

	@Override
	public ProductInfoService getProductInfoService() {
		return this.productInfoService;
	}

	@Override
	public Class<Order> entityClass() {
		return Order.class;
	}

	@Override
	public ProductInfoRepository getProductInfoRepository() {
		return this.productInfoRepository;
	}

	@Override
	public List<ProductInfo> findByProductInfoed(Order t) {
		return this.productInfoRepository.findByOrder(t);
	}

	@Override
	public Optional<ProductInfo> findByIdAndProductInfoed(Long id, Order t) {
		return this.productInfoRepository.findByIdAndOrder(id, t);
	}

	@Override
	public List<ProductInfo> deleteByIdAndProductInfoed(Iterable<? extends Long> ids, Order t) {
		return this.productInfoRepository.deleteByIdInAndOrder(ids, t);
	}
	
	@Override
	protected void preDelete(List<Order> entities) {
		entities.forEach(e -> {
			e.getInvoices().forEach(i -> i.setOrder(null));
			invoiceRepository.saveAll(e.getInvoices());
		});
	}

}
