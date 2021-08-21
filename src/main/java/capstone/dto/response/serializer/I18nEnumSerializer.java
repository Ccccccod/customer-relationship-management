/**
 * 
 */
package capstone.dto.response.serializer;

import java.io.IOException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import capstone.i18n.I18nConfig;
import capstone.i18n.I18nEnum;
import lombok.Getter;

/**
 * I18nEnumSerializer
 * Json Serializer for {@link I18nEnum}
 * @author Tuna
 *
 */
public class I18nEnumSerializer<T extends Enum<T> & I18nEnum<T>> extends StdSerializer<T> {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param t
	 */
	public I18nEnumSerializer(Class<T> t) {
		super(t);
		if (Objects.isNull(messageSource)) {
			messageSource = new I18nConfig().i18nEnumMessageSource();
		}
	}

	public I18nEnumSerializer() {
		this(null);
	}
	
	@Getter
	@Autowired
	@Qualifier("i18nEnumMessageSource")
	protected MessageSource messageSource;

	@Override
	public void serialize(T value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		if (Objects.isNull(value)) {
			gen.writeNull();
			return;
		}
		gen.writeStartObject();
		gen.writeStringField("id", value.name());
		String localed;
		if (value instanceof I18nEnum && //
				(localed = this.getMessageSource().getMessage(((I18nEnum<?>) value).getMessageKey(value), null,
						LocaleContextHolder.getLocale())) != null) {}
		else localed = value.name();
		gen.writeStringField("name", localed);
		gen.writeEndObject();
	}

}
