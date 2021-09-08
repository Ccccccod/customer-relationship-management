/**
 * 
 */
package capstone.config;

import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * CodeGenerationConfig
 * @author Tuna
 */
@Configuration
public class CodeGenerationConfig {
	
	@Bean
	@Qualifier("randomDigitStringGenerator")
	public RandomStringGenerator randomDigitStringGenerator() {
		return new RandomStringGenerator.Builder() //
				.withinRange('0', '9') //
				.filteredBy(CharacterPredicates.DIGITS).build();
	}

}
