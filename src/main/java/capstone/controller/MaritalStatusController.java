/**
 * 
 */
package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.MaritalStatus;
import capstone.service.MaritalStatusService;

/**
 * MaritalStatusController
 * Tình trạng hôn nhân Controller
 * @author Tuna
 */
@RestController
@RequestMapping("/api/maritalStatus")
public class MaritalStatusController implements IReadNameController<MaritalStatus, MaritalStatusService, Long> {
	
	@Autowired
	private MaritalStatusService maritalStatusService;

	@Override
	public MaritalStatusService getService() {
		return maritalStatusService;
	}

}
