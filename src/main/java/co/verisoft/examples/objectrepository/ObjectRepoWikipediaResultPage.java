/*
 * (C) Copyright 2023 VeriSoft (http://www.verisoft.co)
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
import co.verisoft.fw.pages.WebBasePage;
import co.verisoft.fw.utils.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * A very basic web page object, using the Selenium4-Junit5 framework
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since May 2023
 */
public class ObjectRepoWikipediaResultPage extends WebBasePage {

    @ObjectRepositoryItem(id = "WIKI-SEARCH-BAR")
    private WebElement searchBar;


    /**
     * C-tor. Initializes generic properties such as timeOut and pollingInterval
     *
     * @param driver a WebDriver object to store and use
     */
    public ObjectRepoWikipediaResultPage(WebDriver driver) {
        super(driver);
    }


    @Override
    public boolean isOnPage() {
        driver.manage().window().maximize();

        // Verisoft wait object
        return Waits.visibilityOf(driver, timeOut, searchBar).isDisplayed();
    }


    public String getPageTitle(){
        return driver.getTitle();
    }
}
