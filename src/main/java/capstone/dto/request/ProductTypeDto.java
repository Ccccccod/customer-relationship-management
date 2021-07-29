/**
 * 
 */
package capstone.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Loại hàng hóa Dto
 * @author Tuna
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ProductTypeDto extends CodedNamedDto<Long> {
	
	private Long productTypeId;

	/**
	 * @param id
	 * @param name
	 * @param code
	 * @param productTypeId
	 */
	@Builder
	public ProductTypeDto(Long id, String name, String code, Long productTypeId) {
		super(id, name, code);
		this.productTypeId = productTypeId;
	}

}
