package com.expandtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.expandtest.base.BasePage;

public class RegisterPage extends BasePage {

    
    private By username = By.id("username");
    private By password = By.id("password");
    private By confirmPassword = By.id("confirmPassword");
    private By registerBtn =
            By.xpath("//button[contains(text(),'Register')]");
    private By passwordError =
            By.xpath("//*[contains(text(),'Password')]");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

  
    public void enterUsername(String value) {
        type(username, value);
    }

    public void enterPassword(String value) {
        type(password, value);
    }

    
    public void enterConfirmPassword(String value) {
        type(confirmPassword, value);
    }

   
    public void clickRegister() {
        click(registerBtn);
    }

   
    public void registerUser(String user,
                             String pass,
                             String confirmPass) {

        enterUsername(user);
        enterPassword(pass);
        enterConfirmPassword(confirmPass);
        clickRegister();
    }

   
    public String getPasswordError() {
        return getText(passwordError);
    }
}