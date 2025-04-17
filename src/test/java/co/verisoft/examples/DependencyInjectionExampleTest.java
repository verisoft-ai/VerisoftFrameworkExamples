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
package co.verisoft.examples;

import co.verisoft.examples.dependencyinjection.CapabiitiesInjection;
import co.verisoft.examples.pageobjects.WikipediaMainPage;
import co.verisoft.examples.pageobjects.WikipediaResultPage;
import co.verisoft.fw.asserts.Asserts;
import co.verisoft.fw.report.observer.Report;
import co.verisoft.fw.selenium.drivers.VerisoftDriver;
import co.verisoft.fw.selenium.drivers.factory.DriverCapabilities;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.Capabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Locale;

@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CapabiitiesInjection.class})
public class DependencyInjectionExampleTest extends BaseTest {

    @DriverCapabilities
    @Autowired
    private Capabilities capabilities;


    @Test
    @DisplayName("Search Wikipedia test with spring extension")
    public void searchWikipedia(VerisoftDriver driver) {
        String phraseToSearch = "Test Automation";
        WikipediaMainPage wikipediaMainPage = new WikipediaMainPage(driver);
        WikipediaResultPage resultPage = wikipediaMainPage.gotoPage().searchForTerm(phraseToSearch);

        // Note!! Verisoft Assert
        Asserts.assertTrue(resultPage.isOnPage(), "Should be on the result page");
        Report.info("We reaeched the result page");

        Asserts.assertTrue(resultPage.getPageTitle()
                        .toLowerCase(Locale.ROOT).contains(phraseToSearch.toLowerCase())
                , "Title should containt the pharse " + phraseToSearch);
    }
}
