package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CareersPage {
    WebDriver driver;

    public CareersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static By teamsArea = By.xpath("//*[@id=\"career-find-our-calling\"]/div/div/a");
    public static By locationsArea = By.cssSelector("#career-our-location");
    public static By lifeAtInsiderArea = By.cssSelector("[data-id=\"a8e7b90\"]");
}
