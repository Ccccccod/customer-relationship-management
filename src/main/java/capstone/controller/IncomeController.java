/**
 * 
 */
package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.Income;
import capstone.service.IncomeService;

/**
 * IncomeController
 * Doanh thu Controller
 * @author Tuna
 */
@RestController
@RequestMapping("/api/income")
public class IncomeController implements IReadNameController<Income, IncomeService, Long> {
	
	@Autowired
	private IncomeService incomeService;

	@Override
	public IncomeService getService() {
		return incomeService;
	}

}
