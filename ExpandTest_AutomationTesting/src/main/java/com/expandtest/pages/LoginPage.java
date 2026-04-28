package com.expandtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.expandtest.base.BasePage;

public class LoginPage extends BasePage {

   
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginBtn = By.xpath("//button[contains(text(),'Login')]");
    private By logoutBtn = By.cssSelector("a[href='/logout']");
    private By errorMsg = By.xpath("//div[contains(@class,'alert') or contains(@class,'error')]");

    
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public HomePage login(String username, String password) {
        type(usernameField, username);
        type(passwordField, password);
        click(loginBtn);
        return new HomePage(driver);
    }

    
    public void clickLogin() {
        click(loginBtn);
    }

    public void logout() {
        click(logoutBtn);
    }

   
    public boolean isLoggedIn() {
        return waitForElement(logoutBtn).isDisplayed();
    }

    public String getErrorMessage() {
        return getText(errorMsg);
    }

    public boolean isOnLoginPage() {
        return driver.getCurrentUrl().contains("login");
    }

    public boolean isLoginButtonDisplayed() {
        return waitForElement(loginBtn).isDisplayed();
    }
}