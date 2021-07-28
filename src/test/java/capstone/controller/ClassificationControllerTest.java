/**
 * 
 */
package capstone.controller;

import java.util.Arrays;
import java.util.List;

import capstone.entity.Classification;
import capstone.repository.ClassificationRepository;

/**
 * @author DELL
 *
 */
public class ClassificationControllerTest extends
		AbstractSimpleCRUDControllerTest<Classification, ClassificationRepository, ClassificationController, Long> {

	@Override
	protected String url() {
		  return "/api/classification";
	}

	@Override
	protected List<Classification> resources() {
		return Arrays.asList(new Classification("nhap nhieu ") ,
				new Classification("nhap lam") ,
				new Classification("khong sai"));
	}

	@Override
	protected Classification resource() {
		Classification classification = new Classification("qua chuan");
		classification.setId(1L);
		return classification;
	}

}
