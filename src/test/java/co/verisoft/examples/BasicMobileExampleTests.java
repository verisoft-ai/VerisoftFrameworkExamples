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

import co.verisoft.examples.pageobjects.MobileChromeTurnOnSyncPage;
import co.verisoft.examples.pageobjects.MobileChromeWelcomePage;
import co.verisoft.fw.asserts.Asserts;
import co.verisoft.fw.report.observer.Report;
import co.verisoft.fw.selenium.drivers.VerisoftMobileDriver;
import co.verisoft.fw.selenium.drivers.factory.DriverCapabilities;
import co.verisoft.fw.selenium.drivers.factory.DriverUrl;
import co.verisoft.fw.utils.Waits;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;

@Execution(ExecutionMode.SAME_THREAD)
public class BasicMobileExampleTests extends BaseTest {

    @DriverUrl
    private final URL url = new URL("http://127.0.0.1:4723/wd/hub/");

    @DriverCapabilities
    UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
    {
        uiAutomator2Options.setPlatformName(ANDROID)
            .setUdid("RF8M91XQ8TW")
            .setPlatformVersion("12")
            .setAutoGrantPermissions(true)
            .setNoReset(false)
            .setNewCommandTimeout(Duration.ofMinutes(5))
            .setAppActivity(".MainActivity")
            .setAppPackage("io.water.hydration")
            .setAutomationName("Flutter");
   }



    public BasicMobileExampleTests() throws MalformedURLException {
    }


    @Test
    @DisplayName("Click On Element")
    public void clickOnElement(VerisoftMobileDriver driver) {

        driver.findElement(By.id("com.android.chrome:id/terms_accept")).click();
        WebElement e = Waits.visibilityOfElementLocated(driver, 30, By.id("com.android.chrome:id/negative_button"));

        // Note!! Verisoft Assert
        String phrase = "No thanks";
        Asserts.assertTrue(e.getText().contains(phrase), "Page should contain the pharase: " + phrase);

        Report.info("Got to this point - We are operating Google chrome on mobile deviceS");
    }


    @Test
    @DisplayName("Click On Element with Page Object")
    public void clickOnElementWithPageObject(VerisoftMobileDriver driver) {

        MobileChromeWelcomePage welcomePage = new MobileChromeWelcomePage(driver);
        MobileChromeTurnOnSyncPage turnOnSyncPage = welcomePage.accept();

        // Note!! Verisoft Assert
        Asserts.assertTrue(turnOnSyncPage.isOnPage(), "Should be on page");

        Report.info("Got to this point - We are operating Google chrome on mobile deviceS");
    }
}
