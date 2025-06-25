# QA Automation Project – Selenium Web Test

## Project Description

This is a UI test automation project focused on verifying career page navigation, job filtering, and application page redirection on a corporate website.

## Test Scenarios

1. Open the homepage and check that it loads correctly.
2. Navigate to the careers page through the top menu and verify that sections like Locations, Teams, and Life at the company are visible.
3. Go to the QA careers section and click on “See all QA jobs”.
4. Apply filters:
   - Location: Istanbul, Turkey
   - Department: Quality Assurance
5. Verify that each job listing includes:
   - A title containing “Quality Assurance”
   - Department: “Quality Assurance”
   - Location: “Istanbul, Turkey”
6. Click on a job's “View Role” button and verify that it redirects to the application page.

## Technologies Used

- Java 17
- Selenium WebDriver (v4.25.0)
- WebDriverManager (v5.9.2)
- TestNG (v7.7.0)
- JUnit (v4.13.1)
- Lombok (v1.18.34)
- Maven
- IntelliJ IDEA

## Requirements

Before running the project, make sure the following tools are installed:

- JDK 17 or higher
- Maven 3.8+
- Chrome browser
- Internet connection (for WebDriverManager)

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

## How to Run

```bash
mvn clean test
```

Tests are written using TestNG. WebDriverManager handles the ChromeDriver setup automatically.

## Author

Şamil Beyazgül  
[LinkedIn](https://www.linkedin.com/in/your-linkedin)
[Github](https://github.com/beyazgulsamil)
