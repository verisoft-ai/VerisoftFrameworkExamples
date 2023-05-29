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

import co.verisoft.examples.tags.Suites;
import co.verisoft.fw.extentreport.ExtentReport;
import co.verisoft.fw.report.observer.Report;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

/**
 * Test example for using tag names as filters
 * Usage example (from CLI): mvn test -Dgroups=Sanity
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since May 2023
 */
@ExtentReport
@Execution(ExecutionMode.CONCURRENT)
public class UsingTagsTests{

    @Test
    @DisplayName("Sanity test")
    @Tag(Suites.Sanity)
    public void sanityTest() {
        Report.info("This is a sanity test");

    }


    @Test
    @DisplayName("Regression test")
    @Tag(Suites.Regression)
    public void regressionTest() {
        Report.info("This is a regression test");
    }

    @Test
    @DisplayName("Sanity and Regression test")
    @Tag(Suites.Sanity)
    @Tag(Suites.Regression)
    public void sanityAndRegression() {
        Report.info("This is a sanity and regression test");
    }
}
