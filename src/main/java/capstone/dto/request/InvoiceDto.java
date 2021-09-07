/**
 * 
 */
package capstone.dto.request;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import capstone.dto.request.deserializer.IdDeserializable;
import capstone.dto.validatation.annotation.Email;
import capstone.model.Coded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Invoice Dto
 * Hóa đơn Dto
 * @author Tuna
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class InvoiceDto extends BaseDto<Long> implements Coded {
	
	/**
	 * Mã
	 */
	@NotNull
	private String code;
	
	/**
	 * Khách hàng
	 */
	@JsonDeserialize(using = IdDeserializable.class)
	@JsonAlias("customer")
	private Long customerId;
	
	/**
	 * Địa chỉ
	 */
	private String address;
	
	/**
	 * Tài khoản ngân hàng
	 */
	private String bankAccount;
	
	/**
	 * Mở tại ngân hàng
	 */
	private String bank;
	
	/**
	 * Mã số thuế
	 */
	private String taxCode;
	
	/**
	 * Người mua
	 */
	@JsonDeserialize(using = IdDeserializable.class)
	@JsonAlias("buyer")
	private Long buyerId;
	
	/**
	 * Tên người nhận
	 */
	private String receiverName;

	/**
	 * Email người nhận
	 */
	@Email
	private String receiverEmail;

	/**
	 * Điện thoại người nhận
	 */
	private String receiverPhone;
	
	/**
	 * Đơn hàng
	 */
	@JsonDeserialize(using = IdDeserializable.class)
	@JsonAlias("order")
	private Long orderId;

	/**
	 * @param id
	 * @param code
	 * @param customerId
	 * @param address
	 * @param bankAccount
	 * @param bank
	 * @param taxCode
	 * @param buyerId
	 * @param receiverName
	 * @param receiverEmail
	 * @param receiverPhone
	 * @param orderId
	 */
	@Builder
	public InvoiceDto(Long id, String code, Long customerId, String address, String bankAccount, String bank, String taxCode,
			Long buyerId, String receiverName, String receiverEmail, String receiverPhone, Long orderId) {
		super(id);
		this.code = code;
		this.customerId = customerId;
		this.address = address;
		this.bankAccount = bankAccount;
		this.bank = bank;
		this.taxCode = taxCode;
		this.buyerId = buyerId;
		this.receiverName = receiverName;
		this.receiverEmail = receiverEmail;
		this.receiverPhone = receiverPhone;
		this.orderId = orderId;
	}
	
}
