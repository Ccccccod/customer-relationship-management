/**
 * 
 */
package capstone.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.PotentialDto;
import capstone.entity.Contact;
import capstone.entity.Potential;
import capstone.exception.ResourceExistedException;
import capstone.exception.ResourceNotFoundException;
import capstone.repository.PotentialRepository;
import capstone.service.PotentialService;

/**
 * PotentialController
 * Tiềm năng Controller
 * @author Tuna
 */
@RestController
@RequestMapping("/api/potential")
public class PotentialController extends
		CRUDController<PotentialDto, PotentialDto, Potential, Potential, PotentialRepository, PotentialService, Long> {
	
	@PostMapping("/single-convert/{id}")
	public void singleConvert(@PathVariable(name = "id", required = true) Long id,
			@RequestParam(name = "customerId") Long customerId)
			throws ResourceNotFoundException, InstantiationException, IllegalAccessException, ResourceExistedException {
		Contact contact = service.singleConvert(id, customerId);
	}

}
