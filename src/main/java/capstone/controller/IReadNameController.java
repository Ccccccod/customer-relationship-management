/**
 * 
 */
package capstone.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import capstone.exception.ResourceNotFoundException;
import capstone.model.Identifiable;
import capstone.model.Named;
import capstone.service.iservice.IReadNameService;

/**
 * Controller to get all names
 * @author Tuna
 *
 */
interface IReadNameController< //
	T extends Object & Identifiable<ID> & Named, //
	Service extends IReadNameService,
	ID extends Serializable //
> //
{
	
	@Autowired
	Service getService();

	@GetMapping("/name")
	default ResponseEntity<?> getAllName() throws ResourceNotFoundException {
		return ResponseEntity.ok(getService().getAllName());
	}
	
}
