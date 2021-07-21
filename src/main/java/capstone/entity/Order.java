/**
 * 
 */
package capstone.entity;

import java.time.LocalDate;
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
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import capstone.dto.request.deserializer.LocalDateDeserializer;
import capstone.dto.response.serializer.LocalDateSerializer;
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
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)

@Entity
@Table(name = "[Order]", //
		uniqueConstraints = { //
		})
public class Order extends CodedNamedEntity<Long> {
	private static final long serialVersionUID = 1L;
	// Code
	// Name
	
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
	
	// TODO Bao gia

	/**
	 * Giá trị đơn hàng
	 */
	@Column(name = "order_value", nullable = false)
	private Long orderValue;
	
	/**
	 * Giá trị thanh lý

	 */
	@Column(name = "liquidation_value")
	private Long liquidationValue;

	/**
	 * Hạn thanh toán
	 */
	@Column(name = "liquidation_date", nullable = false)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate liquidationDeadline;

	/**
	 * Hạn giao hàng
	 */
	@Column(name = "delivery_date", nullable = false)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate deliveryDeadline;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Invoice> invoices;

	/**
	 * @param id
	 * @param createdAt
	 * @param updatedAt
	 * @param createdBy
	 * @param updatedBy
	 * @param name
	 * @param code
	 * @param orderDate
	 * @param customer
	 * @param contact
	 * @param opportunity
	 * @param orderValue
	 * @param liquidationValue
	 * @param liquidationDeadline
	 * @param deliveryDeadline
	 * @param invoices
	 */
	@Builder
	public Order(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy, String name,
			String code, LocalDate orderDate, Customer customer, Contact contact, Opportunity opportunity,
			Long orderValue, Long liquidationValue, LocalDate liquidationDeadline, LocalDate deliveryDeadline,
			Set<Invoice> invoices) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, name, code);
		this.orderDate = orderDate;
		this.customer = customer;
		this.contact = contact;
		this.opportunity = opportunity;
		this.orderValue = orderValue;
		this.liquidationValue = liquidationValue;
		this.liquidationDeadline = liquidationDeadline;
		this.deliveryDeadline = deliveryDeadline;
		this.invoices = invoices;
	}

}
