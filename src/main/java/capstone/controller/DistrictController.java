/**
 * 
 */
package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.District;
import capstone.service.DistrictService;

/**
 * DistrictController
 * Quận Huyện Controller
 * @author Tuna
 */
@RestController
@RequestMapping("/api/district")
public class DistrictController implements IReadNameController<District, DistrictService, Long> {
	
	@Autowired
	private DistrictService districtService;

	@Override
	public DistrictService getService() {
		return districtService;
	}

}
