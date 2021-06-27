/**
 * 
 */
package capstone.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Tuna
 *
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Invoice extends NamedEntity<Long> {
	private static final long serialVersionUID = 1L;

	private Customer customer;
	
	private String address;
	
	/**
	 * 
	 */
	private String bankAccount;
	
	private String bank;
	
	private String taxCode;
	
	// TODO Nguoi mua hang (Contant)
	
	private String InvoiceReceiver;

	private String receiverEmail;
	
	private String receiverPhone;
	
	private String invoiceType;
	
	private String paymentMethod;
	
	private String status;
	
	private Date date;
	
	// TODO
	
	/**
	 * @param name
	 */
	public Invoice(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

}
