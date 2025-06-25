package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends PageObject{

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".wt-cli-accept-all-btn")
    public WebElement cookieAcceptButtonTwo;

    @FindBy(css = "[alt='insider_logo']")
    public WebElement insiderLogo;

    @FindBy(css = "li:nth-child(6) a#navbarDropdownMenuLink")
    public WebElement navBarCompany;

}
