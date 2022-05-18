/**
 * 
 */
package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.Field;
import capstone.service.FieldService;

/**
 * Field
 * Lĩnh vực
 * @author Tuna
 */
@RestController
@RequestMapping("/api/field")
public class FieldController implements IReadNameController<Field, FieldService, Long> {

	@Autowired
	private FieldService fieldService;
	
	@Override
	public FieldService getService() {
		return fieldService;
	}
	
}
