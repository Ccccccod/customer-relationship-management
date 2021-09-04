/**
 * 
 */
package capstone.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import capstone.common.Constant;
import capstone.dto.request.deserializer.LocalDateDeserializer;
import capstone.dto.response.serializer.LocalDateSerializer;
import capstone.model.Coded;
import capstone.model.ProductInfoed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Order
 * Đơn hàng
 * @author Tuna
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "[Order]", //
		uniqueConstraints = { //
		})
public class Order extends BaseEntity<Long> implements ProductInfoed, Coded {
	private static final long serialVersionUID = 1L;

	/**
	 * Mã đơn hàng
	 */
	@Column(name = "code", unique = true, nullable = false)
	private String code;
	
	/**
	 * Ngày đặt hàng
	 */
	@Column(name = "order_date", nullable = false)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate orderDate;
	
	/**
	 * Khách hàng
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	/**
	 * Liên hệ
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contact_id")
	private Contact contact;

	/**
	 * Cơ hội
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "opportunity_id")
	private Opportunity opportunity;

	/**
	 * Giá trị đơn hàng
	 */
	public Long getOrderValue() {
		return this.totalMoney();
	}

	/**
	 * Diễn giải
	 */
	@Column(name = "explanation", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String explanation;
	
	/**
	 * Giá trị thanh lý
	 */
	public Long getLiquidationValue() {
		return this.totalMoney();
	}

	/**
	 * Hạn giao hàng
	 */
	@Column(name = "delivery_date", nullable = false)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate deliveryDeadline;

	/**
	 * Hạn thanh toán
	 */
	@Column(name = "liquidation_date", nullable = false)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate liquidationDeadline;
	
	// Order fulfillment status
	// Tình trạng thực hiện đơn hàng

	/**
	 * Thực thu
	 */
	@Column(name = "receivedMoney", nullable = false)
	private Long receivedMoney;

	/**
	 * Thông tin từng hàng hóa
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
	protected Set<ProductInfo> productInfos;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Invoice> invoices;

	@Override
	public void productInfoSetThis(ProductInfo productInfo) {
		if (Objects.nonNull(productInfo))
			productInfo.setOrder(this);
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
	 * @param code
	 * @param orderDate
	 * @param customer
	 * @param contact
	 * @param opportunity
	 * @param explanation
	 * @param deliveryDeadline
	 * @param liquidationDeadline
	 * @param receivedMoney
	 * @param productInfos
	 * @param invoices
	 */
	@Builder(toBuilder = true)
	public Order(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy, User owner,
			Boolean shared, Boolean deleted, String code, LocalDate orderDate, Customer customer, Contact contact,
			Opportunity opportunity, String explanation, LocalDate deliveryDeadline, LocalDate liquidationDeadline,
			Long receivedMoney, Set<ProductInfo> productInfos, Set<Invoice> invoices) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, owner, shared, deleted);
		this.code = code;
		this.orderDate = orderDate;
		this.customer = customer;
		this.contact = contact;
		this.opportunity = opportunity;
		this.explanation = explanation;
		this.deliveryDeadline = deliveryDeadline;
		this.liquidationDeadline = liquidationDeadline;
		this.receivedMoney = receivedMoney;
		this.productInfos = productInfos;
		setToProductInfos(productInfos);
		this.invoices = invoices;
	}

}
