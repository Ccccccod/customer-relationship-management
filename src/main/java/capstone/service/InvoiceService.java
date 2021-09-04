/**
 * 
 */
package capstone.service;

import capstone.dto.request.InvoiceDto;
import capstone.entity.Invoice;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.InvoiceRepository;

/**
 * InvoiceService
 * @author DELL
 */
public class InvoiceService extends AbstractService<InvoiceDto , InvoiceDto, Invoice, Invoice,InvoiceRepository, Long> {

	@Override
	protected Class<Invoice> entityClass() {
		return Invoice.class;
	}

	@Override
	protected Invoice entityToResponse(Invoice entity) {
		return entity;
	}

	@Override
	protected Invoice createDtoToEntity(InvoiceDto dto , Invoice entity) throws ResourceNotFoundException {
		 return  entity.toBuilder()
		.code(dto.getCode())
		.customer(customerService.getEntityById(dto.getCustomerId()))
		.address(dto.getAddress())
		.bankAccount(dto.getBankAccount())
		.bank(dto.getBank())
		.taxCode(dto.getTaxCode())
		.buyer(contactService.getEntityById(dto.getBuyerId()))
		.receiverName(dto.getReceiverName())
		.receiverEmail(dto.getReceiverEmail())
		.receiverPhone(dto.getReceiverPhone())
		.order(orderService.getEntityById(dto.getOrderId()))
		.build();
	}

	@Override
	protected Invoice updateDtoToEntity(InvoiceDto updateDto, Invoice entity) throws ResourceNotFoundException {
		return this.createDtoToEntity(updateDto, entity);
	}

	
	

}
