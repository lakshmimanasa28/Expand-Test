package com.expandtest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.expandtest.base.BaseTest;
import com.expandtest.pages.HomePage;
import com.expandtest.pages.InputsPage;
import com.expandtest.pages.RadioPage;

public class FormInteractionsTest extends BaseTest {

    @Test
    public void verifyTextInputs() {
        HomePage home = new HomePage(getDriver());
        home.goToInputsPage();

        InputsPage inputs = new InputsPage(getDriver());

        inputs.enterNumber("123");
        inputs.enterText("Manasa");
        inputs.enterPassword("Password123");
        inputs.enterDate("19-04-2026");

        Assert.assertEquals(inputs.getTextValue(), "Manasa", "Text input not working");
    }

    @Test
    public void verifyDropdown() {
        HomePage home = new HomePage(getDriver());
        home.goToDropdown();

        InputsPage page = new InputsPage(getDriver());
        page.selectDropdown("Option 1");

        Assert.assertEquals(page.getSelectedOption(), "Option 1", "Dropdown selection failed");
    }
    @Test
    public void verifyCheckboxes() {
        HomePage hp = new HomePage(getDriver());
        hp.goToCheckboxes();

        InputsPage page = new InputsPage(getDriver());

        page.toggleCheckbox("Checkbox 1");
        Assert.assertTrue(page.isCheckboxSelected("Checkbox 1"));
    }

    @Test
    public void verifyRadioButtons() {
        HomePage home = new HomePage(getDriver());
        home.goToRadio();

        RadioPage rp = new RadioPage(getDriver());
        rp.closeAdIfPresent();

        rp.selectColor("Yellow");
        Assert.assertTrue(rp.isColorSelected("Yellow"));

        rp.selectColor("Red");
        Assert.assertTrue(rp.isColorSelected("Red"));
        Assert.assertFalse(rp.isColorSelected("Yellow"));
    }
}