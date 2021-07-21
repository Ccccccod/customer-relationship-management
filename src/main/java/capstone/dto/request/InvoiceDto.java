/**
 * 
 */
package capstone.dto.request;

import capstone.dto.validatation.annotation.Email;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Invoice Dto
 * Hóa đơn Dto
 * @author Tuna
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class InvoiceDto extends CodedNamedDto<Long> {
	
	/**
	 * Khách hàng
	 */
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
	private Long orderId;
	
}
