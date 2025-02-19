package co.verisoft.examples;
import co.verisoft.examples.dependencyinjection.CapabiitiesInjection;
import co.verisoft.fw.CustomReportPortalExtension;
import co.verisoft.fw.asserts.Asserts;
import co.verisoft.fw.selenium.drivers.VerisoftDriver;
import co.verisoft.fw.selenium.drivers.factory.DriverCapabilities;
import co.verisoft.fw.selenium.junit.extensions.networkExtension.Network;
import co.verisoft.fw.selenium.junit.extensions.networkExtension.NetworkExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.test.context.ContextConfiguration;

@ExtendWith({CustomReportPortalExtension.class})
@ContextConfiguration(classes = {CapabiitiesInjection.class})
@NetworkExtension//(lastNetworkCalls = 5) you can choose how many last network calls you want
public class NetworkExtensionTest extends BaseTest {

    @DriverCapabilities
    ChromeOptions capabilities = new ChromeOptions();

    @Test
    public void retrieveNetworkCallsByObjectAndAtTheEndFail(VerisoftDriver driver1, VerisoftDriver driver2) {
        driver1.get("https:/google.com");
        Network.getLastRequest(driver1);
        driver2.get("https:/google.com");
        Network.getLastRequest(driver2);
        Network.getLastRequest(driver1);
        Network.getAllResponses(driver1);
        Network.getNumOfLastRequests(driver1,5);
        Network.getNumOfLastResponses(driver1,3);
        Asserts.assertTrue(false, "false");
    }
}
