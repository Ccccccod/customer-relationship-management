/**
 * 
 */
package capstone.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.InvoiceDto;
import capstone.entity.Contact;
import capstone.entity.Customer;
import capstone.entity.Invoice;
import capstone.entity.Order;
import capstone.entity.ProductInfo;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.ContactRepository;
import capstone.repository.CustomerRepository;
import capstone.repository.InvoiceRepository;
import capstone.repository.OrderRepository;
import capstone.repository.ProductInfoRepository;
import capstone.service.AbstractService;
import capstone.service.ProductInfoService;

/**
 * InvoiceController
 * Hóa đơn Controller
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/invoice")
public class InvoiceController extends AbstractDtoEntityController<InvoiceDto, Invoice, InvoiceRepository, Long> //
		implements ProductInfoedController<Invoice, InvoiceRepository, Long> {
	
	@Autowired
	protected CustomerRepository customerRepository;
	
	@Autowired
	protected ContactRepository contactRepository;
	
	@Autowired
	protected OrderRepository orderRepository;
	
	@Autowired
	protected ProductInfoRepository productInfoRepository;
	
	@Autowired
	protected ProductInfoService productInfoService;

	@Override
	protected Invoice dtoToEntity(InvoiceDto dto, Invoice invoice) throws ResourceNotFoundException {
		return invoice.toBuilder()
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

	@Override
	public ProductInfoService getProductInfoService() {
		return this.productInfoService;
	}

	@Override
	public Class<Invoice> entityClass() {
		return Invoice.class;
	}

	@Override
	public ProductInfoRepository getProductInfoRepository() {
		return this.productInfoRepository;
	}

	@Override
	public List<ProductInfo> findByProductInfoed(Invoice t) {
		return this.productInfoRepository.findByInvoice(t);
	}

	@Override
	public Optional<ProductInfo> findByIdAndProductInfoed(Long id, Invoice t) {
		return this.productInfoRepository.findByIdAndInvoice(id, t);
	}

	@Override
	public List<ProductInfo> deleteByIdAndProductInfoed(Iterable<? extends Long> ids, Invoice t) {
		return this.productInfoRepository.deleteByIdInAndInvoice(ids, t);
	}

}
