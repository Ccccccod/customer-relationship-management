/**
 * 
 */
package capstone.dto.request.deserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.springframework.boot.json.JsonParseException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * LocalDateDeserializer
 * @author Tuna
 */
public class LocalDateDeserializer extends StdDeserializer<LocalDate> {
	private static final long serialVersionUID = 1L;

	public LocalDateDeserializer(Class<LocalDate> vc) {
		super(vc);
	}

	public LocalDateDeserializer() {
		super(LocalDate.class);
	}
	
//	private static final String[] PATTERNS = new String[] {
//			"yyyy-MM-dd'T'HH:mm:ss.SSS",
//			"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
//			"yyyy-MM-dd'T'HH:mm:ss.SSSZ",
//			"yyyy-MM-dd'T'HH:mm:ss.SSS Z",
//			"yyyy/MM/dd'T'HH:mm:ss.SSS",
//			"yyyy-MM-dd",
//	};
//	
//	private static final DateTimeFormatter DATE_TIME_FORMATTER = Arrays.stream(PATTERNS)
//	        .map(DateTimeFormatter::ofPattern)
//	        .reduce(new DateTimeFormatterBuilder(), 
//	                DateTimeFormatterBuilder::appendOptional, 
//	                (f1, f2) -> f1.append(f2.toFormatter()))
//	        .toFormatter();

	@Override
	public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		try {
			JsonNode node = jp.getCodec().readTree(jp);
			if (!node.isTextual()) return null;
			if (node.textValue().length() < 10)
				throw new JsonParseException(new DateTimeParseException(null, node.textValue(), 0));
			return LocalDate.parse(node.textValue().substring(0, 10));
		} catch (DateTimeParseException e) {
			throw new JsonParseException(e);
		}
	}

}
