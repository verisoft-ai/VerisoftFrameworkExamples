package co.verisoft.fw.cucumber;

import org.springframework.context.annotation.*;

/**
 * CucumberSpringConfig class provides Spring configuration for Cucumber tests.
 * It defines beans for different dictionaries and return the implementations
 */

@Configuration
public class CucumberSpringConfig {

    /**
     * Creates a bean of type BaseDictionary for the "UI" dictionary.
     */
    @Bean
    @Profile("UI")
    public BaseDictionary baseUIDictionaryImpl() {
        return new BaseDictionaryImpl();
    }

    /**
     * Creates a bean of type BaseDictionary for the "API" dictionary.
     */
    @Bean
    @Profile("API")
    public APIDictionary baseAPIDictionaryImpl() {
        return new BaseDictionaryImpl();
    }
}
