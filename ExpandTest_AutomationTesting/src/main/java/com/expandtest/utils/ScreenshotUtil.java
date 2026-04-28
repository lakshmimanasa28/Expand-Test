package com.expandtest.utils;



import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String capture(
            WebDriver driver,
            String testName) {

        try {

            String screenshotDir =
                    System.getProperty("user.dir")
                    + "/screenshots/";

            Files.createDirectories(
                    Paths.get(screenshotDir));

            File source =
                    ((TakesScreenshot) driver)
                    .getScreenshotAs(
                            OutputType.FILE);

            String timeStamp =
                    new SimpleDateFormat(
                            "ddMMyyyy_HHmmss")
                            .format(new Date());

            String filePath =
                    screenshotDir
                    + testName
                    + "_"
                    + timeStamp
                    + ".png";

            Files.copy(
                    source.toPath(),
                    Paths.get(filePath),
                    StandardCopyOption.REPLACE_EXISTING);

            return filePath;

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }
}