package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import static pickleib.web.driver.PickleibWebDriver.log;
import static utils.StringUtilities.Color.*;
import static utils.StringUtilities.Color.BLUE;
import static utils.StringUtilities.highlighted;

public class Utilities {
    protected WebDriver driver;


    public void initialize() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        log.info(highlighted(GREEN_UNDERLINED,"Initialized:") + highlighted(PURPLE," Driver"));

    }

    public void kill() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void go (String url) {
        driver.get(url);
        log.info(highlighted(BLUE,"Go to: ") + highlighted(YELLOW,url));
    }

    public By scroll(By by) {
        WebElement element = driver.findElement(by);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

        js.executeScript(scrollElementIntoMiddle, element);
        log.info(highlighted(BLUE,"Scrolling"));
        return by;
    }

    public By waitUntilVisible(By by, int timeout, String name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        log.info(highlighted(BLUE,"Wait to visible for ") + highlighted(YELLOW,name));
        return by;
    }

    public void click(By by, String name) {
        driver.findElement(scroll(waitUntilVisible(by, 10,name))).click();
        log.info(highlighted(BLUE,"Click to: ") + highlighted(YELLOW,name));
    }

    public void clickText(String text) {
        By by = By.xpath("//*[contains(text(),'" + text + "')]");
        click(by,text);
    }

    public void loopAndClick(By listLocator, String textToClick) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(listLocator));

        for (WebElement element : elements) {
            String visibleText = element.getText().trim();
            if (visibleText.equalsIgnoreCase(textToClick)) {
                scroll(By.xpath("//*[text()='" + visibleText + "']"));
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                return;
            }
        }

        throw new NoSuchElementException("Text '" + textToClick + "' is not equals");
    }

    public By mouseHover(By by, String name) {
        try {
            WebElement element = driver.findElement(by);
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();

            log.info(highlighted(BLUE, "Mouse hover on: ") + highlighted(YELLOW, name));
            return by;
        } catch (Exception e) {
            log.error("Mouse hover failed for element: " + name, e);
            throw e;
        }
    }

    public void verifyUrlContains(String expectedPart) {
        String actualUrl = driver.getCurrentUrl();

        if (!actualUrl.contains(expectedPart)) {
            throw new AssertionError("URL '" + actualUrl + "' in '" + expectedPart + "' not found!");
        }

        log.info(" URL in '" + expectedPart + "' found.");
    }
    public void switchToNewTab() {
        String currentWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindow)) {
                driver.switchTo().window(windowHandle);
                log.info(" Tab changes: " + driver.getCurrentUrl());
                return;
            }
        }
        throw new RuntimeException("Tab change failed.");
    }



}
