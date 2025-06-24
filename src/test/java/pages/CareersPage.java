package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CareersPage {
    WebDriver driver;

    public CareersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"career-find-our-calling\"]/div/div/a")
    public WebElement teamsArea;

    @FindBy(css = "#career-our-location")
    public WebElement locationsArea;

    @FindBy(css = "[data-id=\"a8e7b90\"]")
    public WebElement lifeAtInsiderArea;

}
