/**
 * 
 */
package capstone.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import capstone.dto.request.InvoiceDto;
import capstone.entity.Country;
import capstone.entity.District;
import capstone.entity.Invoice;
import capstone.entity.Province;
import capstone.entity.Ward;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.InvoiceRepository;

/**
 * InvoiceService
 * @author DELL
 * @author tuna
 */
@Service
public class InvoiceService extends CodedService<InvoiceDto, InvoiceDto, Invoice, Invoice, InvoiceRepository, Long> {

	@Override
	protected Class<Invoice> entityClass() {
		return Invoice.class;
	}

	@Override
	protected Invoice entityToResponse(Invoice entity) {
		return entity;
	}

	@Override
	protected Invoice createDtoToEntity(InvoiceDto d, Invoice entity) throws ResourceNotFoundException {
		Ward ward = wardService.getEntityById(d.getWardId());
		District district = Optional.ofNullable(ward).map(Ward::getDistrict)
				.orElse(districtService.getEntityById(d.getDistrictId()));
		Province province = Optional.ofNullable(district).map(District::getProvince)
				.orElse(provinceService.getEntityById(d.getProvinceId()));
		Country country = Optional.ofNullable(province).map(Province::getCountry)
				.orElse(countryService.getEntityById(d.getCountryId()));
		return entity.toBuilder() //
				.code(d.getCode()) //
				.customer(customerService.getEntityById(d.getCustomerId())) //
				.bankAccount(d.getBankAccount()) //
				.bank(d.getBank()) //
				.taxCode(d.getTaxCode()) //
				.buyer(contactService.getEntityById(d.getBuyerId())) //
				.receiverName(d.getReceiverName()) //
				.receiverEmail(d.getReceiverEmail()) //
				.receiverPhone(d.getReceiverPhone()) //
				.order(orderService.getEntityById(d.getOrderId())) //
				// Address
				.country(country)
				.province(province)
				.district(district)
				.ward(ward)
				.address(d.getAddress())
				.build();
	}

	@Override
	protected Invoice updateDtoToEntity(InvoiceDto updateDto, Invoice entity) throws ResourceNotFoundException {
		return this.createDtoToEntity(updateDto, entity);
	}

}
