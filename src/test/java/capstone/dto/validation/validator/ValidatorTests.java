/**
 * 
 */
package capstone.dto.validation.validator;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import capstone.entity.CodedNamedEntity;
import capstone.entity.NamedEntity;

/**
 * ValidatorTests
 * @author Tuna
 *
 */
public class ValidatorTests {
	
	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		// Set Up validator
//		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//		validator = factory.getValidator();
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		ValidatorTests.validator = localValidatorFactoryBean;
	}
	
	@Test
	void testEmptyName() {
		NamedEntity<Long> entity = new NamedEntity<Long>("");
		
		Set<ConstraintViolation<NamedEntity<Long>>> constraintViolations = validator.validate(entity);

		assertThat(constraintViolations).hasSize(1);
		ConstraintViolation<NamedEntity<Long>> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("name");
		assertThat(violation.getMessage()).isEqualTo("must not be empty");
	}
	
	@Test
	void testEmptyCode() {
		CodedNamedEntity<Long> entity = new CodedNamedEntity<Long>("Name", "");

		Set<ConstraintViolation<CodedNamedEntity<Long>>> constraintViolations = validator.validate(entity);
		
		assertThat(constraintViolations).hasSize(1);
		ConstraintViolation<CodedNamedEntity<Long>> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("code");
		assertThat(violation.getMessage()).isEqualTo("must not be blank");
	}

}
