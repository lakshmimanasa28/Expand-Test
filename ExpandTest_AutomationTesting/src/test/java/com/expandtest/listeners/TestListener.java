package com.expandtest.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.expandtest.base.BaseTest;
import com.expandtest.utils.ExtentManager;
import com.expandtest.utils.ScreenshotUtil;

public class TestListener implements ITestListener {

    private static ExtentReports extent =
            ExtentManager.getInstance();

    private static ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

        test.set(
                extent.createTest(
                        result.getMethod()
                              .getMethodName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        WebDriver driver =
                BaseTest.getDriverStatic();

        test.get().fail(
                result.getThrowable());

        String path =
                ScreenshotUtil.capture(
                        driver,
                        result.getMethod()
                              .getMethodName());

        if (path != null) {
            try {
                test.get()
                    .addScreenCaptureFromPath(path);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        test.get().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();
    }
}