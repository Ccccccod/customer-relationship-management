/**
 * 
 */
package capstone.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
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
 * Opportunity
 * Cơ hội
 * @author Tuna
 *
 */
@SuperBuilder(toBuilder = true)
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
public class Opportunity extends BaseEntity<Long> implements ProductInfoed, Coded, Named {
	private static final long serialVersionUID = 1L;

	/**
	 * Mã cơ hội
	 */
	@UniqueOrNull
	@Column(name = "code", nullable = false)
	private String code;

	/**
	 * Tổ chức
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
	 * Tên cơ hội
	 */
	@Column(name = "name", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String name;

	/**
	 * Gian đoạn
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "opportunity_phase_id")
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
	 * Nguồn gốc
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "source_id")
	private Source source;

	/**
	 * Thông tin từng hàng hóa
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "opportunity", cascade = CascadeType.ALL, orphanRemoval = true)
	protected Set<ProductInfo> productInfos;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "opportunity")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private Set<Order> orders;
	
	/**
	 * Doanh số kỳ vọng
	 */
	public Long getExpectedTurnOver() {
		Long totalMoney = Optional.ofNullable(totalMoney()).orElse(0L);
		Integer successRate = Optional.ofNullable(this.successRate).orElse(0);
		return totalMoney * successRate;
	}
	
	// Thông tin địa chỉ
	
	/**
	 * Quốc gia 
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "country_id")
	private Country country;
	
	/**
	 * Tỉnh
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "province_id")
	private Province province;
	
	/**
	 * Huyện
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "district_id")
	private District district;
	
	/**
	 * Xã, Phường
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ward_id")
	private Ward ward;
	
	/**
	 * Địa chỉ
	 */
	@Column(name = "address", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String address;
	
	@Override
	public void productInfoSetThis(ProductInfo productInfo) {
		if (Objects.nonNull(productInfo))
			productInfo.setOpportunity(this);
	}

	@Override
	public void productInfoRemoveThis(ProductInfo productInfo) {
		if (Objects.nonNull(productInfo))
			productInfo.setOpportunity(null);
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
	 * @param customer
	 * @param contact
	 * @param name
	 * @param opportunityPhase
	 * @param successRate
	 * @param expectedEndDate
	 * @param source
	 * @param productInfos
	 * @param orders
	 * @param country
	 * @param province
	 * @param district
	 * @param ward
	 * @param address
	 */
	public Opportunity(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy,
			User owner, Boolean shared, Boolean deleted, String code, Customer customer, Contact contact, String name,
			OpportunityPhase opportunityPhase, Integer successRate, LocalDate expectedEndDate, Source source,
			Set<ProductInfo> productInfos, Set<Order> orders, Country country, Province province, District district,
			Ward ward, String address) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, owner, shared, deleted);
		this.code = code;
		this.customer = customer;
		this.contact = contact;
		this.name = name;
		this.opportunityPhase = opportunityPhase;
		this.successRate = successRate;
		this.expectedEndDate = expectedEndDate;
		this.source = source;
		this.productInfos = productInfos;
		setToProductInfos(productInfos);
		this.orders = orders;
		this.country = country;
		this.province = province;
		this.district = district;
		this.ward = ward;
		this.address = address;
	}

}
