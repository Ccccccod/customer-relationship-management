/**
 * 
 */
package capstone.dto.response.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import capstone.entity.ProductType;

/**
 * ProductTypeSerializer
 * @author Tuna
 *
 */
public class ProductTypeSerializer extends StdSerializer<ProductType> {
	private static final long serialVersionUID = 1L;

	public ProductTypeSerializer(Class<ProductType> t) {
		super(t);
	}

	public ProductTypeSerializer() {
		this(ProductType.class);
	}

	@Override
	public void serialize(ProductType value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("id", value.getId());
		gen.writeStringField("name", value.getName());
		gen.writeEndObject();
	}

}
