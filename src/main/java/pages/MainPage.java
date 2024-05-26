package pages;

import data.cources.CourcesData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends AbsBasePage {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    public CourcePage clickCoursesMenu(CourcesData courcesData) {
        String categoriesMenu = "//div[./h2[text()='Авторские онлайн‑курсы для профессионалов']]/../following-sibling::section//*[text()='%s']";
        String locator = String.format(categoriesMenu, courcesData.getName());
        $(By.xpath(locator)).click();
        return new CourcePage(driver);
    }

}
