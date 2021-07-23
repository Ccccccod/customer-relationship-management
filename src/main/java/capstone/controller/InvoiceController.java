/**
 * 
 */
package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.InvoiceDto;
import capstone.entity.Contact;
import capstone.entity.Customer;
import capstone.entity.Invoice;
import capstone.entity.Order;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.ContactRepository;
import capstone.repository.CustomerRepository;
import capstone.repository.InvoiceRepository;
import capstone.repository.OrderRepository;
import capstone.service.AbstractService;

/**
 * InvoiceController
 * Hóa đơn Controller
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/invoice")
public class InvoiceController extends AbstractDtoEntityController<InvoiceDto, Invoice, InvoiceRepository, Long> {
	
	@Autowired
	protected CustomerRepository customerRepository;
	
	@Autowired
	protected ContactRepository contactRepository;
	
	@Autowired
	protected OrderRepository orderRepository;

	@Override
	protected Invoice dtoToEntity(InvoiceDto dto) throws ResourceNotFoundException {
		return Invoice.builder()
				.code(dto.getCode())
				.customer(AbstractService.findEntityById(customerRepository, dto.getCustomerId(), Customer.class))
				.address(dto.getAddress())
				.bankAccount(dto.getBankAccount())
				.bank(dto.getBank())
				.taxCode(dto.getTaxCode())
				.buyer(AbstractService.findEntityById(contactRepository, dto.getBuyerId(), Contact.class))
				.receiverName(dto.getReceiverName())
				.receiverEmail(dto.getReceiverEmail())
				.receiverPhone(dto.getReceiverPhone())
				.order(AbstractService.findEntityById(orderRepository, dto.getOrderId(), Order.class))
				.build();
	}

}
