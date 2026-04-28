package com.expandtest.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.expandtest.base.BaseTest;
import com.expandtest.pages.AlertsPage;
import com.expandtest.pages.HomePage;

public class AlertsTest extends BaseTest {

    private AlertsPage alerts;

    @BeforeMethod
    public void setupPage() {

        HomePage home = new HomePage(getDriver());

        getDriver().get("https://practice.expandtesting.com/js-dialogs");

        alerts = new AlertsPage(getDriver());
    }

    @Test
    public void verifyJsAlert() {
        alerts.clickJsAlert();
        alerts.acceptAlert();
        Assert.assertTrue(alerts.getResultText().contains("OK"));
    }

    @Test
    public void verifyJsConfirmDismiss() {
        alerts.clickJsConfirm();
        alerts.dismissConfirm();
        Assert.assertTrue(alerts.getResultText().contains("Cancel"));
    }

    @Test
    public void verifyJsPrompt() {
        alerts.clickJsPrompt();
        alerts.enterPromptText("Manasa");
        Assert.assertTrue(alerts.getResultText().contains("Manasa"));
    }
}