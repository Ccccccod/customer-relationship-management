/**
 * 
 */
package capstone.dto.serializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import capstone.dto.response.serializer.I18nEnumSerializer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Tuna
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Car {
	
	@JsonSerialize(using = I18nEnumSerializer.class)
	EnumTest enumTest;

}
