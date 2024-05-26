import components.CalendarofEventsComponent;
import components.CardComponent;
import components.CoursesFilterComponent;
import data.cources.CourcesData;
import driver.OptionsBrowser;
import driver.WebDriverFactory;
import exceptions.DriverNotSupportedException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.EventsPage;
import pages.MainPage;

import java.net.MalformedURLException;

public class CourseTest {

    private final String browserType = System.getProperty("browser", "chrome").toUpperCase();
    private final String filterName = "Открытый вебинар";
    private String nameCourse;
    private WebDriver driver;

    @BeforeEach
    public void init() throws DriverNotSupportedException, MalformedURLException {
        System.out.println("! browserType " + browserType);
        this.driver = new WebDriverFactory().create(this.browserType, new OptionsBrowser(this.browserType).getOptions());
        this.driver.manage().window().maximize();
    }

    @AfterEach
    public void close() {
        if (this.driver != null) {
            this.driver.close();
            this.driver.quit();
        }
    }

    @Test
    void checkCountCource() {//Проверка количества курсов в разделе тестирование
        goToCards();
        new CoursesFilterComponent(driver)
                .checkCountCard();
    }

    @Test
    void checkCardView() {//Просмотр первой карточки курса
        goToCards();
        nameCourse = new CoursesFilterComponent(driver)
                .clickCard();

        new CardComponent(driver)
                .checkName(nameCourse)
                .checkDescription(nameCourse)
                .checkFormat(nameCourse)
                .checkDurationOfTraining(nameCourse);

    }

    @Test
    void validationDateEvent() {//Валидация дат предстоящих мероприятий
        goToEvents();
        new CalendarofEventsComponent(driver).checkData();

    }

    @Test
    void viewEventFilter() {//Просмотр мероприятий по типу
        goToEvents();
        new CalendarofEventsComponent(driver)
                .checkFilter(filterName);

    }

    private void goToCards() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open("/");
        mainPage
                .clickCoursesMenu(CourcesData.Testing)
                .pageHeaderShouldSameAs("Каталог");
    }

    private void goToEvents() {
        new MainPage(driver).open("/");

        new EventsPage(driver).openEvents();
    }
}
