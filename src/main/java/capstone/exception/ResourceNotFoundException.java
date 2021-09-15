/**
 * 
 */
package capstone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

/**
 * Exception that's thrown when sources are not found
 * @author Tuna
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	@Getter
	private final String entityName;

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param   message   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     */
	public ResourceNotFoundException(String message, String entityName) {
		super(message);
		this.entityName = entityName;
	}
	public ResourceNotFoundException(String message, Class<?> cls) {
		super(message);
		if (cls == null)
			this.entityName = null;
		else
			this.entityName = cls.getSimpleName();
	}

	public ResourceNotFoundException() {
		this(null, (String) null);
	}

}
