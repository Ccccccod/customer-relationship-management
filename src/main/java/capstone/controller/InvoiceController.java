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

import capstone.dto.request.InvoiceDto;
import capstone.entity.Invoice;
import capstone.entity.ProductInfo;
import capstone.repository.InvoiceRepository;
import capstone.repository.ProductInfoRepository;
import capstone.service.InvoiceService;
import capstone.service.ProductInfoService;
import capstone.service.UnitService;
import capstone.service.UserService;

/**
 * InvoiceController
 * Hóa đơn Controller
 * @author Tuna
 */
@RestController
@RequestMapping("/api/invoice")
public class InvoiceController
		extends CRUDController<InvoiceDto, InvoiceDto, Invoice, Invoice, InvoiceRepository, InvoiceService, Long>
		implements ProductInfoedController<Invoice, InvoiceRepository, Long> {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductInfoService productInfoService;
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private ProductInfoRepository productInfoRepository;

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

	@Override
	public InvoiceRepository getRepository() {
		return invoiceRepository;
	}

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
