/**
 * 
 */
package capstone.exception;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Tuna
 * Hold details for errors
 */
@AllArgsConstructor
@Getter
public class ErrorDetails {
	private Date timestamp;
	private List<String> errors;
	private String details;
	
	/**
	 * @param errors
	 */
	public ErrorDetails(List<String> errors) {
		super();
		this.timestamp = new Date();
		this.errors = errors;
	}
	
	/**
	 * @param error
	 */
	public ErrorDetails(String error) {
		super();
		this.timestamp = new Date();
		this.errors = Arrays.asList(error);
	}
	
	/**
	 * @param timestamp
	 * @param error 1 error
	 * @param details
	 */
	public ErrorDetails(Date timestamp, String error, String details) {
		super();
		this.timestamp = timestamp;
		this.errors = Arrays.asList(error);
		this.details = details;
	}

	/**
	 * @param error
	 * @param details
	 */
	public ErrorDetails(String error, String details) {
		super();
		this.errors = Arrays.asList(error);
		this.details = details;
	}

	/**
	 * @param errors
	 * @param details
	 */
	public ErrorDetails(List<String> errors, String details) {
		super();
		this.errors = errors;
		this.details = details;
	}
}
