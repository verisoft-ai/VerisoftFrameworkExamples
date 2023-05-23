package co.verisoft.examples;

import co.verisoft.fw.report.observer.Report;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MiscTest {


    @Test
    @DisplayName("Skips test - Not fail and Not pass")
    public void SkipTest() {

        Report.info("Going to skip the test");

        // Skips the test, write a logical operation here.
        Assumptions.assumeTrue(false);
    }
}
