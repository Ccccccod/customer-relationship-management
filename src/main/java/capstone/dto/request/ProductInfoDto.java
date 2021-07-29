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

@NotNull
public class ProductInfoDto {

	@NotNull
	private String productCode;

	private String explanation;

	private String unit;

	@NotNull
	@Positive
	private Integer amount;

	@NotNull
	private Long price;

	@NotNull
	private Integer discount;

	private Integer vat;

}
