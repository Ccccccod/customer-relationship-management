/**
 * 
 */
package capstone.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import capstone.common.Constant;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Hàng hóa
 * @author Tuna
 */
@SuperBuilder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "Product", //
		uniqueConstraints = { //
		})
public class Product extends CodedNamedEntity<Long> {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Loại hàng hóa
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_type_id")
	private ProductType productType;
	
	/**
	 * Diễn giải khi bán
	 */
	@Column(name = "explanation", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String explanation;
	
	/**
	 * Đơn vị tính
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "unit_id")
	private Unit unit;
	
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
	private Integer vat;

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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<ProductInfo> productInfos;

	/**
	 * @param id
	 * @param createdAt
	 * @param updatedAt
	 * @param createdBy
	 * @param updatedBy
	 * @param owner
	 * @param shared
	 * @param deleted
	 * @param name
	 * @param code
	 * @param productType
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
	 * @param productInfos
	 */
	public Product(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy,
			User owner, Boolean shared, Boolean deleted, String name, String code, ProductType productType,
			String explanation, Unit unit, Long sellPrice, Long sellPrice1, Long sellPrice2, Long permanentPrice,
			Long buyPrice, Boolean enterUnitPriorityAfterTax, Integer vat, Boolean implicitRecord, Long costUnitPrice,
			Set<ProductInfo> productInfos) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, owner, shared, deleted, name, code);
		this.productType = productType;
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
		this.productInfos = productInfos;
	}

	/**
	 * @param name
	 * @param code
	 */
	public Product(String name, String code) {
		super(name, code);
	}

}
