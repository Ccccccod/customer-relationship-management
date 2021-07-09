/**
 * 
 */
package capstone.dto.validatation.validator;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import capstone.dto.request.ChangePasswordRequest;
import capstone.dto.validatation.annotation.ChangePassword;

/**
 * ChangePasswordValidator
 * @author Tuna
 *
 */
public class ChangePasswordValidator implements ConstraintValidator<ChangePassword, ChangePasswordRequest> {

	@Override
	public boolean isValid(ChangePasswordRequest value, ConstraintValidatorContext context) {
		if (Objects.isNull(value) || Objects.isNull(value.getOldPassword()) || Objects.isNull(value.getNewPassword()))
			return true;
		return value.getOldPassword().equals(value.getNewPassword());
	}

}
