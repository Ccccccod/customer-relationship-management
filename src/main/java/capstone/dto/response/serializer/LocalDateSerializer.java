/**
 * 
 */
package capstone.dto.response.serializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * Date serializer
 * @author Tuna
 *
 */
public class LocalDateSerializer extends StdSerializer<LocalDate> {
	private static final long serialVersionUID = 1L;

	public LocalDateSerializer(Class<LocalDate> t) {
		super(t);
	}

	public LocalDateSerializer() {
		this(LocalDate.class);
	}

	@Override
	public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		if (Objects.nonNull(value))
			gen.writeString(value.format(DateTimeFormatter.ISO_LOCAL_DATE));
		else
			gen.writeNull();
	}

}
