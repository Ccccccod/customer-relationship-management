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

import capstone.entity.Ward;
import capstone.exception.ResourceNotFoundException;
import capstone.service.WardService;

/**
 * WardController
 * Phường Xã Controller
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
	
	@GetMapping("/search")
	public ResponseEntity<List<Ward>> search(@RequestParam(name = "districtId") Long districtId) throws ResourceNotFoundException {
		List<Ward> wards = wardService.findByDistrictId(districtId);
		return ResponseEntity.ok(wards);
	}

}
