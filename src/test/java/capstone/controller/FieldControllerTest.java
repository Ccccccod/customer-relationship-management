/**
 * 
 */
package capstone.controller;

import java.util.Arrays;
import java.util.List;

import capstone.entity.Field;
import capstone.repository.FieldRepository;

/**
 * @author DELL
 *
 */
public class FieldControllerTest
		extends AbstractSimpleCRUDControllerTest<Field, FieldRepository, FieldController, Long> {

	@Override
	protected String url() {
		return "/api/field";
	}

	@Override
	protected List<Field> resources() {
		return Arrays.asList(new Field("nhap dung") ,
				new Field("nhap chuan") ,
				new Field("khong sai"));
	}

	@Override
	protected Field resource() {
		Field field = new Field("nhap chuan");
		field.setId(1L);
		return field ;
	}

}
