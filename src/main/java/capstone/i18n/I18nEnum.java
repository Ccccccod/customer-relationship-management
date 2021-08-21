/**
 * 
 */
package capstone.i18n;

import java.io.IOException;
import java.io.Serializable;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import capstone.dto.response.serializer.I18nEnumSerializer;
import capstone.utils.JsonDeserializable;

/**
 * I18nEnum<br>
 * An interface for {@link Enum} with <code>getMessageKey</code> method that
 * returns a {@link String} to be used to search for message in properties files
 * for localization.
 * @author Tuna
 *
 */
@JsonSerialize(using = I18nEnumSerializer.class)
public interface I18nEnum<E> extends Serializable, JsonDeserializable<E> {
	
	/**
	 * @param e {@link Enum} to make message key
	 * @return message key of specified {@link Enum}
	 */
	default String getMessageKey(Enum<?> e) {
		return e.getClass().getSimpleName() + '.' + e.name().toLowerCase();
	}
	
	@Override
	default E deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
