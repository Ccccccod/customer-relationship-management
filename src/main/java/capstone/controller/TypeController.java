/**
 * 
 */
package capstone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.Type;
import capstone.repository.TypeRepository;

/**
 * TypeController
 * Loại hình Controller
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/type")
public class TypeController extends AbstractSimpleCRUDController<Type, TypeRepository, Long>
		implements IReadNameController<Type, TypeRepository, Long> {

	@Override
	protected Class<Type> entityClass() {
		return Type.class;
	}

}
