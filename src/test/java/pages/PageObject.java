package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Logger;

public class PageObject {

    public Logger log = Logger.getLogger(this.getClass().getName());
    public WebDriver driver;

    public PageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
