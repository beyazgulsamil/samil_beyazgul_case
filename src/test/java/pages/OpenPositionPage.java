package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.NoSuchElementException;

public class OpenPositionPage extends PageObject {

    public OpenPositionPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#select2-filter-by-location-container")
    public WebElement filterByLocation;

    @FindBy(css = ".select2-results__option")
    public List<WebElement> listLocation;

    @FindBy(css = ".select2-results__options")
    public WebElement listLocations;

    @FindBy(css = "[title=\"Quality Assurance\"]")
    public WebElement qaTitle;

    @FindBy(css = ".position-list")
    public WebElement positionList;

    @FindBy(css = "[data-team=\"qualityassurance\"]")
    public List<WebElement> positionListItem;

    @FindBy(xpath = "(//div[contains(@class,'position-list-item')]//a[contains(@class,'btn-navy')])[1]")
    public WebElement viewRoleButton;

    public void verifyJobFilters(String expectedLocation, String expectedTeam) {

        if (positionListItem.isEmpty()) {
            throw new NoSuchElementException("Position not found.");
        }

        for (WebElement card : positionListItem) {
            String actualLocation = card.getAttribute("data-location");
            String actualTeam = card.getAttribute("data-team");

            if (!expectedLocation.equalsIgnoreCase(actualLocation)) {
                throw new AssertionError("Invalid data-location: " + actualLocation);
            }

            if (!expectedTeam.equalsIgnoreCase(actualTeam)) {
                throw new AssertionError("Invalid data-team: " + actualTeam);
            }
            String jobTitle = card.findElement(By.cssSelector(".position-title")).getText();
            log.info(" MATCH " + jobTitle + " | " +actualLocation + " / " + actualTeam);
        }
        log.info("Successfully verify job filters:");
    }
}
