/**
 * 
 */
package capstone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.District;
import capstone.exception.ResourceNotFoundException;
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
	
	@GetMapping("/search")
	public ResponseEntity<List<District>> search(@RequestParam(name = "provinceId") Long provinceId)
			throws ResourceNotFoundException {
		List<District> districts = districtService.findByProvinceId(provinceId);
		return ResponseEntity.ok(districts);
	}

}
