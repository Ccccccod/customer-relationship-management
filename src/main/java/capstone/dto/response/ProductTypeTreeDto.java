/**
 * 
 */
package capstone.dto.response;

import java.util.Set;

import javax.validation.constraints.NotNull;

import capstone.dto.request.CodedNamedDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Product Type to convert to Product Type tree json
 * @author Tuna
 */
@AllArgsConstructor
@Getter
@Setter
public class ProductTypeTreeDto extends CodedNamedDto<Long>{
	
	private Set<ProductTypeTreeDto> productTypeTreeDtos;

	/**
	 * @param id
	 * @param name
	 * @param code
	 * @param productTypeTreeDtos
	 */
	@Builder
	public ProductTypeTreeDto(Long id, @NotNull(message = "Name must not be null") String name,
			@NotNull(message = "Code must not be null") String code, Set<ProductTypeTreeDto> productTypeTreeDtos) {
		super(id, name, code);
		this.productTypeTreeDtos = productTypeTreeDtos;
	}

}
