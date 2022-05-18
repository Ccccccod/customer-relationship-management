/**
 * 
 */
package capstone.dto.deserializer;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;

import capstone.dto.request.deserializer.IdSetDeserializer;
import lombok.SneakyThrows;

/**
 * IdSetDeserializerTest
 * @author Tuna
 */
class IdSetDeserializerTest {
	
	private ObjectMapper mapper;
	private IdSetDeserializer deserializer;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		mapper = new ObjectMapper();
		deserializer = new IdSetDeserializer();
	}

	@Test
	void deserializeNodesToLong() {
		// Number
        String json = String.format("[%d, \"%d\", {\"id\": %d}, {\"id\": \"%d\"}]", 1L, 2L, 3L, 4L);
        Set<Long> set = deserialiseNumber(json);
        assertThat(set).isInstanceOf(Set.class);
        assertThat(set).isEqualTo(new LinkedHashSet<Long>(Arrays.asList(1L, 2L, 3L, 4L)));
	}

    @SneakyThrows({JsonParseException.class, IOException.class})
    private Set<Long> deserialiseNumber(String json) {
        InputStream stream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        JsonParser parser = mapper.getFactory().createParser(stream);
        DeserializationContext ctxt = mapper.getDeserializationContext();
//        parser.nextToken();
//        parser.nextToken();
//        parser.nextToken();
        return deserializer.deserialize(parser, ctxt);
    }

}
