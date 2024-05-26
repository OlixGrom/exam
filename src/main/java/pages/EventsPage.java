package pages;

import org.openqa.selenium.WebDriver;

public class EventsPage extends AbsBasePage {
    private final static String EVENTS_URL = System.getProperty("events.url", "/events/near/");

    public EventsPage(WebDriver driver) {
        super(driver);
    }

    public void openEvents() {
        super.open(EVENTS_URL);
    }
}
