/**
 * 
 */
package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.Position;
import capstone.service.PositionService;

/**
 * PositionController
 * Chức danh Controller
 * @author Tuna
 */
@RestController
@RequestMapping("/api/position")
public class PositionController implements IReadNameController<Position, PositionService, Long> {
	
	@Autowired
	private PositionService positionService;

	@Override
	public PositionService getService() {
		return positionService;
	}

}
