/**
 * 
 */
package capstone.dto.response.serializer;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * LocalTimeSerializer
 * @author Tuna
 */
@SuppressWarnings("serial")
public class LocalTimeSerializer extends StdSerializer<LocalTime> {

	public LocalTimeSerializer(Class<LocalTime> t) {
		super(t);
	}

	public LocalTimeSerializer() {
		this(LocalTime.class);
	}

	@Override
	public void serialize(LocalTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		if (Objects.nonNull(value))
			gen.writeString(value.format(DateTimeFormatter.ISO_LOCAL_TIME));
		else
			gen.writeNull();
	}

}
