/**
 * 
 */
package capstone.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.OrderDto;
import capstone.entity.Order;
import capstone.entity.ProductInfo;
import capstone.repository.ContactRepository;
import capstone.repository.CustomerRepository;
import capstone.repository.OpportunityRepository;
import capstone.repository.OrderRepository;
import capstone.repository.ProductInfoRepository;
import capstone.service.OrderService;
import capstone.service.ProductInfoService;
import capstone.service.UnitService;
import capstone.service.UserService;

/**
 * OrderController
 * Đơn hàng controller
 * @author Tuna
 */
@RestController
@RequestMapping("/api/order")
public class OrderConroller extends CRUDController<OrderDto, OrderDto, Order, Order, OrderRepository, OrderService, Long>
		implements /*IReadNameController<Order, OrderRepository, Long>,*/
		ProductInfoedController<Order, OrderRepository, Long> {
	
	@Autowired
	protected OrderRepository orderRepository;
	
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
	protected UserService userService;

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
	public OrderRepository getRepository() {
		return orderRepository;
	}

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Logger getLogger() {
		return logger;
	}

	@Override
	public UserService getUserService() {
		return userService;
	}
	
	@Autowired
	private UnitService unitService;

	@Override
	public UnitService getUnitService() {
		return unitService;
	}

}
