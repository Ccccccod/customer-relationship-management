/**
 * 
 */
package capstone.dto.request.deserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * DateDeserializer
 * @author Tuna
 *
 */
public class DateDeserializer extends StdDeserializer<Date> {
	private static final long serialVersionUID = 1L;

	/**
	 * {@link ThreadLocal} because {@link SimpleDateFormat} is not thread safe
	 */
	private static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = ThreadLocal
			.withInitial(() -> new SimpleDateFormat("dd-MM-yyyy hh:mm:ss"));;

	/**
	 * @param vc
	 */
	public DateDeserializer(Class<?> vc) {
		super(vc);
	}

	public DateDeserializer() {
		this(null);
	}

	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		SimpleDateFormat formatter = simpleDateFormatThreadLocal.get();
		if (Objects.isNull(formatter) || Objects.isNull(p) || Objects.isNull(p.getText())) {
			return null;
		}
		try {
			return formatter.parse(p.getText());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

}
