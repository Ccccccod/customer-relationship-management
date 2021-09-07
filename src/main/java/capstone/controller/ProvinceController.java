/**
 * 
 */
package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.Province;
import capstone.service.ProvinceService;

/**
 * ProvinceController
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

}
