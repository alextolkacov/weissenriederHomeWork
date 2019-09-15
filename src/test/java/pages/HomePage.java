package pages;

import baseFunc.BaseFunc;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class HomePage {
    private BaseFunc baseFunc;
    private final By LOGO = By.xpath(".//a[@class = 'logo']");
    private final By MARKETING_DEMOS = By.xpath(".//div[@class = 'marketing-demos']");
    private final By FOOTER = By.xpath(".//footer[@class = 'footer']");
    private final By CATEGORIES = By.xpath(".//span[@class = 'link-text']");

    public HomePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
        Assertions.assertFalse(baseFunc.isElementPresent(LOGO), "Logo is not present");
        Assertions.assertFalse(baseFunc.isElementPresent(MARKETING_DEMOS), "Marketing demos are not present");
        Assertions.assertFalse(baseFunc.isElementPresent(FOOTER), "Footer is not present");
    }

    public void openCategory(String categoryName) {
        baseFunc.openCategories(CATEGORIES, categoryName);
    }

    public void openLink(String linkName) {
        baseFunc.openCategories(CATEGORIES, linkName);
    }
}
