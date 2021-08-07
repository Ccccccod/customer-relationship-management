/**
 * 
 */
package capstone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * {@link Exception} that's thrown when inserting something but it is unique and it already existed
 * @author Tuna
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceExistedException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * name of the resource
	 */
	private final String name;

	/**
	 * value that already existed
	 */
	@Nullable
	private final Object rejectedValue;

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param   message   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
	 * @param name
	 * @param rejectedValue
     */
	public ResourceExistedException(String message, String name, Object rejectedValue) {
		super(message);
		this.name = name;
		this.rejectedValue = rejectedValue;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the rejectedValue
	 */
	public Object getRejectedValue() {
		return rejectedValue;
	}

}
