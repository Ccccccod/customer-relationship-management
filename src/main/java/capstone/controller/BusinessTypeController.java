/**
 * 
 */
package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.BusinessType;
import capstone.service.BusinessTypeService;

/**
 * BusinessTypeController
 * @author Tuna
 */
@RestController
@RequestMapping("/api/businessType")
public class BusinessTypeController implements IReadNameController<BusinessType, BusinessTypeService, Long> {
	
	@Autowired
	private BusinessTypeService businessTypeService;

	@Override
	public BusinessTypeService getService() {
		return businessTypeService;
	}

}
