/**
 * 
 */
package capstone.controller;

import java.util.Arrays;
import java.util.List;

import capstone.entity.Source;
import capstone.repository.SourceRepository;

/**
 * SourceControllerTest
 * @author Tuna
 *
 */
public class SourceControllerTest extends AbstractSimpleCRUDControllerTest<Source, SourceRepository, SourceController, Long> {

	@Override
	protected String url() {
		return "/api/source";
	}

	@Override
	protected List<Source> resources() {
		return Arrays.asList(new Source("Khách hàng hoặc đối tác giới thiệu"),
				new Source("Nhân viên kinh doanh tự tìm kiếm"), 
				new Source("Thông qua sự kiện, hội thảo, tập huấn"),
				new Source("Khách hàng tự tìm đến"), 
				new Source("Khác")
				);
	}

	@Override
	protected Source resource() {
		return Source.builder()
				.id(1L)
				.name("Khách hàng hoặc đối tác giới thiệu")
				.build();
	}

}
