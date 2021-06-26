/**
 * 
 */
package capstone.dto.validatation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

import capstone.common.Constant;

/**
 * Email validation 
 * @author Tuna
 */
@Pattern.List({ @Pattern(regexp = Constant.Validation.EMAIL_REGEX) })
@Constraint(validatedBy = {})
@Documented
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
		ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {

	String message() default "Email is invalid";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
			ElementType.PARAMETER })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		Email[] value();
	}
}
