package com.expandtest.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.expandtest.base.BaseTest;
import com.expandtest.pages.HomePage;
import com.expandtest.pages.LoginPage;
import com.expandtest.utils.ExcelUtil;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
    	String path = System.getProperty("user.dir")
    			+ "/src/test/resources/LoginData.xlsx";

        return ExcelUtil.getTestData(path, "Sheet1");
    }

    @Test(dataProvider = "loginData")
    public void verifyLogin(String username,
                            String password,
                            String expected) {

        HomePage hp = new HomePage(getDriver());
        hp.goToLoginPage();

        LoginPage login = new LoginPage(getDriver());
        login.login(username, password);

        if (expected.equalsIgnoreCase("success")) {

            Assert.assertTrue(login.isLoggedIn(),
                    "Expected login success but Logout button not visible");

        } else if (expected.equalsIgnoreCase("failure")) {

            String error = login.getErrorMessage();

            Assert.assertTrue(error != null && !error.isEmpty(),
                    "Expected failure but no error shown");

        } else if (expected.equalsIgnoreCase("empty")) {

            String error = login.getErrorMessage();

            Assert.assertTrue(error != null && !error.isEmpty(),
                    "Expected validation for empty fields but not shown");
        }
    }

    
    @Test
    public void verifyLogout() {

        HomePage hp = new HomePage(getDriver());
        hp.goToLoginPage();

        Assert.assertTrue(getDriver().getCurrentUrl().contains("/login"),
                "Did not navigate to login page");

        LoginPage login = new LoginPage(getDriver());

        login.login("practice", "SuperSecretPassword!");
        login.logout();

        Assert.assertTrue(getDriver().getCurrentUrl().contains("/login"),
                "Logout did not redirect to login page");
    }
}