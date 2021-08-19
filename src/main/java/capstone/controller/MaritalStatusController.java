/**
 * 
 */
package capstone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.common.enums.MaritalStatus;

/**
 * MaritalStatusController
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/maritalStatus")
public class MaritalStatusController implements EnumController<MaritalStatus> {

	@Override
	public Class<MaritalStatus> getEnumClass() {
		return MaritalStatus.class;
	}

}
