/**
 * 
 */
package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.Career;
import capstone.service.CareerService;

/**
 * CareerController
 * Ngành nghề Controller
 * @author Tuna
 */
@RestController
@RequestMapping("/api/career")
public class CareerController implements IReadNameController<Career, CareerService, Long> {
	
	@Autowired
	private CareerService careerService;

	@Override
	public CareerService getService() {
		return careerService;
	}

}
