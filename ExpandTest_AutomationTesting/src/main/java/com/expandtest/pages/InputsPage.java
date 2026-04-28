package com.expandtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.expandtest.base.BasePage;

public class InputsPage extends BasePage {

   
    private By numberInput = By.id("input-number");
    private By textInput = By.id("input-text");
    private By passwordInput = By.id("input-password");
    private By dateInput = By.id("input-date");

    
    private By dropdown = By.id("dropdown");

    public InputsPage(WebDriver driver) {
        super(driver);
    }

    public void enterNumber(String value) {
        type(numberInput, value);
    }

   
    public void enterText(String value) {
        type(textInput, value);
    }

    
    public void enterPassword(String value) {
        type(passwordInput, value);
    }

    
    public void enterDate(String value) {
        type(dateInput, value);
    }

   
    public void fillAllInputs(String number,
                              String text,
                              String password,
                              String date) {

        enterNumber(number);
        enterText(text);
        enterPassword(password);
        enterDate(date);
    }

    
    public String getTextValue() {
        return getAttribute(textInput, "value");
    }

  
    public void selectDropdown(String value) {
        WebElement element = waitForElement(dropdown);
        Select select = new Select(element);
        select.selectByVisibleText(value);
    }

  
    public String getSelectedOption() {
        WebElement element = waitForElement(dropdown);
        Select select = new Select(element);
        return select.getFirstSelectedOption().getText();
    }

   
    public void toggleCheckbox(String name) {

        By label = By.xpath(
                "//label[normalize-space()='" + name + "']");

        WebElement lbl = waitForElement(label);

        String id = lbl.getAttribute("for");

        WebElement checkbox =
                driver.findElement(By.id(id));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                checkbox);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                checkbox);
    }

   
    public boolean isCheckboxSelected(String name) {

        By label = By.xpath(
                "//label[normalize-space()='" + name + "']");

        WebElement lbl = waitForElement(label);

        String id = lbl.getAttribute("for");

        By checkbox = By.id(id);

        return driver.findElement(checkbox).isSelected();
    }
}