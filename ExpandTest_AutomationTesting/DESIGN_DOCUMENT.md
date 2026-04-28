# Test Automation Framework Design Document

## 1. Objective

To automate regression and functional testing of Practice Expand Testing web application using Selenium WebDriver with scalable hybrid framework.

---

## 2. Framework Type

Hybrid Framework combining:

- Page Object Model
- Data Driven Framework
- Utility Based Framework

---

## 3. Architecture Layers

### Test Layer

Contains test classes:

- LoginTest
- NotesTest
- AlertsTest
- FormInteractionsTest
- FormValidationsTest

### Page Layer

Contains web element locators and actions:

- HomePage
- LoginPage
- NotesPage
- AlertsPage
- InputsPage
- RegisterPage
- RadioPage

### Base Layer

Contains common setup/teardown:

- Browser launch
- Driver initialization
- Wait methods

### Utility Layer

Contains helper classes:

- ConfigReader
- ExcelUtil
- ScreenshotUtil
- ExtentManager
- RetryAnalyzer

### Listener Layer

- TestListener
- RetryListener

---

## 4. Design Pattern Used

Page Object Model (POM)

Benefits:

- Reusability
- Easy Maintenance
- Clean Code
- Separation of Test Logic & UI Logic

---

## 5. Reporting Mechanism

Extent Report used.

Captures:

- Pass / Fail Status
- Logs
- Screenshots
- Summary

---

## 6. Retry Mechanism

Failed test automatically reruns once using RetryAnalyzer.

---

## 7. Test Data Management

Excel file:

LoginData.xlsx

Used for login scenarios.

---

## 8. Configuration Management

config.properties contains:

- browser
- baseUrl
- email
- password

---

## 9. Total Coverage

18 Automated Functional Test Cases

---

## 10. Future Enhancements

- Cross Browser Testing
- Jenkins CI/CD Integration
- Docker Execution
- Parallel Execution
- Allure Reports

---

## 11. Conclusion

Framework successfully automates critical modules with reusable architecture and reporting support.