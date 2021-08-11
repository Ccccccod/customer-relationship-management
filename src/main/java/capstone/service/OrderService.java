/**
 * 
 */
package capstone.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capstone.entity.Order;
import capstone.repository.OrderRepository;

/**
 * OrderService
 * @author Tuna
 *
 */
@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	public List<Order> findByOrderDateBetween(LocalDate from, LocalDate to) {
		return repository.findByOrderDateBetween(from, to);
	}

}
