/**
 * 
 */
package capstone.dto.request.deserializer;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

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
public class IdsDeserializable extends StdDeserializer<Set<Long>> {

	public IdsDeserializable(Class<Set<Long>> vc) {
		super(vc);
	}

	public IdsDeserializable() {
		super(Set.class);
	}

	@Override
	public Set<Long> deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
		if (node.isArray()) {
			Set<Long> result = new LinkedHashSet<>();
			node.elements().forEachRemaining(n -> {
				if (n.canConvertToLong()) 
					result.add(n.asLong());
				else if (n.isObject() && n.get("id") != null && n.get("id").canConvertToLong())
					result.add(n.get("id").asLong());
			});
			return result;
		}
		return new LinkedHashSet<>();
	}

}
