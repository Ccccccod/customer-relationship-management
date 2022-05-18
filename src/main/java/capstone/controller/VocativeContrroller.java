/**
 * 
 */
package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.Vocative;
import capstone.service.VocativeService;

/**
 * VocativeContrroller
 * Xưng hô Controller
 * @author Tuna
 */
@RestController
@RequestMapping("/api/vocative")
public class VocativeContrroller implements IReadNameController<Vocative, VocativeService, Long> {
	
	@Autowired
	private VocativeService vocativeService;

	@Override
	public VocativeService getService() {
		return vocativeService;
	}

}
