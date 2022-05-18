/**
 * 
 */
package capstone.dto.response;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import capstone.model.Coded;
import capstone.model.Identifiable;
import capstone.model.Named;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Product Type to convert to Product Type tree json
 * @author Tuna
 */
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@Getter
@Setter
public class ProductTypeTreeResponse implements Identifiable<Long>, Coded, Named {
	
	private Long id;
	
	private String code;
	
	private String name;
	
	@JsonProperty("children")
	private Set<ProductTypeTreeResponse> productTypeTreeDtos;

}
