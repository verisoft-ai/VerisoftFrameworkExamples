package co.verisoft.examples;

import io.github.pavelicii.allpairs4j.AllPairs;
import io.github.pavelicii.allpairs4j.Parameter;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

/**
 * Pairwise testing example
 * Based on: https://github.com/pavelicii/allpairs4j
 * @author Nir Gallner, VeriSoft
 */
public class AllPairsTest {


    @Test
    public void noConstraintPairwiseTest() {
        AllPairs allPairs = new AllPairs.AllPairsBuilder()
                .withParameters(Arrays.asList(
                        new Parameter("Browser", "Chrome", "Safari", "Edge"),
                        new Parameter("OS", "Windows", "Linux", "macOS"),
                        new Parameter("RAM", 2048, 4096, 8192, 16384),
                        new Parameter("Drive", "HDD", "SSD")))
                .build();

        System.out.println(allPairs);
    }


    @Test
    public void genericPairWithConstraint() {
        // https://github.com/pavelicii/allpairs4j
        // https://github.com/walkframe/covertable
        AllPairs allPairs = new AllPairs.AllPairsBuilder()
                .withTestCombinationSize(3)
                .withParameters(Arrays.asList(
                        new Parameter("Browser", "Chrome", "Safari", "Edge"),
                        new Parameter("OS", "Windows", "Linux", "macOS"),
                        new Parameter("RAM", 2048, 4096, 8192, 16384),
                        new Parameter("Drive", "HDD", "SSD"),
                        new Parameter("Type", "Happy", "Negative", "Positive")))
                .withConstraint(c -> c.get("Browser").equals("Safari") && !c.get("OS").equals("macOS"))
                .build();

        System.out.println(allPairs);
    }
}
