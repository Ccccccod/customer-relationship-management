/**
 * 
 */
package capstone.dto.request.deserializer;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * IdsDeserializable
 * @author Tuna
 */
@SuppressWarnings("serial")
public class IdsDeserializable extends StdDeserializer<List<Long>> {

	public IdsDeserializable(Class<List<Long>> vc) {
		super(vc);
	}

	public IdsDeserializable() {
		super(List.class);
	}

	@Override
	public List<Long> deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
		if (node.isArray()) {
			List<Long> result = new LinkedList<Long>();
			node.elements().forEachRemaining(n -> {
				if (n.canConvertToLong()) 
					result.add(n.asLong());
				else if (n.isObject() && n.get("id") != null && n.get("id").canConvertToLong())
					result.add(n.get("id").asLong());
			});
			return result;
		}
		return Arrays.asList();
	}

}
