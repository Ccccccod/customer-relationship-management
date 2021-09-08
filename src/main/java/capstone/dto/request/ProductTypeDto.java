/**
 * 
 */
package capstone.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import capstone.dto.request.deserializer.IdDeserializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Loại hàng hóa Dto
 * @author Tuna
 *
 */
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ProductTypeDto extends CodedNamedDto<Long> {

	/**
	 * Loại hàng hóa
	 */
	@JsonDeserialize(using = IdDeserializable.class)
	@JsonAlias("productType")
	private Long productTypeId;

	/**
	 * @param id
	 * @param name
	 * @param code
	 * @param productTypeId
	 */
	public ProductTypeDto(Long id, String name, String code, Long productTypeId) {
		super(id, name, code);
		this.productTypeId = productTypeId;
	}

}
