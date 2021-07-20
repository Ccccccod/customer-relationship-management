/**
 * 
 */
package capstone.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import capstone.common.Constant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Product Info
 * Thông tin 1 hàng hóa
 * @author Tuna
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "ProductInfo", //
		uniqueConstraints = { //
		})
public class ProductInfo extends BaseEntity<Long> {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Mã hàng hóa
	 */
	@Column(name = "product_code", nullable = false, updatable = false)
	private String productCode;

	/**
	 * Diễn giải khi bán
	 */
	@Column(name = "explanation", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String explanation;

	/**
	 * Đơn vị tính
	 */
	@Column(name = "unit", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String unit;
	
	/**
	 * Số lượng
	 */
	@Column(name = "amount", nullable = false)
	private Integer amount;
	
	/**
	 * Đơn giá
	 */
	@Column(name = "price", nullable = false)
	private Long price;
	
	/**
	 * Thành tiền
	 * @return
	 */
	@JsonProperty
	public Long totalPrice() {
		return this.price * this.amount;
	}
	
	/**
	 * Tỷ lệ chiết khấu
	 */
	@Column(name = "discount", nullable = false)
	private Integer discount;
	
	/**
	 * Tiền chiết khấu
	 * @return
	 */
	@JsonProperty
	public Long discountMoney() {
		return this.price * this.discount / 100;
	}
	
	/**
	 * Thuế GTGT
	 */
	@Column(name = "vat")
	private Integer vat;
	
	/**
	 * Tiền thuế
	 * @return
	 */
	@JsonProperty
	public Long vatMoney() {
		Integer vat = Objects.nonNull(this.vat) ? this.vat : 0;
		return (this.totalPrice() - this.discountMoney()) * vat / 100;
	}
	
	/**
	 * Tổng tiền
	 * @return
	 */
	@JsonProperty
	public Long totalMoney() {
		return this.totalPrice() - this.discountMoney() + this.vatMoney();
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "opportunity_id")
	@ToString.Exclude @EqualsAndHashCode.Exclude @JsonIgnore
	private Opportunity opportunity;

	/**
	 * @param id
	 * @param createdAt
	 * @param updatedAt
	 * @param createdBy
	 * @param updatedBy
	 * @param productCode
	 * @param explanation
	 * @param unit
	 * @param amount
	 * @param price
	 * @param discount
	 * @param vat
	 * @param opportunity
	 */
	@Builder
	public ProductInfo(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy,
			String productCode, String explanation, String unit, Integer amount, Long price, Integer discount,
			Integer vat, Opportunity opportunity) {
		super(id, createdAt, updatedAt, createdBy, updatedBy);
		this.productCode = productCode;
		this.explanation = explanation;
		this.unit = unit;
		this.amount = amount;
		this.price = price;
		this.discount = discount;
		this.vat = vat;
		this.opportunity = opportunity;
	}

}
