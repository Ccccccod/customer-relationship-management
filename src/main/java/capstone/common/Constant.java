/**
 * 
 */
package capstone.common;

/**
 * @author Tuna
 *
 */
public final class Constant {
	
	public static final class Validation {
		
		public static final String USERNAME_REGEX = "^([a-zA-Z])+([\\w]{7,31})+$";
		
		public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!\\\"#$%&'()*+,-.\\/:;<=>?@\\[\\]^_`{|}~\\\\])(?=\\S+$).{8,40}$";
		
		public static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\\\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\\\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
		
	}
	
	public static final class Hibernate {
		
		public static final String NVARCHAR_255 = "nvarchar(255)";
		
		public static final String DELETED_FILTER = "deletedFilter";
		
	}
	
	public static final class Order {
		
		public static final class PaymentStatus {
			
			public static final String PAID = "Đã thanh toán";
			
			public static final String PAID_IN_PART = "Đã thanh toán một phần";
			
			public static final String UNPAID = "Chưa thanh toán"; 
			
		}
		
	}

}
