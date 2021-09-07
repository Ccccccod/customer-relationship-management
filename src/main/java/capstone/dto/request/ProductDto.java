/**
 * 
 */
package capstone.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import capstone.dto.request.deserializer.IdDeserializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Hàng hóa Dto
 * @author Tuna
 *
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ProductDto extends CodedNamedDto<Long> {
	
	/**
	 * ID Loại hàngg hóa
	 */
	@JsonDeserialize(using = IdDeserializable.class)
	@JsonAlias("productType")
	private Long productTypeId;

	/**
	 * Diễn giải khi bán
	 */
	private String explanation;

	/**
	 * Đơn vị
	 */
	private String unit;

	/**
	 * Đơn giá bán
	 */
	@PositiveOrZero
	private Long sellPrice;

	/**
	 * Đơn giá bán 1
	 */
	@PositiveOrZero
	private Long sellPrice1;

	/**
	 * Đơn giá bán 2
	 */
	@PositiveOrZero
	private Long sellPrice2;

	/**
	 * Đơn giá bán cố định
	 */
	@PositiveOrZero
	private Long permanentPrice;

	/**
	 * Đơn giá mua
	 */
	@PositiveOrZero
	private Long buyPrice;

	/**
	 * Ưu tiên nhập đơn giá sau thuế
	 */
	private Boolean enterUnitPriorityAfterTax;

	/**
	 * Thuế GTGT
	 */
	@Min(0)
	@Max(100)
	private Integer vat;

	/**
	 * Ngầm định ghi nhân DS trước thuế
	 */
	private Boolean implicitRecord;

	/**
	 * Đơn giá chi phí
	 */
	@PositiveOrZero
	private Long costUnitPrice;

	/**
	 * @param id
	 * @param name
	 * @param code
	 * @param productTypeId
	 * @param explanation
	 * @param unit
	 * @param sellPrice
	 * @param sellPrice1
	 * @param sellPrice2
	 * @param permanentPrice
	 * @param buyPrice
	 * @param enterUnitPriorityAfterTax
	 * @param vat
	 * @param implicitRecord
	 * @param costUnitPrice
	 */
	@Builder
	public ProductDto(Long id, String name, String code, Long productTypeId, String explanation, String unit,
			Long sellPrice, Long sellPrice1, Long sellPrice2, Long permanentPrice, Long buyPrice,
			Boolean enterUnitPriorityAfterTax, Integer vat, Boolean implicitRecord, Long costUnitPrice) {
		super(id, name, code);
		this.productTypeId = productTypeId;
		this.explanation = explanation;
		this.unit = unit;
		this.sellPrice = sellPrice;
		this.sellPrice1 = sellPrice1;
		this.sellPrice2 = sellPrice2;
		this.permanentPrice = permanentPrice;
		this.buyPrice = buyPrice;
		this.enterUnitPriorityAfterTax = enterUnitPriorityAfterTax;
		this.vat = vat;
		this.implicitRecord = implicitRecord;
		this.costUnitPrice = costUnitPrice;
	}

}
