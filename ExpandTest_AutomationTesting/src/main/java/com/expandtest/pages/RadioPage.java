package com.expandtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.expandtest.base.BasePage;

public class RadioPage extends BasePage {

    private By closeAdBtn =
            By.xpath("//div[contains(@class,'modal')]//button[contains(@class,'close')]");

    public RadioPage(WebDriver driver) {
        super(driver);
    }

    public void closeAdIfPresent() {
        try {
            if (driver.findElements(closeAdBtn).size() > 0) {
                click(closeAdBtn);
            }
        } catch (Exception e) {
        }
    }

    public void selectColor(String color) {

        By label =
                By.xpath("//label[normalize-space()='" + color + "']");

        WebElement lbl = waitForElement(label);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                lbl);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                lbl);
    }

    public boolean isColorSelected(String color) {

        By label =
                By.xpath("//label[normalize-space()='" + color + "']");

        WebElement lbl = waitForElement(label);

        String id = lbl.getAttribute("for");

        return driver.findElement(By.id(id)).isSelected();
    }
}