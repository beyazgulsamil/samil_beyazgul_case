# Insider QA Automation Project – Selenium Web Test

## Project Description

This is an automated UI testing project developed for the QA Engineer Selenium Task by Insider. It verifies several key flows on the [Insider website](https://useinsider.com), especially focusing on the Careers and QA Job Listings pages.

## Test Scenarios

1. Open the homepage and verify it loads successfully.
2. Navigate through “Company → Careers” and verify the presence of:
    - Locations section
    - Teams section
    - Life at Insider section
3. Go to the Quality Assurance career page and click “See all QA jobs”.
4. Apply filters:
    - Location: Istanbul, Turkey
    - Department: Quality Assurance
5. Verify that each job listing contains:
    - Title including “Quality Assurance”
    - Department: “Quality Assurance”
    - Location: “Istanbul, Turkey”
6. Click on the “View Role” button and confirm redirection to the Lever application form.

## Technologies Used

- Java 17
- Selenium WebDriver (v4.25.0)
- WebDriverManager (v5.9.2)
- TestNG (v7.7.0)
- JUnit (v4.13.1)
- Lombok (v1.18.34)
- Maven
- IntelliJ IDEA

## Project Structure

```
samil_beyazgul_case/
├── pom.xml
├── README.md
└── src/
    └── test/
        └── java/
            ├── pages/
            │   ├── CareersPage.java
            │   ├── LandingPage.java
            │   └── OpenPositionPage.java
            ├── tests/
            │   └── Case/
            │       └── Case.java
            └── utilities/
                └── Utilities.java
```

## How to Run the Tests

### Prerequisites

- Java 17 or higher
- Maven installed
- Chrome browser

### Running the Tests

```bash
mvn clean test
```

TestNG annotations are used, and WebDriverManager automatically handles the ChromeDriver setup.

## Notes

- Page Object Model (POM) is used to maintain clean and scalable test code.
- XPath and CSS selectors are optimized for maintainability.
- Assertions are implemented with TestNG.
- No BDD frameworks (such as Cucumber) are used, as per task instructions.

## Author

Şamil Beyazgül  
[LinkedIn](https://www.linkedin.com/in/samilbyzgl/)
[Github](https://github.com/beyazgulsamil)
