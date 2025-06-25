package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CareersPage extends PageObject {

    public CareersPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".career-load-more")
    public WebElement teamsArea;

    @FindBy(css = "#career-our-location")
    public WebElement locationsArea;

    @FindBy(css = "[data-id=\"a8e7b90\"]")
    public WebElement lifeAtInsiderArea;

}
