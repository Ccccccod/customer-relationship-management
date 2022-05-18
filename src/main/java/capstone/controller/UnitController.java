/**
 * 
 */
package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.Unit;
import capstone.service.UnitService;

/**
 * UnitController
 * @author Tuna
 */
@RestController
@RequestMapping("/api/unit")
public class UnitController implements IReadNameController<Unit, UnitService, Long> {
	
	@Autowired
	private UnitService unitService;

	@Override
	public UnitService getService() {
		return unitService;
	}

}
