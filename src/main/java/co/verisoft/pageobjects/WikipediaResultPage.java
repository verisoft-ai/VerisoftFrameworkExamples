package co.verisoft.pageobjects;

import co.verisoft.fw.pages.BasePage;
import co.verisoft.fw.utils.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class WikipediaResultPage extends BasePage {

    @FindBy(xpath = "//input[@name='search']")
    private WebElement searchBar;


    /**
     * C-tor. Initializes generic properties such as timeOut and pollingInterval
     *
     * @param driver a WebDriver object to store and use
     */
    public WikipediaResultPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOnPage() {
        driver.manage().window().maximize();
        return Waits.visibilityOf(driver, timeOut, searchBar).isDisplayed();
    }


    public String getPageTitle(){
        return driver.getTitle();
    }

}
