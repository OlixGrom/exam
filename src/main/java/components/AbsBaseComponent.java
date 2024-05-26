package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageObject.AbsPageObject;

public abstract class AbsBaseComponent extends AbsPageObject {
    protected WebDriver driver = null;

    public AbsBaseComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


}

