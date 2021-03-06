/**
 * 
 */
package capstone.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import capstone.dto.request.deserializer.IdDeserializer;
import capstone.dto.request.deserializer.IntegerDeserializer;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Product Info Dto
 * Thông tin hàng hóa Dto
 * @author Tuna
 */
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)

@NotNull
public class ProductInfoDto extends BaseDto<Long> {

	@JsonDeserialize(using = IdDeserializer.class)
	@JsonAlias("product")
	private Long productId;

	private String productCode;

	private String explanation;

	@JsonDeserialize(using = IdDeserializer.class)
	@JsonAlias("unit")
	private Long unitId;

	@NotNull
	@Positive
	@JsonDeserialize(using = IntegerDeserializer.class)
	private Integer amount;

	@NotNull
	private Long price;

	@NotNull
	private Integer discount;

	private Integer vat;

}
