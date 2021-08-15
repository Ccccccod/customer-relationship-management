/**
 * 
 */
package capstone.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * I18nConfig
 * @author Tuna
 *
 */
@Configuration
public class I18nConfig {
	
	static private String i18nEnumBaseName = "i18nEnum"; 

    /**
     * @return resourceBundleMessageSource for i18nEnum
     */
    @Bean
    public MessageSource i18nMessageSource() {
        ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
        rs.setBasenames(i18nEnumBaseName);
//        rs.setDefaultEncoding("UTF-8");
        rs.setUseCodeAsDefaultMessage(true);
        return rs;
    }

}
