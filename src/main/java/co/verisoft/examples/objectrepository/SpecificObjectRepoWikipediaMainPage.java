/*
 * (C) Copyright 2025 VeriSoft (http://www.verisoft.ai)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package co.verisoft.examples.objectrepository;

import co.verisoft.fw.objectrepository.ObjectRepositoryItem;
import co.verisoft.fw.objectrepository.PageObjectName;
import co.verisoft.fw.pages.WebBasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * A very basic web page object, using the Selenium4-Junit5 framework
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since May 2023
 */
@PageObjectName
public class SpecificObjectRepoWikipediaMainPage extends WebBasePage {

    @ObjectRepositoryItem(id = "WIKI-MAIN-SEARCH")
    private WebElement searchBar;

    public static final String pageUrl = "https://www.wikipedia.org/";


    /**
     * C-tor. Initializes generic properties such as timeOut and pollingInterval
     *
     * @param driver a WebDriver object to store and use
     */
    public SpecificObjectRepoWikipediaMainPage(WebDriver driver,String objectRepositoryPath) {
        super(driver,objectRepositoryPath);
    }


    @Override
    public boolean isOnPage() {
        return searchBar.isDisplayed();
    }


    public ObjectRepoWikipediaResultPage searchForTerm(String term){
        searchBar.sendKeys(term);
        new Actions(driver).sendKeys(Keys.ENTER).build().perform();
        return new ObjectRepoWikipediaResultPage(driver);
    }


    public SpecificObjectRepoWikipediaMainPage gotoPage(){
        driver.get(pageUrl);
        return this;
    }
}
