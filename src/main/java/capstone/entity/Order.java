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
import capstone.common.annotation.UniqueOrNull;
import capstone.dto.request.deserializer.LocalDateDeserializer;
import capstone.dto.response.serializer.IdNameSerializer;
import capstone.dto.response.serializer.LocalDateSerializer;
import capstone.model.Coded;
import capstone.model.Named;
import capstone.model.ProductInfoed;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Order
 * Đơn hàng
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
@Table(name = "[Order]", //
		uniqueConstraints = { //
		})
public class Order extends BaseEntity<Long> implements ProductInfoed, Coded, Named {
	private static final long serialVersionUID = 1L;

	/**
	 * Mã đơn hàng
	 */
	@UniqueOrNull
	@Column(name = "code", nullable = false)
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
	@JsonSerialize(using = IdNameSerializer.class)
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
	@JsonSerialize(using = IdNameSerializer.class)
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
	
	public String getPaymentStatus() {
		Long receivedMoney = this.receivedMoney != null ? this.receivedMoney : 0L;
		Long totalMoney = this.totalMoney();
		if (Objects.equals(receivedMoney, totalMoney))
			return Constant.Order.PaymentStatus.PAID;
		else if (receivedMoney == 0)
			return Constant.Order.PaymentStatus.UNPAID;
		return Constant.Order.PaymentStatus.PAID_IN_PART;
	}

	/**
	 * Thông tin từng hàng hóa
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	protected Set<ProductInfo> productInfos;
	
	// Thong tin dia chi
	
	/**
	 * Quốc gia 
	 */
	@JsonSerialize(using = IdNameSerializer.class)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "country_id")
	private Country country;
	
	/**
	 * Tỉnh
	 */
	@JsonSerialize(using = IdNameSerializer.class)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "province_id")
	private Province province;
	
	/**
	 * Huyện
	 */
	@JsonSerialize(using = IdNameSerializer.class)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "district_id")
	private District district;
	
	/**
	 * Xã, Phường
	 */
	@JsonSerialize(using = IdNameSerializer.class)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ward_id")
	private Ward ward;
	
	/**
	 * Địa chỉ
	 */
	@Column(name = "address", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String address;
	
	// OneToMany

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

	@Override
	public void productInfoRemoveThis(ProductInfo productInfo) {
		if (Objects.nonNull(productInfo))
			productInfo.setOrder(null);
	}

	@Override
	public String getName() {
		return this.explanation;
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
	 * @param country
	 * @param province
	 * @param district
	 * @param ward
	 * @param address
	 * @param invoices
	 */
	public Order(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy, User owner,
			Boolean shared, Boolean deleted, String code, LocalDate orderDate, Customer customer, Contact contact,
			Opportunity opportunity, String explanation, LocalDate deliveryDeadline, LocalDate liquidationDeadline,
			Long receivedMoney, Set<ProductInfo> productInfos, Country country, Province province, District district,
			Ward ward, String address, Set<Invoice> invoices) {
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
		this.country = country;
		this.province = province;
		this.district = district;
		this.ward = ward;
		this.address = address;
		this.invoices = invoices;
	}

}
