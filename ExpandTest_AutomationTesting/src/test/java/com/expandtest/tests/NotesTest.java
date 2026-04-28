package com.expandtest.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.expandtest.base.BaseTest;
import com.expandtest.pages.HomePage;
import com.expandtest.pages.NotesPage;

public class NotesTest extends BaseTest {

    private HomePage hp;
    private NotesPage notes;

    @BeforeMethod
    public void setUpNotes() {

        hp = new HomePage(getDriver());
        hp.goToNotes();

        notes = new NotesPage(getDriver());

        notes.login(
                "lakshmimanasakatakamsetty@gmail.com",
                "Password123");

        notes.waitForNotesPage();
    }

    @Test
    public void verifyCreateNote() {

        String title =
                "Note_" + System.currentTimeMillis();

        notes.createNote(
                title,
                "Automation Description",
                "Work");

        Assert.assertTrue(
                notes.isNotePresent(title));
    }

    @Test
    public void verifyEditNote() {

        String title =
                "EditNote_" + System.currentTimeMillis();

        notes.createNote(
                title,
                "Initial Desc",
                "Work");

        String updatedTitle =
                title + "_Updated";

        notes.editNote(
                title,
                updatedTitle,
                "Updated Description");

        Assert.assertTrue(
                notes.isNotePresent(updatedTitle));
    }

    @Test
    public void verifyDeleteNote() {

        String title =
                "DeleteNote_" + System.currentTimeMillis();

        notes.createNote(
                title,
                "To be deleted",
                "Work");

        notes.deleteNote(title);

        Assert.assertFalse(
                notes.isNotePresent(title));
    }

    @Test
    public void verifyFilterNotes() {

        String workTitle =
                "WorkNote_" + System.currentTimeMillis();

        String personalTitle =
                "PersonalNote_" + System.currentTimeMillis();

        notes.filterByCategory("All");

        notes.createNote(
                workTitle,
                "Desc",
                "Work");

        notes.waitForNote(workTitle);

        notes.createNote(
                personalTitle,
                "Desc",
                "Personal");

        notes.waitForNote(personalTitle);

        notes.filterByCategory("Work");

        notes.waitForNoteToDisappear(personalTitle);

        Assert.assertFalse(
                notes.isNotePresent(personalTitle));
    }
}
