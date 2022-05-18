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

import capstone.entity.Province;
import capstone.exception.ResourceNotFoundException;
import capstone.service.ProvinceService;

/**
 * ProvinceController
 * Tỉnh Controller
 * @author Tuna
 */
@RestController
@RequestMapping("/api/province")
public class ProvinceController implements IReadNameController<Province, ProvinceService, Long> {
	
	@Autowired
	private ProvinceService provinceService;

	@Override
	public ProvinceService getService() {
		return provinceService;
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Province>> search(@RequestParam(name = "countryId") Long countryId) throws ResourceNotFoundException {
		List<Province> provinces = provinceService.findByCountryId(countryId);
		return ResponseEntity.ok(provinces);
	}

}
