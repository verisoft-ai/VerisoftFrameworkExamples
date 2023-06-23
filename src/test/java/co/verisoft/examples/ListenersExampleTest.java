package co.verisoft.examples;

import co.verisoft.fw.selenium.drivers.VerisoftDriver;
import co.verisoft.fw.selenium.drivers.factory.DriverCapabilities;
import co.verisoft.fw.selenium.listeners.AlertListener;
import co.verisoft.fw.utils.Waits;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

@Execution(ExecutionMode.CONCURRENT)
public class ListenersExampleTest extends BaseTest {

    @DriverCapabilities
    private ChromeOptions capabilities = new ChromeOptions();

    @Test
    public void addListener(VerisoftDriver driver) {
        WebDriverListener listener = new AlertListener();
        WebDriver newDriver = new EventFiringDecorator(listener).decorate(driver);
        newDriver.get("https://www.google.com");
        Waits.milliseconds(4000);
    }
}


