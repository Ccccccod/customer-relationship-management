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

/**
 * OrderControllerTest
 * @author DELL
 *
 */
public class OrderControllerTest
		extends AbstractDtoEntityControllerTest<OrderDto, Order, OrderRepository, OrderConroller, Long> {

	@Override
	protected String url() {
		return "/api/order";
	}

	@Override
	protected List<Order> resources() {
		return Arrays.asList(Order.builder()
				.name("ban cho tu")
				.code("DH11")
				.paid(true)
				.build() ,
				Order.builder()
						.name("ban cho kien")
						.code("DH12")
						.paid(true)
						.build() ,
						Order.builder()
						.code("DH13")
								.name("ban cho bao")
								.paid(false)
								.build());
	}

	@Override
	protected Order resource() {
		return Order.builder().id(1L)
				.name("ban cho duc")
				.code("DH14")
				.orderValue(1200L)
				.orderDate(LocalDate.of(2021, 4, 20))
				.liquidationDeadline(LocalDate.of(2021, 4, 20))
				.deliveryDeadline(LocalDate.of(2021, 4, 20)).build();
	}

	@Override
	protected OrderDto createResource() {
		return OrderDto.builder()
				.name(resource().getName())
				.code(resource().getCode())
				.orderValue(resource().getOrderValue())
				.orderDate(resource().getOrderDate())
				.liquidationDeadline(resource().getLiquidationDeadline())
				.deliveryDeadline(resource().getDeliveryDeadline())
				.build();
	}

}
