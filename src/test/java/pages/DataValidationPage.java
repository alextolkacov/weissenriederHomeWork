package pages;

import baseFunc.BaseFunc;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DataValidationPage {
    private BaseFunc baseFunc;
    private final By LOGO = By.xpath(".//a[@class = 'logo']");
    private final By CODE = By.xpath(".//div[@class = 'code']");
    private final By TABS = By.xpath(".//div[@class = 'approaches']/h3");
    private final By IFRAME_BUTTONS = By.xpath(".//span[@class = 'dx-button-text']");
    private final By ADD_NEW_ROW_BTN = By.xpath(".//i[@class = 'dx-icon dx-icon-edit-button-addrow']");
    private final By LOADER = By.xpath(".//dic[@class = 'dx-loadindicator-icon']");
    private final By EMAIL_FIELD = By
            .xpath(".//tr[@class = 'dx-row dx-data-row dx-column-lines dx-row-inserted']/td[5]");
    private final By NEW_EMAIL_INPUT_FIELD = By.xpath(".//input[@class = 'dx-texteditor-input']");
    private final By ERROR_NOTIFICATION = By.xpath(".//div[@class = 'dx-overlay-content dx-resizable']");
    private final By BORDER = By.xpath(".//div[@class = 'dx-highlight-outline dx-pointer-events-target']");
    private final String IFRAME_ID = "demoFrame";

    public DataValidationPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
        Assertions.assertFalse(baseFunc.isElementPresent(LOGO), "Logo is not present");
        Assertions.assertFalse(baseFunc.isElementPresent(CODE), "Code field is not present");
    }

    public void openTab(String tabName) {
        List<WebElement> tabs = baseFunc.getAllElements(TABS);
        for (WebElement we : tabs) {
            if (we.getText().equals(tabName)) {
                we.click();
            }
        }
        baseFunc.waitUntilInvisible(LOADER);
    }

    public void switchToFrame() throws InterruptedException {
        baseFunc.switchToFrame(IFRAME_ID);
    }

    public void pushAddARowBtn() {
        baseFunc.waitForElement(IFRAME_BUTTONS);
        baseFunc.clickToCertainElementPoint(ADD_NEW_ROW_BTN, 10, 10);
    }

    public void waitUntilLoaded() {
        baseFunc.waitUntilInvisible(LOADER);
    }

    public void addNewEmail(String email) {
        baseFunc.getElement(EMAIL_FIELD).click();
        baseFunc.getElement(NEW_EMAIL_INPUT_FIELD).sendKeys(email);
    }

    public void checkIncorrectEmails(List<String> listOfEmails) {
        for (String email : listOfEmails) {
            baseFunc.clear(NEW_EMAIL_INPUT_FIELD);
            addNewEmail(email);
            baseFunc.pressEnterBtn();
            String borderColor = baseFunc.getCssValue(BORDER);
            Assertions.assertFalse(baseFunc.isElementPresent(ERROR_NOTIFICATION), "No error message appeared");
            Assertions.assertTrue(borderColor.contains("rgba(217, 83, 79, 0.4)"));
        }
    }

    public void checkCorrectEmails(List<String> listOfEmails) {
        for (String email : listOfEmails) {
            baseFunc.clear(NEW_EMAIL_INPUT_FIELD);
            addNewEmail(email);
            baseFunc.pressEnterBtn();
            Assertions.assertTrue(baseFunc.getElement(BORDER).getCssValue("border-color")
                    .contains("rgba(92, 184, 92, 0.5)"), "Green border not appeared");
        }
    }
}
