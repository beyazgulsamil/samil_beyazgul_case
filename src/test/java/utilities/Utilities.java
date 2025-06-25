package utilities;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import java.util.logging.Logger;


public class Utilities {
    private static final Logger log = Logger.getLogger(Utilities.class.getName());

    protected WebDriver driver;

    public void initialize() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        log.info("Initialized: Chrome Driver");
    }

    public void kill() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void takeScreenshot(WebDriver driver, String name) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = name + "_" + timestamp + ".png";
        String directory = "screenshots";

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(directory + "/" + fileName);

            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }

            Files.copy(src.toPath(), dest.toPath());
            log.info("Screenshot alındı: " + dest.getPath());

        } catch (IOException e) {
            log.severe("Screenshot alınamadı: " + e.getMessage());
        }
    }

    public void go (String url) {
        driver.get(url);
        log.info("Go to: " + url);
    }

    public WebElement scroll(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

        js.executeScript(scrollElementIntoMiddle, element);
        log.info("Scrolling");
        return element;
    }

    public WebElement waitUntilVisible(WebElement element, int timeoutSec, String name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSec));
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            log.info("Visible: " + name);
            return element;
        } catch (TimeoutException e) {
            throw new TimeoutException("Element NOT visible after " + timeoutSec + " s → " + name, e);
        }
    }

    public void click(WebElement element, String name) {
        scroll(waitUntilVisible(element, 10,name)).click();
        log.info("Click to: " + name);
    }

    public WebElement textToElement(String text) {
        return driver.findElement(By.xpath("//*[contains(text(),'" + text + "')]"));
    }

    public void loopAndClick(List<WebElement> list, String buttonName){
        for (WebElement item:list) {
            if (item.getText().contains(buttonName)){
                click(item,buttonName);
                return;
            }
        }
        Assert.fail("No such element was found...");
    }

    public WebElement mouseHover(WebElement element, String name) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();

            log.info("Mouse hover on: "+ name);
            return element;
        } catch (Exception e) {
            log.warning("Mouse hover failed for element: " + name);
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

    public void switchToTheNextTab() {
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
