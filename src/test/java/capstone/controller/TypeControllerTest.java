/**
 * 
 */
package capstone.controller;

import java.util.Arrays;
import java.util.List;

import capstone.entity.Type;
import capstone.repository.TypeRepository;

/**
 * @author DELL
 *
 */
public class TypeControllerTest extends AbstractSimpleCRUDControllerTest<Type, TypeRepository, TypeController, Long> {

	@Override
	protected String url() {
		return "/api/type";
	}

	@Override
	protected List<Type> resources() {
		return Arrays.asList(new Type("nhap bua") ,
				new Type("Nhap dung") ,
				new Type("Nhap chuan"));
		
	}

	@Override
	protected Type resource() {
		Type type = new Type("bua di");
		type.setId(1L);
		return type;
	}

}
