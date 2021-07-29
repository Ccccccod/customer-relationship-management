/**
 * 
 */
package capstone.controller;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import capstone.dto.request.OrderDto;
import capstone.entity.Order;
import capstone.repository.OrderRepository;

/**
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
				.build() ,
				Order.builder()
						.name("ban cho kien")
						.code("DH12")
						.build() ,
						Order.builder()
						.code("DH13")
								.name("ban cho bao")
								.build());
	}

	@Override
	protected Order resource() {
		return Order.builder().id(1L).name("ban cho duc").code("DH14").liquidationDeadline(LocalDate.of(2021, 4, 20)).deliveryDeadline(LocalDate.of(2021, 4, 20)).build();
	}
	@Override
	@Test
	public void testCreateUpdate() throws Exception {
		org.assertj.core.api.Assertions.assertThat("").isEqualToIgnoringWhitespace("");

	}

}
