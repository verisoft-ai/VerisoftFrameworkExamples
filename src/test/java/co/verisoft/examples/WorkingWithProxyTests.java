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
import co.verisoft.fw.report.observer.Report;
import co.verisoft.fw.selenium.drivers.VerisoftMobileDriver;
import co.verisoft.fw.selenium.drivers.factory.DriverCapabilities;
import co.verisoft.fw.selenium.drivers.factory.DriverCommandExecutor;
import co.verisoft.fw.utils.Waits;
import io.appium.java_client.MobileCommand;
import io.appium.java_client.remote.AppiumCommandExecutor;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.http.netty.ProxyNettyClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.MalformedURLException;
import java.net.URL;

public class WorkingWithProxyTests extends BaseTest {

    @DriverCapabilities
    private DesiredCapabilities capabilities = new DesiredCapabilities();

    {
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability("appPackage", "com.android.chrome");
        capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");
        capabilities.setCapability("automationName", "UIAutomator2");
    }

    @DriverCommandExecutor
    private HttpCommandExecutor commandExecutor = new AppiumCommandExecutor(
            MobileCommand.commandRepository, new URL("http://127.0.0.1:4723/wd/hub/"), new ProxyNettyClient.Factory());


    public WorkingWithProxyTests() throws MalformedURLException {
    }


    @Test
    @DisplayName("Working with proxy example")
    public void testWithProxy(@DriverCommandExecutor @Autowired HttpCommandExecutor commandExecutor, VerisoftMobileDriver driver) {
        driver.findElement(By.id("com.android.chrome:id/terms_accept")).click();
        WebElement e = Waits.visibilityOfElementLocated(driver, 30, By.id("com.android.chrome:id/negative_button"));

        // Note!! Verisoft Assert
        String phrase = "No thanks";
        Asserts.assertTrue(e.getText().contains(phrase), "Page should contain the pharase: " + phrase);

        Report.info("Got to this point - We are operating Google chrome on mobile deviceS");
    }
}
