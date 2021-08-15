/**
 * 
 */
package capstone;

import java.io.IOException;
import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;

import capstone.common.enums.OpportunityPhase;
import capstone.dto.response.serializer.I18nEnumSerializer;

/**
 * AbstractTest
 * @author Tuna
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BackEndCapstoneApplication.class)
@WebAppConfiguration
public abstract class AbstractTest {

	@Autowired
	WebApplicationContext webApplicationContext;

	protected MockMvc mockMvc;
	
	protected ObjectMapper objectMapper;
	
	protected ObjectMapper dtoMapper;

	@BeforeEach
	protected void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		objectMapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addSerializer(OpportunityPhase.class, new I18nEnumSerializer<OpportunityPhase>());
		objectMapper.registerModule(module);
//		objectMapper.getFactory().configure(JsonWriteFeature.ESCAPE_NON_ASCII.mappedFeature(), true);
		
		dtoMapper = new ObjectMapper();
		dtoMapper.disable(MapperFeature.USE_ANNOTATIONS);
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		return objectMapper.writeValueAsString(obj);
	}

	protected String mapToJsonDto(Object obj) throws JsonProcessingException {
		JsonNode json = objectMapper.readTree(objectMapper.writeValueAsString(obj));
		return editJson(json, obj).toString();
	}
	
	/**
	 * Override this to custom json string that is mapped from resource(s) to be
	 * used as body when calling api
	 * @param node jsonNode from obj
	 * @param obj  object being mapped
	 * @return node after editing
	 */
	protected JsonNode editJson(JsonNode node, Object obj) {
		// map enum to string json
		for (Field field : obj.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			try {
				if (field.getType().isEnum() && field.get(obj) != null) {
					((ObjectNode) node).put(field.getName(), ((Enum<?>)field.get(obj)).name() );
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return node;
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		return objectMapper.readValue(json, clazz);
	}
}
