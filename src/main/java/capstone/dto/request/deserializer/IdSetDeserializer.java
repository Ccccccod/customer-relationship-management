/**
 * 
 */
package capstone.dto.request.deserializer;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * IdSetDeserializer
 * @author Tuna
 */
@SuppressWarnings("serial")
public class IdSetDeserializer extends StdDeserializer<Set<Long>> {

	public IdSetDeserializer(Class<Set<Long>> vc) {
		super(vc);
	}

	public IdSetDeserializer() {
		super(Set.class);
	}

	@Override
	public Set<Long> deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
		try {
			if (node.isArray()) {
				Set<Long> result = new LinkedHashSet<>();
				Iterable<JsonNode> elementNodes = () -> node.elements();
				for (JsonNode elementNode : elementNodes) {
					if (elementNode.isObject() && elementNode.get("id") != null) {
						// If node is object with Long id field
						elementNode = elementNode.get("id");
					}
					if (elementNode.isNull()) {
						result.add(null);
						
					} else if (elementNode.canConvertToLong()) {
						result.add(elementNode.asLong());
						
					} else if (elementNode.isTextual()) {
						String nodeValue = elementNode.textValue();
						try {
							result.add(Long.parseLong(nodeValue));
						} catch (NumberFormatException e) {
							throw new JsonParseException(jp, "Can not parse text node to Long: " + nodeValue);
						}
					}
				}
				return result;
			}
		} catch (JsonParseException e) {
			throw new JsonParseException(jp, "Can not parse node to Long: " + node.toString(), e);
		}
		
		throw new JsonParseException(jp, "Can not parse node to Long: " + node.toString());
	}

}
