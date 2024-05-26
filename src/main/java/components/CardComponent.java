package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class CardComponent extends AbsBaseComponent {
    private static final String CARD_TEMPLATE_NAME = "//h1[text()='%s']";

    public CardComponent(WebDriver driver) {
        super(driver);
    }

    public CardComponent assertData(String locatorText) {
        assertThat($(By.xpath(locatorText)).getText())
                .as("Error! Data card is empty")
                .isNotEmpty();
        return this;
    }

    public CardComponent checkName(String locatorText) {
        String locator = String.format(CARD_TEMPLATE_NAME, locatorText);
        assertThat($(By.xpath(locator)).getText())
                .as("Error Name card %s", locatorText)
                .isEqualTo(locatorText);
        return this;
    }

    public CardComponent checkDescription(String nameCard) {
        return assertData(String.format(CARD_TEMPLATE_NAME, nameCard) + "/following-sibling::div");
    }

    public CardComponent checkDurationOfTraining(String nameCard) {
        return assertData(String.format(CARD_TEMPLATE_NAME, nameCard) + "/../../following-sibling::div/div/div[3]");
    }

    public CardComponent checkFormat(String nameCard) {
        return assertData(String.format(CARD_TEMPLATE_NAME, nameCard) + "/../../following-sibling::div/div/div[4]");
    }
}
