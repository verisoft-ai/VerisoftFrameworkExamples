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

import co.verisoft.examples.customlistener.CustomDriverInjectionExtension;
import co.verisoft.examples.customlistener.ExampleListener;
import co.verisoft.fw.extentreport.ExtentReport;
import co.verisoft.fw.selenium.drivers.VerisoftDriver;
import co.verisoft.fw.selenium.drivers.factory.DriverCapabilities;
import co.verisoft.fw.selenium.junit.extensions.DriverInjectionExtension;
import co.verisoft.fw.selenium.junit.extensions.ScreenShotExtension;
import co.verisoft.fw.selenium.junit.extensions.SeleniumLogExtesion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.WebDriverListener;

@ExtentReport
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith({SeleniumLogExtesion.class, ScreenShotExtension.class})
public class ListenersExampleTest {

    @DriverCapabilities
    private ChromeOptions capabilities = new ChromeOptions();

    @Test
    @ExtendWith(DriverInjectionExtension.class)
    public void addListenerInline(VerisoftDriver driver) {
        WebDriverListener listener = new ExampleListener();
        driver.addListener(listener);
        driver.get("https://www.google.com");
    }


    @Test
    @ExtendWith(CustomDriverInjectionExtension.class)
    public void addListenerUsingExtension(VerisoftDriver driver) {
        driver.get("https://www.google.com");
    }
}
