package baseFunc;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;

public class BaseFunc {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private JavascriptExecutor jse;

    public BaseFunc() {
        String chromeWebDriver = "webdriver.chrome.driver";
        String propertyMac = "user.home";
        String chromeDriverMac = "chromedriver";
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            System.setProperty(chromeWebDriver, "C://chromedriver.exe");
        } else if (os.contains("mac")) {
            System.setProperty(chromeWebDriver, new File(System.getProperty(propertyMac), chromeDriverMac).getAbsolutePath());
        } else Assertions.assertTrue(!(os.equals("mac")) && !(os.equals("win")), "Incorrect webDriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
        actions = new Actions(driver);
        jse = ((JavascriptExecutor) driver);
    }

    public void openPage(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }
        driver.get(url);
    }

    public WebElement getElement(By locator) {
        Assertions.assertFalse(getAllElements(locator).isEmpty(), "Element is not present");
        return driver.findElement(locator);
    }

    public List<WebElement> getAllElements(By locator) {
        Assertions.assertFalse(driver.findElements(locator).isEmpty(), "elements are not present");
        return driver.findElements(locator);
    }

    public void waitForElement(By locator) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public boolean isElementPresent(By locator) {
        waitForElement(locator);
        return getAllElements(locator).isEmpty();
    }

    public void quit() {
        driver.close();
    }

    public void openCategories(By locator, String categoryName) {
        List<WebElement> categories = getAllElements(locator);
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getText().equals(categoryName)) {
                categories.get(i).click();
                break;
            }
        }
    }

    public void switchToFrame(String id) throws InterruptedException {
        Thread.sleep(3000);
        driver.switchTo().frame(id);
    }

    public void waitUntilInvisible(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void clickToCertainElementPoint(By locator, int x, int y) {
        WebElement element = getElement(locator);
        actions.moveToElement(element, x, y).click().perform();
    }

    public void pressEnterBtn() {
        actions.sendKeys(Keys.ENTER);
    }

    public String getCssValue(By locator) {
        WebElement element = driver.findElement(locator);
        return jse.executeScript
                        ("return window.getComputedStyle(arguments[0], ':after').getPropertyValue('border-color');",
                                element).toString();
    }

    public void clear(By locator) {
        getElement(locator).clear();
    }
}
