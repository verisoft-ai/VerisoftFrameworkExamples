package co.verisoft.examples;

import co.verisoft.fw.CustomReportPortalExtension;
import co.verisoft.fw.extentreport.Description;
import co.verisoft.fw.extentreport.ExtentReport;
import co.verisoft.fw.report.observer.Report;
import com.epam.reportportal.listeners.LogLevel;
import com.epam.reportportal.service.ReportPortal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

@ExtentReport
@ExtendWith({CustomReportPortalExtension.class})
public class MorgtagesDemo {

    public enum TestParams {
        ONE,
        TWO
    }

    @Test
    @DisplayName("תהליך בקשה לאישור עקרוני")
    public void principalRequest() throws InterruptedException {
        Report.info("כניסה למסך בקשה חדשה");
        Report.info("הזנת פרטים");
        Report.info("הזנת סכום");
        Report.info("קבלת אישור אוטומטי");
//        Thread.sleep(8000);
    }

    @Test
    @DisplayName("תהליך בקשה למחזור")
    public void retake() throws InterruptedException {
        Report.info("כניסה למסך לקוח קיים");
        Report.info("הזנת פרטים");
        Report.info("בקשה לביטול קיים");
//        Thread.sleep(8000);
        Random rand = new Random();
        int i = rand.nextInt(3);
        if (i == 2)
            Report.info("ביטול הצליח");
        else{
            Report.error("משהו קרה");
            throw new IllegalStateException("error occured");
        }
    }

    @Test
    @DisplayName("משכנתא דיגיטלית")
    public void digital() throws InterruptedException {
        Report.info("הזנת פרטים");
        Report.info("קבלת OTP");
        Report.info("בקשה לאישור עקרוני");
//        Thread.sleep(8000);

        Random rand = new Random();
        int i = rand.nextInt(2);
        if (i == 1)
            Report.info("תהליך הצליח");
        else{
            Report.error("תהליך נכשל");
            throw new RuntimeException("failed digital");
        }
    }

    @ParameterizedTest
    @EnumSource(TestParams.class)
    @DisplayName("בדיקת פרמטרים")
    public void testParameters(TestParams param) throws AWTException, IOException {
        Report.info("בדיקה: " + param.name());
        File outputFile = null;

        try {
            // Create a Robot instance
            Robot robot = new Robot();

            // Capture the screen
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenImage = robot.createScreenCapture(screenRect);

            // Save the captured image to a file
            outputFile = new File("screenshot.png");
            ImageIO.write(screenImage, "png", outputFile);

            System.out.println("תמונה נשמרה");
            ReportPortal.emitLog("זו התמונה", LogLevel.INFO.name(), new Date(), outputFile);
        } catch (AWTException | IOException e) {
            System.out.println("An error occurred while capturing the screenshot: " + e.getMessage());
        }
        
    }

}
