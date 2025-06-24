package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LandingPage {
    WebDriver driver;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".wt-cli-accept-all-btn")
    public WebElement cookieAcceptButtonTwo;

    @FindBy(css = "[alt='insider_logo']")
    public WebElement insiderLogo;

    @FindBy(xpath = "//a[contains(text(),'Company')]")
    public WebElement navBarCompany;

}
