# Expand-Test

# Web Automation Testing Framework

## Project Overview
This project is a Selenium WebDriver + TestNG based automation framework developed using Java.  
It automates functional testing of the Practice Expand Testing website.

Framework follows:
- Page Object Model (POM)
- Data Driven Testing using Excel
- Retry Mechanism
- Extent Reports
- Screenshot Capture on Failure
- Configurable Environment using properties file

---

## Tech Stack

- Java
- Selenium WebDriver
- TestNG
- Maven
- Apache POI
- Extent Reports

---

## Project Structure

src/test/java

com.expandtest.base  
com.expandtest.pages  
com.expandtest.tests  
com.expandtest.utils  
com.expandtest.listeners  

src/test/resources

config.properties  
LoginData.xlsx

---

## Modules Automated

### Login Module
- Valid Login
- Invalid Login
- Empty Login
- Logout Functionality

### Notes Module
- Create Note
- Edit Note
- Delete Note
- Filter Notes

### Form Interaction Module
- Text Inputs
- Dropdown
- Checkboxes
- Radio Buttons

### Alerts Module
- JS Alert
- JS Confirm
- JS Prompt

### Validation Module
- Empty Title Validation
- Password Length Validation
- Invalid Email Validation

---

## Total Test Cases

18 Functional Test Cases

---

## Features

- Reusable Page Object Model
- TestNG Assertions
- Retry Failed Tests Automatically
- Extent HTML Report
- Screenshot on Failure
- Excel Driven Test Data
- Config Reader Support

---

## How To Run

1. Import project into Eclipse
2. Update Maven dependencies
3. Run testng.xml file

---

## Reports

After execution:

report/ExtentReport.html

---

## Author

Lakshmi Manasa
