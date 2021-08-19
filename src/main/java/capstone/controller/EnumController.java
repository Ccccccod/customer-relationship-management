/**
 * 
 */
package capstone.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * EnumController
 * a controller implements this to get all enum constants of an {@link Enum}
 * @author Tuna
 *
 */
public interface EnumController<T extends Enum<T>> {
	
	/**
	 * @return {@link Class} of T
	 */
	Class<T> getEnumClass();

	@GetMapping("/name")
	default ResponseEntity<?> getAllName() {
		return ResponseEntity.ok(getEnumClass().getEnumConstants());
	}

}
