/**
 * 
 */
package capstone.dto.validation.validator;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import capstone.entity.NamedEntity;

/**
 * @author Tuna
 *
 */
public class ValidatorTests {
	
	/**
	 * Create Validator
	 * @return
	 */
	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}
	
	@Test
	void TestEmptyName() {
		NamedEntity<Long> entity = new NamedEntity<Long>("");
		
		Validator validator = createValidator();
		Set<ConstraintViolation<NamedEntity<Long>>> constraintViolations = validator.validate(entity);

		assertThat(constraintViolations).hasSize(1);
		ConstraintViolation<NamedEntity<Long>> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("name");
		assertThat(violation.getMessage()).isEqualTo("must not be empty");
	}

}
