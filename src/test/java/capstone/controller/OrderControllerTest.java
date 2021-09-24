/**
 * 
 */
package capstone.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import capstone.dto.request.OrderDto;
import capstone.entity.Order;
import capstone.repository.OrderRepository;
import capstone.service.OrderService;

/**
 * OrderControllerTest
 * @author DELL
 */
public class OrderControllerTest
		extends CRUDControllerTest<OrderDto, OrderDto, Order, Order, OrderRepository, OrderService, OrderConroller, Long>{

	@Override
	protected String url() {
		return "/api/order";
	}

	@Override
	protected List<Order> resources() {
		return Arrays.asList(Order.builder()
				.explanation("ban cho tu")
				.code("DH11")
				.build() ,
				Order.builder()
						.explanation("ban cho kien")
						.code("DH12")
						.build() ,
						Order.builder()
						.code("DH13")
								.explanation("ban cho bao")
								.build());
	}

	@Override
	protected Order resource() {
		return Order.builder().id(1L)
				.explanation("ban cho duc")
				.code("DH14")
				.orderDate(LocalDate.of(2021, 4, 20))
				.liquidationDeadline(LocalDate.of(2021, 4, 20))
				.deliveryDeadline(LocalDate.of(2021, 4, 20))
				.build();
	}

}
