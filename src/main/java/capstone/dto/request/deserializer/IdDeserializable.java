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
 * IdDeserializable
 * @author Tuna
 */
@SuppressWarnings("serial")
public class IdDeserializable extends StdDeserializer<Long> {

	public IdDeserializable(Class<Long> vc) {
		super(vc);
	}

	public IdDeserializable() {
		super(Long.class);
	}

	@Override
	public Long deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
		// Node is Long
		if (node.canConvertToLong()) {
			return node.asLong();
		// Node is object with Long id field
		} else if (node.isObject() && node.get("id") != null && node.get("id").canConvertToLong()) {
			return node.get("id").asLong();
		}
		//
		throw new JsonParseException(jp, "Can not parse node to Long: " + node.toString());
	}

}
