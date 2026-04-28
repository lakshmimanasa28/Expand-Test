package com.expandtest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.expandtest.base.BaseTest;
import com.expandtest.pages.HomePage;
import com.expandtest.pages.LoginPage;
import com.expandtest.pages.NotesPage;
import com.expandtest.pages.RegisterPage;

public class FormValidationsTest extends BaseTest {

    @Test
    public void verifyEmptyTitleValidation() {

        HomePage hp = new HomePage(getDriver());
        hp.goToNotes();

        NotesPage notes = new NotesPage(getDriver());

        notes.login(
                "lakshmimanasakatakamsetty@gmail.com",
                "Password123");

        notes.waitForNotesPage();

        notes.createNote(
                "",
                "Hello Everyone!",
                "Personal");

        Assert.assertTrue(
                notes.getTitleError().contains("required"));
    }

    @Test
	public void verifyPasswordValidation() {
	    HomePage hp=new HomePage(getDriver());
	    hp.goToRegister();

	    RegisterPage register=new RegisterPage(getDriver());
	    register.enterUsername("testuser");
	    register.enterPassword("123");
	    register.enterConfirmPassword("123");
	    register.clickRegister();

	    Assert.assertTrue(register.getPasswordError().contains("Password must be"),"Password validation not working");
	}
    @Test
    public void verifyInvalidEmailValidation() {

        HomePage hp = new HomePage(getDriver());
        hp.goToLoginPage();

        LoginPage login =
                new LoginPage(getDriver());

        login.login(
                "invalid",
                "Password123");

        Assert.assertTrue(
                login.getErrorMessage()
                .contains("username is invalid"));
    }
}