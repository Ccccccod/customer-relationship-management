/**
 * 
 */
package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.Type;
import capstone.service.TypeService;

/**
 * TypeController
 * Loại hình Controller
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/type")
public class TypeController implements IReadNameController<Type, TypeService, Long> {

	@Autowired
	private TypeService typeService;
	
	@Override
	public TypeService getService() {
		return typeService;
	}

}
