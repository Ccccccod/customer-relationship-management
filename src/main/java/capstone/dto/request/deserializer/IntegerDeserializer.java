/**
 * 
 */
package capstone.dto.request.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * IntegerDeserializer
 * @author Tuna
 */
public class IntegerDeserializer extends StdDeserializer<Integer> {
	private static final long serialVersionUID = 1L;

	public IntegerDeserializer(Class<?> vc) {
		super(vc);
	}

	public IntegerDeserializer() {
		this(Integer.class);
	}

	@Override
	public Integer deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
		if (node.isNull()) {
			return null;
		} else if (node.canConvertToInt()) {
			return node.asInt();
		} else if (node.isTextual()) {
			String nodeValue = node.textValue();
			try {
				return Integer.parseInt(nodeValue);
			} catch (NumberFormatException e) {
				throw new JsonParseException(jp, "Can not parse text node to Integer: " + nodeValue, e);
			}
		}
		
		throw new JsonParseException(jp, "Can not parse node to Integer: " + node.toString());
	}

}
