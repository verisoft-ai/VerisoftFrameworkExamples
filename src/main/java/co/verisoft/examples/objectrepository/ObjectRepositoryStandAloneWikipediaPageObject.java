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
