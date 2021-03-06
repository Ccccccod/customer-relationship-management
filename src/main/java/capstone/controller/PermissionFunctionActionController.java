/**
 * 
 */
package capstone.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.PermissionFunctionAction;
import capstone.repository.PermissionFunctionActionRepository;

/**
 * PermissionFunctionActionController
 * Quyền Controller
 * @author Tuna
 */
@RestController
@RequestMapping("/api/permission")
public class PermissionFunctionActionController
		extends AbstractSimpleCRUDController<PermissionFunctionAction, PermissionFunctionActionRepository, Long> {

	@GetMapping("/name")
	public ResponseEntity<?> getAllName() {
		Set<Map<String, Object>> result = this.getRepository().findAll().stream()
				.map(p -> {
					Map<String,Object> map = new LinkedHashMap<String, Object>();
					map.put("id", p.getId());
					map.put("name", p.getValue());
					map.put("viName", p.getViName());
					return map;
				})
				.collect(Collectors.toSet());
		return ResponseEntity.ok(result);
	}

	@Override
	protected Class<PermissionFunctionAction> entityClass() {
		return PermissionFunctionAction.class;
	}

}
