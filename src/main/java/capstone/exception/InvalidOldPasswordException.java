/**
 * 
 */
package capstone.exception;

/**
 * InvalidOldPasswordException
 * @author Tuna
 *
 */
public class InvalidOldPasswordException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidOldPasswordException() {
	}

	public InvalidOldPasswordException(String message) {
		super(message);
	}

}
