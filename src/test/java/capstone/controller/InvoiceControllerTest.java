/**
 * 
 */
package capstone.controller;

import java.util.Arrays;
import java.util.List;

import capstone.dto.request.InvoiceDto;
import capstone.entity.Invoice;
import capstone.entity.Product;
import capstone.repository.InvoiceRepository;

/**
 * @author DELL
 *
 */
public class InvoiceControllerTest
		extends AbstractDtoEntityControllerTest<InvoiceDto, Invoice, InvoiceRepository, InvoiceController, Long> {

	@Override
	protected String url() {
		return "/api/invoice";
	}

	@Override
	protected List<Invoice> resources() {
		return Arrays.asList(Invoice.builder()
				.code("HD0009")
				.build() ,
				Invoice.builder()
						.code("HD0008")
						.build() ,
						Invoice.builder()
								.code("HD0006")
								.build());
	}

	@Override
	protected Invoice resource() {
		return Invoice.builder().id(1L).code("HD0004").build();
	}

}
