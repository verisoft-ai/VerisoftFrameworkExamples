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
package co.verisoft.examples.extensions;


import co.verisoft.fw.extensions.jupiter.ExtensionUtilities;
import co.verisoft.fw.report.observer.Report;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.*;

import java.util.Optional;

/**
 * <b>A generic extension implementation.</b><br>
 * This class is meant to serve as an example to copy parts from it and use them for other extension
 * implementations. <br>
 * <b>Note!! You don't have to implement all the listed methods for all the extensions. Use this class as example only,
 * choose the method that fit your needs and implements it's interface only in the extension</b>
 * <p>
 * Some considerations when implementing extensions:<br>
 * 1. You must register the extension at the class level in order for it to be invoked.<br>
 * 2. afterAll and beforeAll work <b>per container</b>, which means that if you run several tests from different test
 * classes these methods will be invoked <b>more than once</b>.<br>
 * 3. You may use more than 1 extension. Junit 5 guarantees that all extension methods will be executed.<br>
 * 4. <b>However!</b> Junit 5 does not guarantee the order of execution. So keep the single responsibility and
 * independence of extensions.
 * </p><br>
 * <p>
 * Some useful links to refer to:<br>
 * 1. Understanding Junit 5 extension model, from <a href="https://junit.org/junit5/docs/current/user-guide/#extensions">Junit5 user guide</a><br>
 * 2. Test lifecycle callback order is listed <a href="https://junit.org/junit5/docs/current/user-guide/#extensions-lifecycle-callbacks">here</a><br>
 * 3. An example of VeriSoft: @see co.verisoft.selenium.framework.extensions.junit.PageSourceSaverExtension
 * 4. Junit <a href="http://javadox.com/org.junit.jupiter/junit-jupiter-api/5.5.2/org/junit/jupiter/api/extension/package-summary.html">extension package javadoc</a>
 * </p> <br>
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a> @ <a href="http://www.verisoft.co">www.VeriSoft.co</a>
 * @since 2.0.3.9
 */
@Slf4j
@SuppressWarnings("unused")
public class ExampleExtension implements
        AfterTestExecutionCallback,
        AfterAllCallback,
        AfterEachCallback,
        BeforeAllCallback,
        BeforeEachCallback,
        BeforeTestExecutionCallback,
        ExtensionContext.Store.CloseableResource,
        TestWatcher {


    // Since the beforeAll method will be invoked for each class registered with this extension, and there are pieces
    // of code we wish to run ONLY ONCE per execution, we mark this flag and use it in the beforeAll method
    private static boolean didRun = false;

    private static final Object lock = new Object();
    /**
     * Callback that is invoked once <em>after</em> all tests in the current
     * container.
     *
     * @param context the current extension context; never {@code null}
     */
    @Override
    public void beforeAll(ExtensionContext context) {
        synchronized (lock) {
            // This section will run once per suite
            if (didRun == false) {

                // This will NOT be shown in some report observers (i.e extents reports), since there is no active test at this point
                Report.info("Before Suite: This part will only be executed once per suite");
                didRun = true;
            }

            // This section will run once per test class
            Report.info("BeforeAll: This part will only be executed once per class");
        }
    }


    /**
     * Callback that is invoked <em>after</em> an individual test and any
     * user-defined teardown methods for that test have been executed.
     *
     * @param context the current extension context; never {@code null}
     */
    @Override
    public void afterEach(ExtensionContext context) {
        Report.info("AfterEach: This part will be executed after each test an after test execution");
    }


    /**
     * Callback that is invoked <em>immediately after</em> an individual test has
     * been executed but before any user-defined teardown methods have been
     * executed for that test.
     *
     * @param context the current extension context; never {@code null}
     */
    @Override
    public void afterTestExecution(ExtensionContext context) {
        Report.info("AfterTestExecution: This part will be executed after test");
    }


    /**
     * Callback that is invoked once <em>before</em> all tests in the current
     * container.
     *
     * @param context the current extension context; never {@code null}
     */
    @Override
    public void afterAll(ExtensionContext context) {
        Report.info("AfterAll: This part will only be executed once after all tests in the class executes");
    }


    /**
     * Callback that is invoked <em>before</em> an individual test and any
     * user-defined setup methods for that test have been executed.
     *
     * @param context the current extension context; never {@code null}
     */
    @Override
    public void beforeEach(ExtensionContext context) {
        Object [] args=  ExtensionUtilities.getTestMethodArgumentsFromExtensionContext(context);
        // This will NOT be shown in some report observers (i.e extents reports), since there is no active test at this point
        Report.info("BeforeEach: This part will be executed before each test and before test execution");
    }


    /**
     * Callback that is invoked <em>immediately before</em> an individual test is
     * executed but after any user-defined setup methods have been executed
     * for that test.
     *
     * @param context the current extension context; never {@code null}
     */
    @Override
    public void beforeTestExecution(ExtensionContext context) {
        Report.info("BeforeTestExecution: This part will only be executed before test and after BeforeEach");
    }


    /**
     * Invoked after a disabled test has been skipped.
     *
     * <p>The default implementation does nothing. Concrete implementations can
     * override this method as appropriate.
     *
     * @param context the current extension context; never {@code null}
     * @param reason  the reason the test is disabled; never {@code null} but
     *                potentially <em>empty</em>
     */
    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        String s = "GeneralExampleExtension- Test " + context.getDisplayName() + " was disabled";
        if (reason.isPresent())
            s += ", reason: " + reason;

        Report.info(s);
    }


    /**
     * Invoked after a test has completed successfully.
     *
     * <p>The default implementation does nothing. Concrete implementations can
     * override this method as appropriate.
     *
     * @param context the current extension context; never {@code null}
     */
    @Override
    public void testSuccessful(ExtensionContext context) {
        String s = "GeneralExampleExtension- Test " + context.getDisplayName() + " passed";
        Report.info(s);
    }


    /**
     * Invoked after a test has been aborted.
     *
     * <p>The default implementation does nothing. Concrete implementations can
     * override this method as appropriate.
     *
     * @param context the current extension context; never {@code null}
     * @param cause   the throwable responsible for the test being aborted; may be {@code null}
     */
    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        String s = "GeneralExampleExtension- Test " + context.getDisplayName() + " aborted";
        s += ", cause: " + cause.getMessage();

        Report.info(s);
    }


    /**
     * Invoked after a test has failed.
     *
     * <p>The default implementation does nothing. Concrete implementations can
     * override this method as appropriate.
     *
     * @param context the current extension context; never {@code null}
     * @param cause   the throwable that caused test failure; may be {@code null}
     */
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        String s = "GeneralExampleExtension- Test " + context.getDisplayName() + " failed";
        s += ", cause: " + cause.getMessage();

        Report.error(s);
    }


    /**
     * Invoked after all tests have been executed, from all classes. This method will run while junit engine is
     * shutting donwn.
     */
    @Override
    public void close() {
        System.out.println("This is the last method, after all other methods and extension methods are performed");
    }
}
