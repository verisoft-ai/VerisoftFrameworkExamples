package co.verisoft.examples;

import co.verisoft.fw.extentreport.ExtentReport;
import co.verisoft.fw.selenium.junit.extensions.DriverInjectionExtension;
import co.verisoft.fw.selenium.junit.extensions.ScreenShotExtension;
import co.verisoft.fw.selenium.junit.extensions.SeleniumLogExtesion;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@ExtentReport
@ExtendWith({DriverInjectionExtension.class, SeleniumLogExtesion.class, ScreenShotExtension.class})
public class BaseTest {
}
