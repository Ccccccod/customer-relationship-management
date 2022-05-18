/**
 * 
 */
package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.Gender;
import capstone.service.GenderService;

/**
 * GenderController
 * Giới tính Controller
 * @author Tuna
 */
@RestController
@RequestMapping("/api/gender")
public class GenderController implements IReadNameController<Gender, GenderService, Long> {
	
	@Autowired
	private GenderService genderService;

	@Override
	public GenderService getService() {
		return genderService;
	}

}
