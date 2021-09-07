/**
 * 
 */
package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.Department;
import capstone.service.DepartmentService;

/**
 * DepartmentController
 * Phòng ban Controller
 * @author Tuna
 */
@RestController
@RequestMapping("/api/department")
public class DepartmentController implements IReadNameController<Department, DepartmentService, Long> {
	
	@Autowired
	private DepartmentService departmentService;

	@Override
	public DepartmentService getService() {
		return departmentService;
	}

}
