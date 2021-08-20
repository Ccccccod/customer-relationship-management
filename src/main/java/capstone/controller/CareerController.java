/**
 * 
 */
package capstone.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.Career;
import capstone.repository.CareerRepository;

/**
 * CareerController
 * Ngành nghề Controller
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/career")
public class CareerController extends AbstractSimpleCRUDController<Career, CareerRepository, Long>
		implements IReadNameController<Career, CareerRepository, Long> {

	@Override
	protected Class<Career> entityClass() {
		return Career.class;
	}
	
	@Override
	public ResponseEntity<?> getAllName() {
		List<Career> all = getRepository().findAll();
		return ResponseEntity.ok(all);
	}

}
