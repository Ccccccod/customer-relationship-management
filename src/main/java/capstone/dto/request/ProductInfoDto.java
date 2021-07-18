/**
 * 
 */
package capstone.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Product Info Dto
 * Thông tin hàng hóa Dto
 * @author Tuna
 *
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)

@NotNull(message = "ProductInfoDto must not be null")
public class ProductInfoDto {

	@NotNull(message = "productCode must not be null")
	private String productCode;

	private String explanation;

	private String unit;

	@NotNull(message = "amount must not be null")
	@Positive(message = "amount must be positive")
	private Integer amount;

	@NotNull(message = "price must not be null")
	private Long price;

	@NotNull(message = "discount must not be null")
	private Integer discount;

	private Integer vat;

}
