package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class CourcePage extends AbsBasePage {

    public CourcePage(WebDriver driver) {
        super(driver);
    }

    public CourcePage pageHeaderShouldSameAs(String expectedHeader) {
        String catalog = "//h1/div[text()='%s']";
        String locator = String.format(catalog, expectedHeader);
        waiters.waitForElementVisible(By.xpath(locator));
        assertThat($(By.xpath(locator)).getText())
                .as("Header of page should be same as {}", expectedHeader)
                .isEqualTo(expectedHeader);

        return this;
    }
}
