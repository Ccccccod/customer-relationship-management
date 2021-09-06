/**
 * 
 */
package capstone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SpringFoxConfig
 * @author Tuna
 */
@Configuration
@EnableSwagger2
@Import(SpringDataRestConfiguration.class)
public class SpringFoxConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2) //
				.select() //
				.apis(RequestHandlerSelectors.any()) //
				.paths(PathSelectors.any()).build()
				.apiInfo(metaData());
	}
	
	private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Tech Interface - Spring Boot Swagger Configuration")
                .description("\"Swagger configuration for application \"")
//                .version("1.1.0")
//                .license("Apache 2.0")
//                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .build();
	}
	
}