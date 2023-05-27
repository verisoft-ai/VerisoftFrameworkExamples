package co.verisoft.examples;

import co.verisoft.examples.property.CustomProperty;
import co.verisoft.fw.config.EnvConfig;
import co.verisoft.fw.report.observer.Report;
import co.verisoft.fw.selenium.drivers.VerisoftDriver;
import co.verisoft.fw.selenium.drivers.factory.DriverCapabilities;
import co.verisoft.fw.selenium.drivers.factory.DriverUrl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URL;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {EnvConfig.class})
public class PropertyFileLoaderTest extends BaseTest{

    @Autowired
    CustomProperty customProperty;

    @Autowired
    @DriverUrl
    private URL url;

    @DriverCapabilities
    private DesiredCapabilities capabilities = new DesiredCapabilities();
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");

        capabilities.setBrowserName("chrome");
        options.merge(capabilities);
    }


    @Test
    @DisplayName("Injected URL")
    public void injectedURL(VerisoftDriver driver) throws InterruptedException {
        driver.get("https://www.wikipedia.org/");
        Report.info("Loaded page from a url found in application.properties");
    }

    @Test
    @DisplayName("Custom Property")
    public void customProperty(VerisoftDriver driver) throws InterruptedException {

        Assertions.assertEquals(customProperty.getCustomProperty(), "Hello");
    }


}
