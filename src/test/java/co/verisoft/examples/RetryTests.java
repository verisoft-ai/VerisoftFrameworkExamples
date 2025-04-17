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

import co.verisoft.fw.selenium.drivers.VerisoftDriver;
import co.verisoft.fw.selenium.drivers.factory.DriverCapabilities;
import co.verisoft.fw.utils.Attemptable;
import co.verisoft.fw.utils.Retry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class RetryTests extends BaseTest {

    @DriverCapabilities
    private final DesiredCapabilities capabilities = new DesiredCapabilities();

    {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");

        capabilities.setBrowserName("chrome");
        options.merge(capabilities);
    }

    @Test
    @DisplayName("Test to retry operation")
    public void retryTest(VerisoftDriver driver) {
        driver.get("https://www.wikipedia.org/");
        driver.findElement(By.id("searchInput")).sendKeys("Test Automation");
        new Actions(driver).sendKeys(Keys.ENTER).build().perform();

        Retry retry = new Retry(driver, 3, 1500, TimeUnit.MILLISECONDS);
        retry.attempt(new Attemptable() {
            @Override
            public void attempt() {
                //replace string with "Test automations" to see the retry in action
                if (driver.getTitle().contains("Test automation"))
                    return;
                else
                    throw new NotFoundException("Could not find element - retrying");
            }

            @Override
            public void onAttemptFail() {
                driver.navigate().refresh();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // No-op
                }
            }
        });
    }
}
