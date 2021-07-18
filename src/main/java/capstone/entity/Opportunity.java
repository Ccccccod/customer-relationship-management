/**
 * 
 */
package capstone.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import capstone.dto.response.serializer.DateSerializer;
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
@NoArgsConstructor
@AllArgsConstructor
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
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "opportunity_phase_id", nullable = false)
	private OpportunityPhase opportunityPhase;
	
	/**
	 * Tỉ lệ thành công
	 */
	@Column(name = "success_rate")
	private Integer successRate;
	
	/**
	 * Ngày kỳ vọng kết thúc
	 */
	@JsonSerialize(using = DateSerializer.class)
	@Temporal(value = TemporalType.DATE)
	@Column(name = "opportunity_phase", nullable = false)
	private Date expectedEndDate;
	
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
	
	/**
	 * Địa chỉ
	 * @return
	 */
	public String address() {
		return Objects.nonNull(this.customer) ? this.customer.getAddress() : null;
	}
	
	@Override
	public void setProductInfos(Set<ProductInfo> productInfos) {
		this.productInfos = productInfos;
		if (Objects.nonNull(this.productInfos))
			this.productInfos.forEach(i -> i.setOpportunity(this));
	}
	
	@Override
	public void addToProductInfos(ProductInfo productInfo) {
		productInfo.setOpportunity(this);
		if (Objects.nonNull(this.productInfos))
			this.productInfos.add(productInfo);
		else
			this.productInfos = new LinkedHashSet<ProductInfo>(Arrays.asList(productInfo));
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
	@Builder
	public Opportunity(Long id, Date createdAt, Date updatedAt, User createdBy, User updatedBy, String name,
			Customer customer, Contact contact, Long moneyAmount, OpportunityPhase opportunityPhase,
			Integer successRate, Date expectedEndDate, Long expectedTurnOver, Source source,
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
		this.productInfos = productInfos;
		if (Objects.nonNull(productInfos))
			productInfos.forEach(i -> i.setOpportunity(this));
	}

}
