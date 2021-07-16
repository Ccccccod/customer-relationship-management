/**
 * 
 */
package capstone.dto.validatation.validator;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import capstone.common.Constant;
import capstone.dto.validatation.annotation.Username;

/**
 * Username Validator
 * Validator for {@link Username}
 * @author Tuna
 *
 */
public class UsernameValidator implements ConstraintValidator<Username, String>{
	
	private static final String USERNAME_REGEX = Constant.Validation.USERNAME_REGEX;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			return Objects.isNull(value) || value.matches(UsernameValidator.USERNAME_REGEX);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
