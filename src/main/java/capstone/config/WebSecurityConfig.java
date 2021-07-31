package capstone.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import capstone.entity.PermissionFunctionAction;
import capstone.repository.PermissionFunctionActionRepository;
import capstone.security.jwt.AuthEntryPointJwt;
import capstone.security.jwt.AuthTokenFilter;
import capstone.security.service.UserDetailsServiceImpl;

/**
 * WebSecurityConfig
 * @author Tuna
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;
	
	@Autowired
	private PermissionFunctionActionRepository permissionFunctionActionRepository;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
        	.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
        	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Permit all for login and logout
		http.authorizeRequests() //
				.antMatchers("/api/auth/**").permitAll()
				.antMatchers("/api/test/**").permitAll();

		final String baseUrl = "/api";
		
		List<PermissionFunctionAction> permissionFunctionActions = permissionFunctionActionRepository.findAll();
		for (PermissionFunctionAction permissionFunctionAction : permissionFunctionActions) {
			String function = permissionFunctionAction.getPermissionFunction().getName();
			function = WordUtils.capitalizeFully(function, '_').replace("_", "");
			function = WordUtils.uncapitalize(function, new char[0]);
			String action = permissionFunctionAction.getPermissionAction().getName();
			System.out.println("fff: " + function);
			String url = baseUrl + "/" + function;
			String url1 = url + "/";

			switch (action) {
			case "READ":
				http.authorizeRequests().antMatchers(HttpMethod.GET, url, url1, url1 + "{id:\\d+}")
						.hasAuthority(permissionFunctionAction.getValue());
				break;
			case "CREATE":
				http.authorizeRequests().antMatchers(HttpMethod.POST, url, url1)
						.hasAuthority(permissionFunctionAction.getValue());
				break;
			case "UPDATE":
				http.authorizeRequests().antMatchers(HttpMethod.PUT, url1 + "{id:\\d+}")
						.hasAuthority(permissionFunctionAction.getValue());
				break;
			case "DELETE":
				http.authorizeRequests().antMatchers(HttpMethod.DELETE, url1 + "{id:\\d+(,\\d+)?}")
						.hasAuthority(permissionFunctionAction.getValue());
				break;
			}
		}
        
		http.authorizeRequests()
        .anyRequest().authenticated()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        corsConfiguration.setExposedHeaders(Collections.singletonList(("AUTHORIZATION")));
        
		// TODO Get CORS client from config table
		List<String> corsAllow = Arrays.asList("http://localhost:3000", "https://localhost:3000");
        
        if (corsAllow.isEmpty()) corsAllow.add("/**");
        corsConfiguration.setAllowedOrigins(corsAllow);

        // Allow all methods
		Stream.of(HttpMethod.values()).forEach(corsConfiguration::addAllowedMethod);
		
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

}