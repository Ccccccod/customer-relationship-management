/**
 * 
 */
package capstone.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import capstone.common.Constant;
import capstone.model.Coded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Invoice
 * Hóa đơn
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
@Table(name = "Invoice", //
		uniqueConstraints = { //
		})
public class Invoice extends BaseEntity<Long> implements Coded {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "code", unique = true, nullable = false)
	private String code;

	/**
	 * Khách hàng
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	/**
	 * Địa chỉ
	 */
	@Column(name = "address", columnDefinition = Constant.Hibernate.NVARCHAR_255)
	private String address;

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
	 * @param id
	 * @param createdAt
	 * @param updatedAt
	 * @param createdBy
	 * @param updatedBy
	 * @param code
	 * @param customer
	 * @param address
	 * @param bankAccount
	 * @param bank
	 * @param taxCode
	 * @param buyer
	 * @param receiverName
	 * @param receiverEmail
	 * @param receiverPhone
	 * @param order
	 */
	@Builder
	public Invoice(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, User createdBy, User updatedBy,
			String code, Customer customer, String address, String bankAccount, String bank, String taxCode,
			Contact buyer, String receiverName, String receiverEmail, String receiverPhone, Order order) {
		super(id, createdAt, updatedAt, createdBy, updatedBy);
		this.code = code;
		this.customer = customer;
		this.address = address;
		this.bankAccount = bankAccount;
		this.bank = bank;
		this.taxCode = taxCode;
		this.buyer = buyer;
		this.receiverName = receiverName;
		this.receiverEmail = receiverEmail;
		this.receiverPhone = receiverPhone;
		this.order = order;
	}

}
