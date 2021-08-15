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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import capstone.common.enums.OpportunityPhase;
import capstone.dto.request.deserializer.LocalDateDeserializer;
import capstone.dto.response.serializer.I18nEnumSerializer;
import capstone.dto.response.serializer.LocalDateSerializer;
import capstone.model.ProductInfoed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Opportunity
 * Cơ hội
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
@Table(name = "Opportunity", //
		uniqueConstraints = { //
		})
public class Opportunity extends NamedEntity<Long> implements ProductInfoed {
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contact_id")
	private Contact contact;
	
	/**
	 * Số tiền
	 */
	@Column(name = "money_amount", nullable = false)
	private Long moneyAmount;

	/**
	 * Gian đoạn
	 */
	@JsonSerialize(using = I18nEnumSerializer.class, as = OpportunityPhase.class)
	@Column(name = "opportunity_phase", nullable = false)
	@Enumerated(EnumType.STRING)
	private OpportunityPhase opportunityPhase;
	
	/**
	 * Tỉ lệ thành công
	 */
	@Column(name = "success_rate")
	private Integer successRate;
	
	/**
	 * Ngày kỳ vọng kết thúc
	 */
	@Column(name = "expected_end_date", nullable = false)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
//	@DateTimeFormat(iso = ISO.DATE)
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yy")
	private LocalDate expectedEndDate;
	
	/**
	 * Doanh số kỳ vọng
	 */
	@Column(name = "expected_turn_over", nullable = false)
	private Long expectedTurnOver;
	
	/**
	 * Nguồn gốc
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "source_id")
	private Source source;

	/**
	 * Thông tin từng hàng hóa
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "opportunity", cascade = CascadeType.ALL)
	protected Set<ProductInfo> productInfos;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "opportunity")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Order> orders;
	
	/**
	 * Địa chỉ
	 * @return
	 */
	public String address() {
		return Objects.nonNull(this.customer) ? this.customer.getAddress() : null;
	}
	
	@Override
	public void productInfoSetThis(ProductInfo productInfo) {
		if (Objects.nonNull(productInfo))
			productInfo.setOpportunity(this);
	}

	/**
	 * @param id
	 * @param createdAt
	 * @param updatedAt
	 * @param createdBy
	 * @param updatedBy
	 * @param name
	 * @param customer
	 * @param contact
	 * @param moneyAmount
	 * @param opportunityPhase
	 * @param successRate
	 * @param expectedEndDate
	 * @param expectedTurnOver
	 * @param source
	 * @param productInfos
	 */
	@Builder(toBuilder = true)
	public Opportunity(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy,
			String name, Customer customer, Contact contact, Long moneyAmount, OpportunityPhase opportunityPhase,
			Integer successRate, LocalDate expectedEndDate, Long expectedTurnOver, Source source,
			Set<ProductInfo> productInfos) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, name);
		this.customer = customer;
		this.contact = contact;
		this.moneyAmount = moneyAmount;
		this.opportunityPhase = opportunityPhase;
		this.successRate = successRate;
		this.expectedEndDate = expectedEndDate;
		this.expectedTurnOver = expectedTurnOver;
		this.source = source;
		setToProductInfos(productInfos);
	}

}
