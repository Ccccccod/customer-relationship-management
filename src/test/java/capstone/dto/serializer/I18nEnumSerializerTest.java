/**
 * 
 */
package capstone.dto.serializer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import capstone.common.enums.OpportunityPhase;
import capstone.dto.request.OpportunityDto;
import capstone.entity.Opportunity;

/**
 * @author Tuna
 *
 */
class I18nEnumSerializerTest {
	
	private ObjectMapper om = new ObjectMapper();
	
	private JsonFactory jsonFactory;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		jsonFactory = new JsonFactory();
	}

	@Test
	void testSerialize() throws JsonProcessingException {
		Car car = new Car(EnumTest.Test);
		String stringTest = om.writeValueAsString(car);
		System.out.println(stringTest);
		assertTrue(stringTest.contains("name"));
	}
	
	@Test
	void testDeserialize() throws IOException {
		StringWriter test = new StringWriter();
		JsonGenerator jg = jsonFactory.createGenerator(test);
		jg.writeString("Test");
		jg.close();
		EnumTest enumTest = om.readValue(test.toString(), EnumTest.class);
		System.out.println(enumTest);
		OpportunityDto opportunityDto = om.readValue("{\r\n" + 
				"    \"name\": \"Bán hàng cho Công ty TNHH Eurodoor\",\r\n" + 
				"    \"customerId\": 1,\r\n" + 
				"    \"contactId\": 1,\r\n" + 
				"    \"moneyAmount\": 24035000,\r\n" + 
				"    \"opportunityPhaseId\": 3,\r\n" + 
				"    \"eeeOpportunityPhaseId\": \"BEGINNING\",\r\n" + 
				"    \"successRate\": 70,\r\n" + 
				"    \"expectedEndDate\": \"2022-10-06\",\r\n" + 
				"    \"expectedTurnOver\": 16824500,\r\n" + 
				"    \"sourceId\": 2\r\n" + 
				"}", OpportunityDto.class);
		System.out.println(opportunityDto);
	}

}
