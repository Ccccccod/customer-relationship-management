/**
 * 
 */
package capstone.dto.response.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import capstone.entity.User;

/**
 * User Serializer
 * Serialize User to json object with id and name
 * @author Tuna
 *
 */
public class UserSerializer extends StdSerializer<User> {
	private static final long serialVersionUID = 1L;

	public UserSerializer(Class<User> t) {
		super(t);
	}
	
	public UserSerializer() {
		this(User.class);
	}

	@Override
	public void serialize(User user, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("id", user.getId());
//		gen.writeStringField("code", user.getCode());
		gen.writeStringField("username", user.getUsername());
		gen.writeStringField("lastName", user.getLastName());
		gen.writeStringField("name", user.getName());
		gen.writeStringField("fullName", user.getFullName());
		gen.writeStringField("email", user.getEmail());
		gen.writeEndObject();
	}

}
