package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.NoSuchElementException;

import static pickleib.web.driver.PickleibWebDriver.log;
import static utils.StringUtilities.Color.*;
import static utils.StringUtilities.highlighted;

public class OpenPositionPage {
    WebDriver driver;


    public OpenPositionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public static By filterByLocation = By.cssSelector("#select2-filter-by-location-container");
    public static By listLocation = By.cssSelector(".select2-results__option");
    public static By qaTitle = By.cssSelector("[title=\"Quality Assurance\"]");
    public static By positionList = By.cssSelector(".position-list");
    public static By positionListItem = By.cssSelector(".position-list-item");
    public static By listItemTeam = By.cssSelector("[data-team=\"qualityassurance\"]");
    public static By viewRoleButton = By.xpath("(//div[contains(@class,'position-list-item')]//a[contains(@class,'btn-navy')])[1]");

    public void verifyJobFilters(String expectedLocation, String expectedTeam) {
        List<WebElement> jobCards = driver.findElements(positionListItem);

        if (jobCards.isEmpty()) {
            throw new NoSuchElementException("Position not found.");
        }

        for (WebElement card : jobCards) {
            String actualLocation = card.getAttribute("data-location");
            String actualTeam = card.getAttribute("data-team");

            if (!expectedLocation.equalsIgnoreCase(actualLocation)) {
                throw new AssertionError("Invalid data-location: " + actualLocation);
            }

            if (!expectedTeam.equalsIgnoreCase(actualTeam)) {
                throw new AssertionError("Invalid data-team: " + actualTeam);
            }
            String jobTitle = card.findElement(By.cssSelector(".position-title")).getText();
            log.info(highlighted(BLUE, " MATCH ") +
                    highlighted(YELLOW, jobTitle) + " | " +
                    highlighted(PURPLE, actualLocation + " / " + actualTeam));
        }
        log.info(highlighted(BLUE,"Successfully verify job filters:"));
    }
}
