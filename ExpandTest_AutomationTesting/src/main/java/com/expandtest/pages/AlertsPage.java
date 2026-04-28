package com.expandtest.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.expandtest.base.BasePage;

public class AlertsPage extends BasePage {

    private By jsAlertBtn = By.id("js-alert");
    private By jsConfirmBtn = By.id("js-confirm");
    private By jsPromptBtn = By.id("js-prompt");
    private By resultText = By.id("dialog-response");

    public AlertsPage(WebDriver driver) {
        super(driver);
    }

    public void clickJsAlert() {
        click(jsAlertBtn);
    }

    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public void clickJsConfirm() {
        click(jsConfirmBtn);
    }

    public void dismissConfirm() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
    }

    public void clickJsPrompt() {
        click(jsPromptBtn);
    }

    public void enterPromptText(String text) {
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        alert.sendKeys(text);
        alert.accept();
    }

    public String getResultText() {
        return getText(resultText);
    }
}