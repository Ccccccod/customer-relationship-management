/**
 * 
 */
package capstone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.common.enums.Gender;

/**
 * GenderController
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/gender")
public class GenderController implements EnumController<Gender> {

	@Override
	public Class<Gender> getEnumClass() {
		return Gender.class;
	}

}
