package com.expandtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.expandtest.base.BasePage;
import com.expandtest.utils.ConfigReader;

public class HomePage extends BasePage {

    private By loginTryBtn = By.xpath("//a[normalize-space()='Test Login Page']");
    private By notesMenu = By.xpath("//a[contains(text(),'Notes')]");
    private By webInputsCard = By.xpath("//a[contains(.,'Web inputs')]");
    private By radioBtn = By.xpath("//a[contains(.,'Radio Buttons')]");
    private By checkBoxesBtn = By.xpath("//a[contains(.,'Check Boxes')]");
    private By dropDownList = By.xpath("//a[contains(.,'Dropdown List')]");
    private By jsDialogsLink = By.xpath("//a[contains(.,'JS Dialogs')]");
    private By registerLink = By.xpath("//a[normalize-space()='Test Register Page']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void goToLoginPage() {
        click(loginTryBtn);
    }

    public void goToNotes() {
        driver.get(ConfigReader.getProperty("baseUrl") + "/notes/app/login");
    }

    public void waitForNotesApp() {
        wait.until(ExpectedConditions.urlContains("/notes/app"));
    }

    public void goToInputsPage() {
        safeClick(webInputsCard);
    }

    public void goToRadio() {
        safeClick(radioBtn);
    }

    public void goToCheckboxes() {
        safeClick(checkBoxesBtn);
    }

    public void goToDropdown() {
        safeClick(dropDownList);
    }

    public void goToJsAlerts() {
        safeClick(jsDialogsLink);
    }

    public void goToRegister() {
        safeClick(registerLink);
    }
    

    
    private void safeClick(By locator) {

        WebElement element = waitForElement(locator);

        ((JavascriptExecutor) driver)
                .executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element);

        ((JavascriptExecutor) driver)
                .executeScript(
                "arguments[0].click();",
                element);
    }
}