package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CoursesFilterComponent extends AbsBaseComponent {
    private final String listCardLocator = "//h1/div[text()='Каталог']/../../following-sibling::div/div/a";

    public CoursesFilterComponent(WebDriver driver) {
        super(driver);
    }

    public CoursesFilterComponent checkCountCard() {
        List<WebElement> webElementList = $$(By.xpath(listCardLocator));
        assertThat(webElementList.size())
                .as("Error")
                .isEqualTo(10);
        return this;
    }

    public String clickCard() {
        String name = $(By.xpath(listCardLocator+"/h6/div")).getText();
        $(By.xpath(listCardLocator)).click();
        return name;
    }
}
