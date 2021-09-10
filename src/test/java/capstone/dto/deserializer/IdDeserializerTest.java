/**
 * 
 */
package capstone.dto.deserializer;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;

import capstone.dto.request.deserializer.IdDeserializer;
import lombok.SneakyThrows;

/**
 * IdDeserializerTest
 * @author Tuna
 */
class IdDeserializerTest {
	
	private ObjectMapper mapper;
	private IdDeserializer deserializer;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		mapper = new ObjectMapper();
		deserializer = new IdDeserializer(Long.class);
	}

	@Test
	void deserializeNumberNodeToLong() {
		// Number
        String json = String.format("%s", "1");
        Long deserialisedNumber = deserialiseNumber(json);
        assertThat(deserialisedNumber).isInstanceOf(Long.class);
        assertThat(deserialisedNumber).isEqualTo(1L);
	}

	@Test
	void deserializeTextNodeToLong() {
		// Text
        String json = String.format("%s", "\"10\"");
        Long deserialisedNumber = deserialiseNumber(json);
        assertThat(deserialisedNumber).isInstanceOf(Long.class);
        assertThat(deserialisedNumber).isEqualTo(10L);
	}

	@Test
	void deserializeObjectNodeToLong() {
		// Object
        String json = String.format("{\"id\": %s}", "\"1\"");
        Long deserialisedNumber = deserialiseNumber(json);
        assertThat(deserialisedNumber).isInstanceOf(Long.class);
        assertThat(deserialisedNumber).isEqualTo(1L);
	}

	@Test
	void deserializeNullNodeToLong() {
		// Object
        String json = String.format("{\"id\": %s}", "null");
        Long deserialisedNumber = deserialiseNumber(json);
        assertThat(deserialisedNumber).isEqualTo(null);
	}

    @SneakyThrows({JsonParseException.class, IOException.class})
    private Long deserialiseNumber(String json) {
        InputStream stream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        JsonParser parser = mapper.getFactory().createParser(stream);
        DeserializationContext ctxt = mapper.getDeserializationContext();
//        parser.nextToken();
//        parser.nextToken();
//        parser.nextToken();
        return deserializer.deserialize(parser, ctxt);
    }

}
