/**
 * 
 */
package capstone.dto.validatation.validator;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import capstone.common.Constant;
import capstone.dto.validatation.annotation.Email;

/**
 * Email Validator
 * @author Tuna
 *
 */
public class EmailValidator implements ConstraintValidator<Email, String>{
	
	private static final String EMAIL_REGEX = Constant.Validation.EMAIL_REGEX;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			return !Objects.isNull(value) && value.matches(EmailValidator.EMAIL_REGEX);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
