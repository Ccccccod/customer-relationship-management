/**
 * 
 */
package capstone.controller;

import java.util.Arrays;
import java.util.List;

import capstone.entity.Career;
import capstone.repository.CareerRepository;

/**
 * @author DELL
 *
 */
public class CareerControllerTest
		extends AbstractSimpleCRUDControllerTest<Career, CareerRepository, CareerController, Long> {

	@Override
	protected String url() {
		return "/api/career";
	}

	@Override
	protected List<Career> resources() {
		return Arrays.asList(new Career("nhap dung"),
				new Career("nhap chuan") ,
				new Career("khong sai ty gi"));
	}

	@Override
	protected Career resource() {
		Career career = new Career("quen ten");
		career.setId(1L);
		return career;
	}

}
