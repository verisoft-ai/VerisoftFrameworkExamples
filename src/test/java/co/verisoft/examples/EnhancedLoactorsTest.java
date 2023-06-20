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

import co.verisoft.fw.asserts.Asserts;
import co.verisoft.fw.selenium.drivers.VerisoftDriver;
import co.verisoft.fw.selenium.drivers.factory.DriverCapabilities;
import co.verisoft.fw.utils.locators.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.List;

@Execution(ExecutionMode.CONCURRENT)
public class EnhancedLoactorsTest extends BaseTest {

    private static final String pageTestUrl = "file://" +
            new File(System.getProperty("user.dir") +
                    "/src/test/resources/framework.html").getPath();


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
    public void shouldFindLocatorFromSeveralLocators(VerisoftDriver driver) {
        driver.get(pageTestUrl);
        List<WebElement> elements = driver.findElements(AnyBy.any(By.id("will-not-find"),
                By.cssSelector("#option1")));

        // Note!! Verisoft Assert
        Asserts.assertEquals(1, elements.size(), "Should find 1 elements");
    }


    @Test
    public void shouldNotFindLocatorFromDifferentObjects(VerisoftDriver driver) {
        driver.get(pageTestUrl);
        List<WebElement> elements = driver.findElements(AllBy.all(By.xpath("//*[@id='option1']"),
                By.id("option2")));

        // Note!! Verisoft Assert
        Asserts.assertEquals(0, elements.size(), "Should find 0 elements");
    }


    @Test
    public void shouldFindLocatorFromSameObject(VerisoftDriver driver) {
        driver.get(pageTestUrl);
        List<WebElement> elements = driver.findElements(AllBy.all(By.xpath("//*[@id='option1']"),
                By.name("options")));

        // Note!! Verisoft Assert
        Asserts.assertEquals(1, elements.size(), "Should find 1 elements");
    }


    @Test
    public void shouldNotFindOption1Object(VerisoftDriver driver) {
        driver.get(pageTestUrl);
        List<WebElement> elements = driver.findElements(NotBy.not(By.id("option1")));

        boolean foundObject = false;
        for (WebElement e : elements)
            if (e.getAttribute("id").equals("option1")) {
                foundObject = true;
                break;
            }

        // Note!! Verisoft Assert
        Asserts.assertFalse(foundObject, "Should not find WebElement");
    }

    @Test
    public void shouldFindOption2Object(VerisoftDriver driver) {
        driver.get(pageTestUrl);
        List<WebElement> elements = driver.findElements(NotBy.not(By.id("option1")));

        boolean foundObject = false;
        for (WebElement e : elements)
            if (e.getAttribute("id").equals("option2")) {
                foundObject = true;
                break;
            }

        // Note!! Verisoft Assert
        Asserts.assertTrue(foundObject, "Should find WebElement");
    }


    @Test
    void elementByFoundResults(VerisoftDriver driver) {
        driver.get(pageTestUrl);
        List<WebElement> elements = driver.findElements(ElementBy.partialText("checkbox"));

        // Note!! Verisoft Assert
        Asserts.assertTrue(1 <= elements.size(), "Expected more than one element to be found");
    }


    @Test
    void inputByOneResult(VerisoftDriver driver) {
        driver.get(pageTestUrl);
        List<WebElement> elements = driver.findElements(InputBy.label("label"));

        // Note!! Verisoft Assert
        Asserts.assertEquals(1, elements.size(), "Expected to found 1 element");
        Asserts.assertEquals("texta", elements.get(0).getAttribute("id"), "Expected to find 'texta' ");
    }


    @Test
    void tdBySpecificValueCellCol(VerisoftDriver driver) {
        driver.get(pageTestUrl);
        WebElement element = driver.findElement(TdBy.cellLocation(1, 0));

        // Note!! Verisoft Assert
        Asserts.assertEquals("January", element.getText(), "Expected to found and element with value 'January'");

    }

    @Test
    void tdBySpecificHeaderByCol(VerisoftDriver driver) {
        driver.get(pageTestUrl);
        WebElement element = driver.findElement(TdBy.tableHeader(0));

        // Note!! Verisoft Assert
        Asserts.assertEquals("Month", element.getText(), "Expected to found and element with value 'January'");

    }
}
