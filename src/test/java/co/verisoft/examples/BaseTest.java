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

import co.verisoft.fw.extentreport.ExtentReport;
import co.verisoft.fw.selenium.junit.extensions.DriverInjectionExtension;
import co.verisoft.fw.selenium.junit.extensions.ScreenShotExtension;
import co.verisoft.fw.selenium.junit.extensions.SeleniumLogExtesion;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 *
 * BaseTest serves as the base class for all test classes in this project.
 * It includes necessary extensions and configurations for running tests.
 *
 * <p><strong>Important:</strong> Do not use the {@code @Execution(ExecutionMode.CONCURRENT)}
 * annotation with test classes that extend BaseTest, as it may lead to issues when multiple
 * tests run concurrently. This base class is not designed for concurrent execution.
 */
@ExtentReport
@ExtendWith({DriverInjectionExtension.class, SeleniumLogExtesion.class, ScreenShotExtension.class})
public class BaseTest {
}
