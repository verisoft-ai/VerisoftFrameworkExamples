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
package co.verisoft.examples;

import co.verisoft.examples.dependencyinjection.CapabiitiesInjection;
import co.verisoft.fw.report.observer.Report;
import co.verisoft.fw.selenium.drivers.VerisoftDriver;
import co.verisoft.fw.selenium.drivers.factory.DriverCapabilities;
import co.verisoft.fw.utils.Asserts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CapabiitiesInjection.class})
public class DependencyInjectionExampleTest extends BaseTest{

    @DriverCapabilities
    @Autowired
    private DesiredCapabilities capabilities;


    @Test
    @DisplayName("Search Wikipedia test with spring extension")
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
}
