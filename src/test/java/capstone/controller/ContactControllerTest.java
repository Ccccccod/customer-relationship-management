/**
 * 
 */
package capstone.controller;

import java.util.Arrays;
import java.util.List;

import capstone.dto.request.ContactDto;
import capstone.entity.Contact;
import capstone.repository.ContactRepository;
import capstone.service.ContactService;

/**
 * ContactControllerTest
 * @author DELL
 */
public class ContactControllerTest extends
		CRUDControllerTest<ContactDto, ContactDto, Contact, Contact, ContactRepository, ContactService, ContactController, Long> {

	@Override
	protected String url() {
		return "/api/contact";
	}

	@Override
	protected List<Contact> resources() {
		return Arrays.asList(
				Contact.builder()
						.name("anh tu")
						.code("LH11")
						.build(),
				Contact.builder()
						.name("minh kien")
						.code("LH12")
						.build(),
				Contact.builder()
						.name(" Vi bao")
						.code("LH13")
						.build());
	}
	

	@Override
	protected Contact resource() {
		return Contact.builder()
				.id(1L)
				.name("tu")
				.code("LH41")
				.build();
	}

}
