/**
 * 
 */
package capstone.entity;

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

import capstone.common.Constant;
import capstone.common.annotation.UniqueOrNull;
import capstone.model.Coded;
import capstone.model.ProductInfoed;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Invoice
 * Hóa đơn
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
@Table(name = "Invoice", //
		uniqueConstraints = { //
		})
public class Invoice extends BaseEntity<Long> implements Coded, ProductInfoed {
	private static final long serialVersionUID = 1L;
	
	@UniqueOrNull
	@Column(name = "code", nullable = false)
	private String code;

	/**
	 * Khách hàng
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	/**
	 * Tài khoản ngân hàng
	 */
	@Column(name = "bank_account")
	private String bankAccount;

	/**
	 * Mở tại ngân hàng
	 */
	@Column(name = "bank", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String bank;

	/**
	 * Mã số thuế
	 */
	@Column(name = "tax_code")
	private String taxCode;

	/**
	 * Người mua
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "buyer_id")
	private Contact buyer;

	/**
	 * Tên người nhận
	 */
	@Column(name = "receiver_name", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String receiverName;

	/**
	 * Email người nhận
	 */
	@Column(name = "receiver_email", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String receiverEmail;
	
	/**
	 * Điện thoại người nhận
	 */
	@Column(name = "phone")
	private String receiverPhone;

	/**
	 * Đơn hàng
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id")
	private Order order;

	/**
	 * Thông tin từng hàng hóa
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
	protected Set<ProductInfo> productInfos;
	
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
			productInfo.setInvoice(this);
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
	 * @param bankAccount
	 * @param bank
	 * @param taxCode
	 * @param buyer
	 * @param receiverName
	 * @param receiverEmail
	 * @param receiverPhone
	 * @param order
	 * @param productInfos
	 * @param country
	 * @param province
	 * @param district
	 * @param ward
	 * @param address
	 */
	public Invoice(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy,
			User owner, Boolean shared, Boolean deleted, String code, Customer customer, String bankAccount,
			String bank, String taxCode, Contact buyer, String receiverName, String receiverEmail, String receiverPhone,
			Order order, Set<ProductInfo> productInfos, Country country, Province province, District district,
			Ward ward, String address) {
		super(id, createdAt, updatedAt, createdBy, updatedBy, owner, shared, deleted);
		this.code = code;
		this.customer = customer;
		this.bankAccount = bankAccount;
		this.bank = bank;
		this.taxCode = taxCode;
		this.buyer = buyer;
		this.receiverName = receiverName;
		this.receiverEmail = receiverEmail;
		this.receiverPhone = receiverPhone;
		this.order = order;
		this.productInfos = productInfos;
		this.setToProductInfos(productInfos);
		this.country = country;
		this.province = province;
		this.district = district;
		this.ward = ward;
		this.address = address;
	}

}
