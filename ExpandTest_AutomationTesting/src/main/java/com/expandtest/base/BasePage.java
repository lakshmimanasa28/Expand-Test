package com.expandtest.base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.expandtest.utils.ConfigReader;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ConfigReader config;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.config = new ConfigReader();
        this.wait = new WebDriverWait(driver,
                Duration.ofSeconds(config.getTimeout()));
    }

    // Wait for visible element
    public WebElement waitForElement(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Click method
    public void click(By locator) {

        WebElement element = waitForElement(locator);

        try {
            element.click();
        } catch (Exception e) {

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    element);

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    element);
        }
    }

    // Type text
    public void type(By locator, String text) {
        WebElement element = waitForElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    // Get text
    public String getText(By locator) {
        return waitForElement(locator).getText();
    }

    // Check selected
    public boolean isSelected(By locator) {
        return waitForElement(locator).isSelected();
    }

    // Get attribute
    public String getAttribute(By locator, String attribute) {
        return waitForElement(locator).getAttribute(attribute);
    }

    // Dropdown select
    public void selectByVisibleText(By locator, String text) {
        Select select = new Select(waitForElement(locator));
        select.selectByVisibleText(text);
    }

    // Get selected dropdown value
    public String getSelectedOptionText(By locator) {
        Select select = new Select(waitForElement(locator));
        return select.getFirstSelectedOption().getText();
    }

    // Checkbox helpers
    public void selectColor(String color) {
        click(By.xpath("//label[normalize-space()='" + color + "']"));
    }

    public void selectSport(String sport) {
        click(By.xpath("//label[normalize-space()='" + sport + "']"));
    }

    public boolean isColorSelected(String color) {
        return driver.findElement(
                By.xpath("//label[normalize-space()='" + color + "']/input"))
                .isSelected();
    }

    public boolean isSportSelected(String sport) {
        return driver.findElement(
                By.xpath("//label[normalize-space()='" + sport + "']/input"))
                .isSelected();
    }

    // Current URL
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // Page title
    public String getTitle() {
        return driver.getTitle();
    }
}