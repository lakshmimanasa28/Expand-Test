package com.expandtest.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            try {

                String reportDir =
                        System.getProperty("user.dir")
                        + "/reports/";

                Files.createDirectories(
                        Paths.get(reportDir));

                String timeStamp =
                        new SimpleDateFormat(
                                "ddMMyyyy_HHmmss")
                                .format(new Date());

                String reportPath =
                        reportDir
                        + "ExtentReport_"
                        + timeStamp
                        + ".html";

                ExtentSparkReporter reporter =
                        new ExtentSparkReporter(
                                reportPath);

                reporter.config()
                        .setReportName(
                                "Automation Test Report");

                reporter.config()
                        .setDocumentTitle(
                                "Execution Results");

                extent = new ExtentReports();

                extent.attachReporter(
                        reporter);

                extent.setSystemInfo(
                        "Project",
                        "Expand Testing");

                extent.setSystemInfo(
                        "Tester",
                        "Lakshmi Manasa");

                extent.setSystemInfo(
                        "Environment",
                        "QA");

                extent.setSystemInfo(
                        "OS",
                        System.getProperty(
                                "os.name"));

                extent.setSystemInfo(
                        "Java Version",
                        System.getProperty(
                                "java.version"));

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        return extent;
    }
}