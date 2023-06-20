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
import co.verisoft.fw.report.observer.ReportPublisher;
import co.verisoft.examples.reportobserver.SampleObserver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.List;


@Execution(ExecutionMode.SAME_THREAD)
public class CustomReportObserverTest extends BaseTest {

    private final SampleObserver sampleObserver;

    public CustomReportObserverTest() {
        sampleObserver = new SampleObserver();
    }


    @Test
    public void shouldWriteEntry() {

        // Setup
        List<String> list = sampleObserver.getEntries();

        // Test
        Report.trace("Trace message - should only appear on log");

        // Note!! Verisoft Assert
        Asserts.assertTrue(list.stream().anyMatch(x -> x.contains("Trace message")), "Should appear on log");
    }


    @Test
    public void shouldBeAbleToUnregisterExtentReport() {

        // Setup
        List<String> list = sampleObserver.getEntries();

        // Test
        Report.trace("Trace message - should only appear on log");

        // Note!! Verisoft Assert
        Asserts.assertTrue(list.stream().anyMatch(x -> x.contains("Trace message")), "Should appear on log");

        ReportPublisher.getInstance().unregister(sampleObserver);

        Report.error("Error message - SHOULD appear on log");
        Asserts.assertFalse(list.stream().anyMatch(x -> x.contains("Error message")), "Should NOT appear on log");

        // Cleanup
        ReportPublisher.getInstance().register(sampleObserver);

    }


    @Test
    public void shouldNotAllowToRegisterSameObjectTwice() {

        // Setup
        List<String> list = sampleObserver.getEntries();


        // Test
        Report.info("Info message - SHOULD appear on log");

        // Note!! Verisoft Assert
        Asserts.assertTrue(list.stream().anyMatch(x -> x.contains("Info message")), "Should appear on log");

        ReportPublisher.getInstance().register(sampleObserver); // Will not register twice
        ReportPublisher.getInstance().unregister(sampleObserver); // Will unregister - no listener

        Report.warn("Warn message - SHOULD appear on log");
        Asserts.assertFalse(list.stream().anyMatch(x -> x.contains("Warn message")), "Should NOT appear on log");


        // Cleanup
        ReportPublisher.getInstance().register(sampleObserver);
    }
}
