package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class LandingPage {
    WebDriver driver;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static By cookieAcceptButton = By.cssSelector(".wt-cli-accept-all-btn");
    public static By insiderLogo = By.cssSelector("[alt='insider_logo']");
    public static By navBarCompany = By.xpath("//a[contains(text(),'Company')]");


}
