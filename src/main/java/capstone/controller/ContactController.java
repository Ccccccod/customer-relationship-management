/**
 * 
 */
package capstone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.ContactDto;
import capstone.entity.Contact;

/**
 * @author Tuna
 *
 */
@RestController
@RequestMapping(value = "/api/contact")
public class ContactController extends AbstractDtoEntityController<ContactDto, Contact, Long> {

}
