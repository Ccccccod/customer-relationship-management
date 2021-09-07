/**
 * 
 */
package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.Country;
import capstone.service.CountryService;

/**
 * CountryController
 * @author Tuna
 */
@RestController
@RequestMapping("/api/country")
public class CountryController implements IReadNameController<Country, CountryService, Long> {
	
	@Autowired
	private CountryService countryService;

	@Override
	public CountryService getService() {
		return countryService;
	}

}
