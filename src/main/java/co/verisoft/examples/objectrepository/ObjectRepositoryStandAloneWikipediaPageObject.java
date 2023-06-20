package co.verisoft.examples.objectrepository;

import co.verisoft.fw.objectrepository.ObjectReporsitoryFactory;
import co.verisoft.fw.objectrepository.ObjectRepositoryItem;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ObjectRepositoryStandAloneWikipediaPageObject {

    @ObjectRepositoryItem(id = "WIKI-MAIN-SEARCH")
    private WebElement searchBar;

    private WebDriver driver;

    public static final String pageUrl = "https://www.wikipedia.org/";


    /**
     * C-tor. Initializes generic properties such as timeOut and pollingInterval
     *
     * @param driver a WebDriver object to store and use
     */
    public ObjectRepositoryStandAloneWikipediaPageObject(WebDriver driver) {

        this.driver = driver;
        ObjectReporsitoryFactory.initObjects(driver, this);
    }



    public ObjectRepoWikipediaResultPage searchForTerm(String term){
        searchBar.sendKeys(term);
        new Actions(driver).sendKeys(Keys.ENTER).build().perform();
        return new ObjectRepoWikipediaResultPage(driver);
    }


    public ObjectRepositoryStandAloneWikipediaPageObject gotoPage(){
        driver.get(pageUrl);
        return this;
    }
}
