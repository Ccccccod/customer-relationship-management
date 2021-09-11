/**
 * 
 */
package capstone.dto.response;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import capstone.dto.request.CodedNamedDto;
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
public class ProductTypeTreeResponse extends CodedNamedDto<Long>{
	
	@JsonProperty("children")
	private Set<ProductTypeTreeResponse> productTypeTreeDtos;

	/**
	 * @param id
	 * @param name
	 * @param code
	 * @param productTypeTreeDtos
	 */
	public ProductTypeTreeResponse(Long id, String name, String code,
			Set<ProductTypeTreeResponse> productTypeTreeDtos) {
		super(id, name, code);
		this.productTypeTreeDtos = productTypeTreeDtos;
	}

}
