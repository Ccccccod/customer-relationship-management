/**
 * 
 */
package capstone.dto.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
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
	public ProductTypeDto(Long id, @NotNull(message = "Name must not be null") String name,
			@NotNull(message = "Code must not be null") String code, Long productTypeId) {
		super(id, name, code);
		this.productTypeId = productTypeId;
	}

}
