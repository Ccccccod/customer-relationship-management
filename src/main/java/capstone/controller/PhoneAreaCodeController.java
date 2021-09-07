/**
 * 
 */
package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.PhoneAreaCode;
import capstone.service.PhoneAreaCodeService;

/**
 * PhoneAreaCodeController
 * @author Tuna
 */
@RestController
@RequestMapping("/api/phoneAreaCode")
public class PhoneAreaCodeController implements IReadNameController<PhoneAreaCode, PhoneAreaCodeService, Long> {
	
	@Autowired
	private PhoneAreaCodeService phoneAreaCodeService;

	@Override
	public PhoneAreaCodeService getService() {
		return phoneAreaCodeService;
	}

}
