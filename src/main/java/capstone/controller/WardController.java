/**
 * 
 */
package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.Ward;
import capstone.service.WardService;

/**
 * WardController
 * @author Tuna
 */
@RestController
@RequestMapping("/api/ward")
public class WardController implements IReadNameController<Ward, WardService, Long> {

	@Autowired
	private WardService wardService;

	@Override
	public WardService getService() {
		return wardService;
	}

}
