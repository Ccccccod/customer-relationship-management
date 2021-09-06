/**
 * 
 */
package capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.entity.Source;
import capstone.service.SourceService;

/**
 * SourceController Nguồn gốc Controller
 * 
 * @author Tuna
 * @author minhkien
 */
@RestController
@RequestMapping("/api/source")
public class SourceController implements IReadNameController<Source, SourceService, Long> {

	@Autowired
	private SourceService sourceService;

	@Override
	public SourceService getService() {
		return sourceService;
	}

}
