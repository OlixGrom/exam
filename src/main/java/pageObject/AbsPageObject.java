package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import waiters.Waiters;

import java.util.List;

public abstract class AbsPageObject {
    protected WebDriver driver;
    protected Actions actions;
    protected Waiters waiters;

    public AbsPageObject(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.waiters = new Waiters(driver);
    }

    public WebElement $(By locator) {
        return driver.findElement(locator);
    }

    public List<WebElement> $$(By locator) {
        return driver.findElements(locator);
    }
}
