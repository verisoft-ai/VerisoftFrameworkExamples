package co.verisoft.pageobjects;

import co.verisoft.fw.pages.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class WikipediaMainPage extends BasePage {

    @FindBy(id = "searchInput")
    private WebElement searchBar;

    public static final String pageUrl = "https://www.wikipedia.org/";


    /**
     * C-tor. Initializes generic properties such as timeOut and pollingInterval
     *
     * @param driver a WebDriver object to store and use
     */
    public WikipediaMainPage(WebDriver driver) {
        super(driver);
    }


    @Override
    public boolean isOnPage() {
        return searchBar.isDisplayed();
    }


    public WikipediaResultPage searchForTerm(String term){
        searchBar.sendKeys(term);
        new Actions(driver).sendKeys(Keys.ENTER).build().perform();
        return new WikipediaResultPage(driver);
    }

    public WikipediaMainPage gotoPage(){
        driver.get(pageUrl);
        return this;
    }
}
