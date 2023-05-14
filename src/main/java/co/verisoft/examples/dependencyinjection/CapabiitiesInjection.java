package co.verisoft.examples.dependencyinjection;


import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("co.verisoft")
public class CapabiitiesInjection {


    @Bean
    public DesiredCapabilities getCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");

        capabilities.setBrowserName("chrome");
        capabilities.setCapability("browserVersion", "113");
        capabilities.setCapability("driverVersion", "113");
        options.merge(capabilities);

        return capabilities;
    }
}
