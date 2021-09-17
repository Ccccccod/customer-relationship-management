/**
 * 
 */
package capstone.exception;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Tuna
 * Global Exception Handler
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	/**
	 * Exception handler for {@link Valid} annotation or in other word, {@link MethodArgumentNotValidException}
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ex.printStackTrace();
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

		// Get all errors
		List<Map<String, Object>> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
				.map(x -> {
					Map<String, Object> map = new LinkedHashMap<>();
					map.put("message", x.getDefaultMessage());
					map.put("field", x.getField());
					map.put("rejectedValue", x.getRejectedValue());
					return map;
                })
                .collect(Collectors.toList());

        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);
	}

	/**
	 * Exception handler for {@link Validated} annotation
	 * @param response
	 * @throws IOException
	 */
    @ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    /**
	 * Provides handling for {@link ResourceExistedException}
     * @param e the target exception
     * @param request the current request
     * @return
     */
    @ExceptionHandler(ResourceExistedException.class)
    public ResponseEntity<?> resourceExistedException(ResourceExistedException e, WebRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());
        
        Map<String, Object> error = new LinkedHashMap<>();
        error.put("message", e.getMessage());
        error.put("field", e.getName());
        error.put("rejectedValue", e.getRejectedValue());
        error.put("type", "duplicate");
        error.put("msg", "Giá trị của " + e.getName() + " đã bị trùng");
        body.put("error", error);
        
		return new ResponseEntity<>(body, status);
    }

	/**
	 * Provides handling for {@link ResourceNotFoundException},
	 * {@link ResourceExistedException}, {@link DuplicateKeyException},
	 * {@link InvalidOldPasswordException}
	 * @param ex      the target exception
	 * @param request the current request
	 */
	@ExceptionHandler({ //
			ResourceNotFoundException.class, //
			DuplicateKeyException.class, //
			InvalidOldPasswordException.class, //
			org.hibernate.exception.ConstraintViolationException.class, //
			BadCredentialsException.class
		})
	@Nullable
    public ResponseEntity<?> handleException1(Exception ex, WebRequest request) throws Exception {
		if (ex instanceof ResourceNotFoundException) {
	        Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", new Date());
	        body.put("status", HttpStatus.NOT_FOUND.value());
	        
	        Map<String, Object> error = new LinkedHashMap<>();
	        error.put("message", ex.getMessage());
	        error.put("entityName", ((ResourceNotFoundException) ex).getEntityName());
	        body.put("error", error);
	        
			return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
			
		} else if (ex instanceof DuplicateKeyException) {
			ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
			
		} else if (ex instanceof InvalidOldPasswordException) {
			ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
			
		} else if (ex instanceof org.hibernate.exception.ConstraintViolationException) {
			ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
			
		} else if (ex instanceof BadCredentialsException) {
			ErrorDetails errorDetails = new ErrorDetails(new Date(), "Usename or password is incorrect", request.getDescription(false));
			return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
			
		} else {
			ex.printStackTrace();
			throw ex;
		}
    }
	
	/**
	 * Global exception handler
	 * @param Exception
	 * @param request WebRequest
	 * @return Response Entity
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> globlalExcpetionHandler(Exception ex, WebRequest request) {
		ex.printStackTrace();
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
