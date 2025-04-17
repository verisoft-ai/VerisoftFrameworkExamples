package co.verisoft.examples;

import co.verisoft.examples.pageobjects.WikipediaMainPage;
import co.verisoft.examples.pageobjects.WikipediaResultPage;
import co.verisoft.fw.asserts.Asserts;
import co.verisoft.fw.selenium.drivers.VerisoftDriver;
import co.verisoft.fw.selenium.drivers.factory.DriverCapabilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SingleSessionDriverTest extends BaseTest{

    private final WebDriver driver;

    @DriverCapabilities
    private final DesiredCapabilities capabilities = new DesiredCapabilities();

    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");

        capabilities.setBrowserName("chrome");
        options.merge(capabilities);
    }

    public SingleSessionDriverTest(){
        this.driver = new VerisoftDriver(capabilities);
    }

    @BeforeEach
    public void setup(){
        this.driver.get("https://www.wikipedia.org");
    }

    @Test
    public void test1(){
        WikipediaMainPage wikipediaMainPage = new WikipediaMainPage(driver);
        WikipediaResultPage wikipediaResultPage = wikipediaMainPage.searchForTerm("Test Automation");
        Asserts.assertTrue(wikipediaResultPage.getPageTitle().toLowerCase().contains("Test Automation".toLowerCase()),
                "Should contain");
    }
}
