package capstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BackEndCapstoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackEndCapstoneApplication.class, args);
	}
	
	@Bean
    public WebMvcConfigurer corsWebConfig() {

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("**")
                        .allowedOrigins("http://localhost:3000")
                        .allowCredentials(true);
            }
        };
    }

}
