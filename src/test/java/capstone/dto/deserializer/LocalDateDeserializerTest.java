/**
 * 
 */
package capstone.dto.deserializer;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;

import capstone.dto.request.deserializer.LocalDateDeserializer;
import lombok.SneakyThrows;

/**
 * LocalDateDeserializerTest
 * @author Tuna
 */
class LocalDateDeserializerTest {
	
	private ObjectMapper mapper;
	private LocalDateDeserializer deserializer;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		mapper = new ObjectMapper();
		deserializer = new LocalDateDeserializer(LocalDate.class);
	}

	@Test
	void deserialize() {
        String json = String.format("\"%s\"", "2021-09-10T15:50:54.000");
        LocalDate date = deserialiseNumber(json);
        assertThat(date).isInstanceOf(LocalDate.class);
        assertThat(date).isEqualTo(LocalDate.of(2021, 9, 10));
	}

    @SneakyThrows({JsonParseException.class, IOException.class})
    private LocalDate deserialiseNumber(String json) {
        InputStream stream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        JsonParser parser = mapper.getFactory().createParser(stream);
        DeserializationContext ctxt = mapper.getDeserializationContext();
        return deserializer.deserialize(parser, ctxt);
    }

}
