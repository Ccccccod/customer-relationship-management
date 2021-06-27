/**
 * 
 */
package capstone.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

@Entity
@Table(name = "Product", //
		uniqueConstraints = { //
		})
public class Product extends NamedEntity<Long> {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Loại hàng hóa
	 */
	@Column(name = "product_type", columnDefinition = "nvarchar(255)")
	private String productType;
	
	/**
	 * Diễn giải khi bán
	 */
	@Column(name = "explanation", columnDefinition = "nvarchar(255)")
	private String explanation;
	
	/**
	 * Đơn vị
	 */
	@Column(name = "unit", columnDefinition = "nvarchar(255)")
	private String unit;
	
	/**
	 * Đơn giá bán
	 */
	@Column(name = "sell_price")
	private Long sellPrice;
	
	/**
	 * Đơn giá bán 1
	 */
	@Column(name = "sell_price1")
	private Long sellPrice1;
	
	/**
	 * Đơn giá bán 2
	 */
	@Column(name = "sell_price2")
	private Long sellPrice2;
	
	/**
	 * Đơn giá bán cố định
	 */
	@Column(name = "permanent_price")
	private Long permanentPrice;
	
	/**
	 * Đơn giá mua
	 */
	@Column(name = "buy_price")
	private Long buyPrice;

	/**
	 * Ưu tiên nhập đơn giá sau thuế
	 */
	@Column(name = "enter_unit_priority_after_tax")
	private Boolean enterUnitPriorityAfterTax;
	
	/**
	 * Thuế GTGT
	 */
	@Column(name = "vat")
	private String vat;

	/**
	 * Ngầm định ghi nhân DS trước thuế
	 */
	@Column(name = "implicit_record")
	private Boolean implicitRecord;
	
	/**
	 * Đơn giá chi phí
	 */
	@Column(name = "cost_unit_price")
	private Long costUnitPrice;
	
	@JsonIgnore
	public Product(String name) {
		super(name);
	}

}
