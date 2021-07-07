/**
 * 
 */
package capstone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * {@link Exception} that's thrown when inserting something but it already existed
 * @author Tuna
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceExistedException extends Exception {
	private static final long serialVersionUID = 1L;

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param   message   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     */
	public ResourceExistedException(String message) {
		super(message);
	}

}
