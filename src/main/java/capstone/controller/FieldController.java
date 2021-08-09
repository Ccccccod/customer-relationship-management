/**
 * 
 */
package capstone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.Field;
import capstone.repository.FieldRepository;

/**
 * Field
 * Lĩnh vực
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/field")
public class FieldController extends AbstractSimpleCRUDController<Field, FieldRepository, Long>
		implements IReadNameController<Field, FieldRepository, Long> {

	@Override
	protected Class<Field> entityClass() {
		return Field.class;
	}

}
