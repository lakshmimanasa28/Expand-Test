package com.expandtest.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.expandtest.base.BasePage;

public class NotesPage extends BasePage {

 
	private By addNoteBtn = By.xpath("//button[contains(.,'Add Note') or contains(.,'+ Add Note')]");
    private By titleInput = By.id("title");
    private By descInput = By.id("description");
    private By categoryDropdown = By.id("category");

    private By createBtn =
            By.xpath("//button[normalize-space()='Create']");
    private By updateBtn =
            By.xpath("//button[contains(text(),'Update')]");
    private By saveBtn =
            By.xpath("//button[normalize-space()='Save']");

    private By filterDropdown = By.id("category");

    private By emailInput = By.id("email");
    private By passwordInput = By.id("password");
    private By loginBtn =
            By.xpath("//button[contains(text(),'Login')]");
    private By clickLogin =
            By.xpath("//a[normalize-space()='Login']");

    
    private By titleError =
            By.xpath("//*[contains(text(),'required')]");

    public NotesPage(WebDriver driver) {
        super(driver);
    }

   
    public void clickLoginBtn() {
        click(clickLogin);
    }

    
    public NotesPage login(String email, String password) {

        type(emailInput, email);
        type(passwordInput, password);
        click(loginBtn);

        return new NotesPage(driver);
    }

    
    public void createNote(String title,
                           String desc,
                           String category) {

        click(addNoteBtn);

        type(titleInput, title);
        type(descInput, desc);

        driver.findElement(categoryDropdown)
                .sendKeys(category);

        click(createBtn);
    }

    
    public void editNote(String oldTitle,
                         String newTitle,
                         String newDesc) {

        By editBtn = By.xpath(
                "//div[@data-testid='note-card-title' " +
                "and contains(text(),'" + oldTitle + "')]" +
                "/ancestor::div[contains(@class,'card')]" +
                "//button[@data-testid='note-edit']");

        click(editBtn);

        WebElement titleEl = waitForElement(titleInput);
        titleEl.clear();
        titleEl.sendKeys(newTitle);

        WebElement descEl = waitForElement(descInput);
        descEl.clear();
        descEl.sendKeys(newDesc);

        click(saveBtn);
    }

   
    public void deleteNote(String title) {

        By deleteBtn = By.xpath(
                "//div[@data-testid='note-card-title' " +
                "and normalize-space()='" + title + "']" +
                "/ancestor::div[contains(@class,'card')]" +
                "//button[@data-testid='note-delete']");

        click(deleteBtn);

        By confirmDelete =
                By.xpath("//button[@data-testid='note-delete-confirm']");

        click(confirmDelete);

        waitForNoteToDisappear(title);
    }

    
    public boolean isNotePresent(String title) {

        try {
            By note = By.xpath(
                    "//div[@data-testid='note-card-title' " +
                    "and contains(text(),'" + title + "')]");

            return waitForElement(note).isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    public void waitForNotesPage() {
        wait.until(ExpectedConditions.urlContains("/notes/app"));
        waitForElement(addNoteBtn);
    }
  
    public void filterByCategory(String category) {

        String testId =
                "category-" + category.toLowerCase();

        By filterBtn = By.xpath(
                "//button[@data-testid='" + testId + "']");

        click(filterBtn);
    }

    
    public boolean areAllNotesOfCategory(String category) {

        String expected =
                "category-" + category.toLowerCase();

        List<WebElement> cards =
                driver.findElements(
                        By.xpath("//div[contains(@class,'card')]"));

        for (WebElement card : cards) {

            List<WebElement> buttons =
                    card.findElements(
                            By.xpath(".//button[contains(@data-testid,'category-')]"));

            if (buttons.isEmpty()) {
                return false;
            }

            String actual =
                    buttons.get(0).getAttribute("data-testid");

            if (!actual.equalsIgnoreCase(expected)) {
                return false;
            }
        }

        return true;
    }

    
    public void waitForNote(String title) {

        By note = By.xpath(
                "//div[@data-testid='note-card-title' " +
                "and contains(text(),'" + title + "')]");

        waitForElement(note);
    }

    
    public List<String> getVisibleNoteTitles() {

        List<WebElement> elements =
                driver.findElements(
                        By.xpath("//div[@data-testid='note-card-title']"));

        List<String> titles = new ArrayList<String>();

        for (WebElement el : elements) {
            titles.add(el.getText().trim());
        }

        return titles;
    }

   
    public void waitForNoteToDisappear(String title) {

        By note = By.xpath(
                "//div[@data-testid='note-card-title' " +
                "and normalize-space()='" + title + "']");

        wait.until(
                ExpectedConditions
                        .invisibilityOfElementLocated(note));
    }
    
    public String getTitleError() {
        return getText(titleError);
    }
   
}