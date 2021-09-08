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
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
	 * Hàng hóa
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	private Product product;
	
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
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "unit_id")
	private Unit unit;
	
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
		return this.totalPrice() * vat / 100;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id")
	@ToString.Exclude @EqualsAndHashCode.Exclude @JsonIgnore
	private Order order;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "invoice_id")
	@ToString.Exclude @EqualsAndHashCode.Exclude @JsonIgnore
	private Invoice invoice;
	
	@JsonProperty
	@JsonInclude(value = Include.NON_NULL)
	public Long opportunityId() {
		return Objects.nonNull(this.opportunity) ? this.opportunity.getId() : null;
	}
	
	@JsonProperty
	@JsonInclude(value = Include.NON_NULL)
	public Long orderId() {
		return Objects.nonNull(this.order) ? this.order.getId() : null;
	}
	
	@JsonProperty
	@JsonInclude(value = Include.NON_NULL)
	public Long invoiceId() {
		return Objects.nonNull(this.invoice) ? this.invoice.getId() : null;
	}

	/**
	 * @param id
	 * @param createdAt
	 * @param updatedAt
	 * @param createdBy
	 * @param updatedBy
	 * @param owner
	 * @param shared
	 * @param deleted
	 * @param product
	 * @param productCode
	 * @param explanation
	 * @param unit
	 * @param amount
	 * @param price
	 * @param discount
	 * @param vat
	 * @param opportunity
	 * @param order
	 * @param invoice
	 */
	@Builder(toBuilder = true)
	public ProductInfo(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy,
			User owner, Boolean shared, Boolean deleted, Product product, String productCode, String explanation,
			Unit unit, Integer amount, Long price, Integer discount, Integer vat, Opportunity opportunity,
			Order order, Invoice invoice) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, owner, shared, deleted);
		this.product = product;
		this.productCode = productCode;
		this.explanation = explanation;
		this.unit = unit;
		this.amount = amount;
		this.price = price;
		this.discount = discount;
		this.vat = vat;
		this.opportunity = opportunity;
		this.order = order;
		this.invoice = invoice;
	}

}
