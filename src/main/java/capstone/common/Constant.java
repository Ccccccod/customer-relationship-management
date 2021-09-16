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
		
		public static final String EMAIL_REGEX = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		
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
