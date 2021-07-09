/**
 * 
 */
package capstone.dto.response.serializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * ShortDateSerializer
 * @author Tuna
 *
 */
public class ShortDateSerializer extends StdSerializer<Date> {
	private static final long serialVersionUID = 1L;

	/**
	 * {@link ThreadLocal} because {@link SimpleDateFormat} is not thread safe
	 */
	private static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = ThreadLocal
			.withInitial(() -> new SimpleDateFormat("dd-MM-yyyy hh:mm:ss"));;
    
	public ShortDateSerializer() {
		this(null);
	}

	public ShortDateSerializer(Class<Date> t) {
		super(t);
	}

	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		SimpleDateFormat formatter = ShortDateSerializer.simpleDateFormatThreadLocal.get();
		if (Objects.isNull(formatter) || Objects.isNull(value))
			gen.writeNull();
		else
			gen.writeString(formatter.format(value));
	}

}
