/**
 * 
 */
package capstone.dto.validatation.validator;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import capstone.common.Constant;
import capstone.dto.validatation.annotation.Password;

/**
 * Password Validator
 * @author Tuna
 *
 */
public class PasswordValidator implements ConstraintValidator<Password, String>{
	
	private static final String PASSWORD_REGEX = Constant.Validation.PASSWORD_REGEX;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			return Objects.isNull(value) || value.matches(PASSWORD_REGEX);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
