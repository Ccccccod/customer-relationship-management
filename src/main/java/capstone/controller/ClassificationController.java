/**
 * 
 */
package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.Classification;
import capstone.service.ClassificationService;

/**
 * ClassificationController
 * Phân loại khách hàng Controller
 * @author Tuna
 *
 */
@RestController
@RequestMapping("/api/classification")
public class ClassificationController
		implements IReadNameController<Classification, ClassificationService, Long> {
	
	@Autowired
	private ClassificationService classificationService;

	@Override
	public ClassificationService getService() {
		return classificationService;
	}

}
