/**
 * 
 */
package capstone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.dto.request.PotentialDto;
import capstone.entity.Potential;
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
	
//	@Autowired
//	private PotentialService service;
//
//	@GetMapping
//	public ResponseEntity<List<Potential>> getAll() {
//		List<Potential> list = service.getAll();
//		return ResponseEntity.ok(list);
//	}
//
//	@GetMapping("/{id}")
//	public ResponseEntity<Potential> getById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
//		Potential get = service.getById(id);
//		return ResponseEntity.ok(get);
//	}
//
//	@PostMapping
//	public ResponseEntity<Potential> create(@Valid @RequestBody PotentialDto dto) throws IllegalArgumentException,
//			IllegalAccessException, InstantiationException, ResourceNotFoundException, ResourceExistedException {
//		Potential create = service.create(dto);
//		return ResponseEntity.status(HttpStatus.CREATED).body(create);
//	}
//	
//	@PutMapping("/{id}")
//	public ResponseEntity<Potential> update(@PathVariable(value = "id") Long id, @Valid @RequestBody PotentialDto dto)
//			throws InstantiationException, IllegalAccessException, ResourceNotFoundException, ResourceExistedException {
//		Potential update = service.update(id, dto);
//		return ResponseEntity.ok(update);
//	}
//	
//	@DeleteMapping
//	public ResponseEntity<?> delete(@RequestBody List<Long> ids) throws ResourceNotFoundException {
//		service.delete(ids);
//		LinkedHashMap<String,Object> map = new LinkedHashMap<String, Object>();
//		map.put("deleted", true);
//		return ResponseEntity.ok(map);
//	}
//	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<?> delete1(@PathVariable(value = "id") List<Long> ids) throws ResourceNotFoundException {
//		return delete(ids);
//	}

}
