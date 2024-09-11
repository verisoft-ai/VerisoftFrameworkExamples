package co.verisoft.examples;

import co.verisoft.fw.CustomReportPortalExtension;
import co.verisoft.fw.extentreport.ExtentReport;
import co.verisoft.fw.store.StoreManager;
import co.verisoft.fw.store.StoreType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@ExtentReport
@Disabled
@ExtendWith({CustomReportPortalExtension.class})
public class ReportPortalTest {

    @Test
    public void ShouldBeSkipped() {
        StoreManager.getStore(StoreType.GLOBAL).putValueInStore("isNull","isNotNull");
        assumeTrue(false);
    }

}
