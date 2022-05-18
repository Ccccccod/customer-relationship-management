/**
 * 
 */
package capstone.dto.response.serializer;

import java.io.IOException;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import capstone.model.Identifiable;
import capstone.model.Named;

/**
 * IdNameSerializer
 * @author Tuna
 */
public class IdNameSerializer<T extends Object & Identifiable<Long> & Named> extends StdSerializer<T> {
	private static final long serialVersionUID = 1L;

	public IdNameSerializer(Class<T> t) {
		super(t);
	}

	public IdNameSerializer() {
		this(null);
	}

	@Override
	public void serialize(T value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		if (Objects.isNull(value)) {
			gen.writeNull();
			return;
		}
		gen.writeStartObject();
		gen.writeNumberField("id", value.getId());
		gen.writeStringField("name", value.getName());
		gen.writeEndObject();
	}

}
