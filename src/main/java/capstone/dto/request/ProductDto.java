/**
 * 
 */
package capstone.dto.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Tuna
 *
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ProductDto extends CodedNamedDto<Long> {
	
	/**
	 * ID Loại hàngg hóa
	 */
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
	private Long sellPrice;

	/**
	 * Đơn giá bán 1
	 */
	private Long sellPrice1;

	/**
	 * Đơn giá bán 2
	 */
	private Long sellPrice2;

	/**
	 * Đơn giá bán cố định
	 */
	private Long permanentPrice;

	/**
	 * Đơn giá mua
	 */
	private Long buyPrice;

	/**
	 * Ưu tiên nhập đơn giá sau thuế
	 */
	private Boolean enterUnitPriorityAfterTax;

	/**
	 * Thuế GTGT
	 */
	private String vat;

	/**
	 * Ngầm định ghi nhân DS trước thuế
	 */
	private Boolean implicitRecord;

	/**
	 * Đơn giá chi phí
	 */
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
	public ProductDto(Long id, @NotNull(message = "Name must not be null") String name,
			@NotNull(message = "Code must not be null") String code, Long productTypeId, String explanation,
			String unit, Long sellPrice, Long sellPrice1, Long sellPrice2, Long permanentPrice, Long buyPrice,
			Boolean enterUnitPriorityAfterTax, String vat, Boolean implicitRecord, Long costUnitPrice) {
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
