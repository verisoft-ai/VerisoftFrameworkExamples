package co.verisoft.examples;

import co.verisoft.fw.selenium.drivers.VerisoftDriver;
import co.verisoft.fw.selenium.drivers.factory.DriverCapabilities;
import co.verisoft.fw.utils.Attemptable;
import co.verisoft.fw.utils.Retry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class RetryTests extends BaseTest{

    @DriverCapabilities
    private DesiredCapabilities capabilities = new DesiredCapabilities();
    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");

        capabilities.setBrowserName("chrome");
        capabilities.setCapability("browserVersion", "113");
        capabilities.setCapability("driverVersion", "113");
        options.merge(capabilities);
    }

    @Test
    @DisplayName("Test to retry operation")
    public void retryTest(VerisoftDriver driver){
        driver.get("https://www.wikipedia.org/");
        driver.findElement(By.id("searchInput")).sendKeys("Test Automation");
        new Actions(driver).sendKeys(Keys.ENTER).build().perform();

        Retry retry = new Retry(driver, 3, 1500, TimeUnit.MILLISECONDS);
        retry.attempt(new Attemptable() {
            @Override
            public void attempt() throws Throwable {
                //replace string with "Test automations" to see the retry in action
                if (driver.getTitle().contains("Test automation"))
                    return;
                else
                    throw new NotFoundException("Could not find element - retrying");
            }

            @Override
            public void onAttemptFail() {
                driver.navigate().refresh();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // No-op
                }
            }
        });
    }
}
