package co.verisoft.examples;

import co.verisoft.fw.report.observer.Report;
import co.verisoft.fw.selenium.drivers.VerisoftDriver;
import co.verisoft.fw.selenium.drivers.factory.DriverCapabilities;
import co.verisoft.fw.utils.Asserts;
import co.verisoft.wikipedia.pageobjects.WikipediaMainPage;
import co.verisoft.wikipedia.pageobjects.WikipediaResultPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Locale;

@Execution(ExecutionMode.CONCURRENT)
public class BasicWebExampleTests extends BaseTest{

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
    @DisplayName("Search Wikipedia test")
    public void searchWikipedia(VerisoftDriver driver)  {
        driver.get("https://www.wikipedia.org/");
        driver.findElement(By.id("searchInput")).sendKeys("Test Automation");
        new Actions(driver).sendKeys(Keys.ENTER).build().perform();

        String phraseToAssert = "Test automation";

        // Note!! Verisoft Assert
        Asserts.assertTrue(driver.getTitle().contains(phraseToAssert), "Page should contain the pharase " + phraseToAssert);

        Report.info("Got to this point - We are on the right page");
        driver.quit();
    }


    @Test
    @DisplayName("Search Wikipedia with Page Objects")
    public void searchWikipediaWithPageObjects(VerisoftDriver driver){

        String phraseToSearch = "Test Automation";

        WikipediaMainPage wikipediaMainPage = new WikipediaMainPage(driver);
        WikipediaResultPage resultPage = wikipediaMainPage.gotoPage().searchForTerm(phraseToSearch);

        // Note!! Verisoft Assert
        Asserts.assertTrue(resultPage.isOnPage(), "Should be on the result page");
        Report.info("We reaeched the result page");

        Asserts.assertTrue(resultPage.getPageTitle().toLowerCase(Locale.ROOT)
                .contains(phraseToSearch.toLowerCase()), "Title should containt the pharse " + phraseToSearch);

    }


    @Test
    @DisplayName("Search Wikipedia with Page Objects - Failed Test")
    public void searchWikipediaWithPageObjectsFail(VerisoftDriver driver){

        String phraseToSearch = "Test Automation";

        WikipediaMainPage wikipediaMainPage = new WikipediaMainPage(driver);
        WikipediaResultPage resultPage = wikipediaMainPage.gotoPage().searchForTerm(phraseToSearch);

        // Note!! Verisoft Assert
        Asserts.assertTrue(resultPage.isOnPage(), "Should be on the result page");
        Report.info("We reaeched the result page");

        Asserts.assertFalse(resultPage.getPageTitle().toLowerCase(Locale.ROOT)
                .contains(phraseToSearch.toLowerCase()), "Title should containt the pharse " + phraseToSearch);

    }
}
