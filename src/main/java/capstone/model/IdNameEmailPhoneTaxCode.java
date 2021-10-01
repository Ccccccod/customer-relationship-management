/**
 * 
 */
package capstone.model;

import java.io.Serializable;

/**
 * IdNameEmailPhoneTaxCode
 * @author Tuna
 */
public interface IdNameEmailPhoneTaxCode<ID extends Serializable> extends IdNameEmailPhone<ID> {
	
	String getBankAccount();
	
	String getBank();
	
	String getTaxCode();

}
