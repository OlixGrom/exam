package components;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CalendarofEventsComponent extends AbsBaseComponent {
    private final String arrayCardsLocator = "//div[@class='dod_new-events__list js-dod_new_events']/a";
    private final String dateEventLocator = "//span[@class='dod_new-event__date-text']";
    private final String typeMeetLocator = "//div[@class='dod_new-event__type']";
    private final String filterLocator = "//div[@class='dod_new-events__header-left']//div[@class='dod_new-events-dropdown__input']//span[text()='Все мероприятия']/..";
    private static final String NAME_OF_FILTER_LOCATOR = "//div[@class='dod_new-events__header-left']//a[text()='%s']";

    public CalendarofEventsComponent(WebDriver driver) {
        super(driver);
    }

    public void checkData() {
        //Проверить, что есть карточки мероприятий
        List<WebElement> webElementList = $$(By.xpath(arrayCardsLocator));
        if (webElementList.size() != 0) {
            boolean isNotBefore = isNotBefore(webElementList);
            assertThat(isNotBefore)
                    .as("The date is less than the current one")
                    .isEqualTo(true);
        } else {
            assertThat(false)
                    .as("Event cards are missing")
                    .isEqualTo(true);
        }
    }

    public void checkFilter(String filterName) {
        //Проверить, что есть карточки мероприятий
        List<WebElement> webElementList = $$(By.xpath(arrayCardsLocator));
        if (webElementList.size() != 0) {
            // Скрываем элемент cookies через JavaScript
            $(By.xpath("//button[@class='js-cookie-accept cookies__button']")).click();
            $(By.xpath(filterLocator)).click();
            $(By.xpath(String.format(NAME_OF_FILTER_LOCATOR, filterName))).click();

            webElementList = $$(By.xpath(arrayCardsLocator));//обновили карточки
            if (webElementList.size() != 0) {
                boolean isRequiredType = isCardRequiredType(webElementList,filterName);
                assertThat(isRequiredType)
                        .as("The event type in the card does not match the selected filter")
                        .isEqualTo(true);

            } else {
                assertThat(false)
                        .as("Event cards for current type not found")
                        .isEqualTo(true);
            }
        } else {
            assertThat(false)
                    .as("Event cards are missing")
                    .isEqualTo(true);
        }
    }

//    public void checkFilter(String filterName) {
//        //Проверить, что есть карточки мероприятий
//        List<WebElement> webElementList = $$(By.xpath(arrayCardsLocator));
//        if (webElementList.size() != 0) {
//            $(By.xpath(filterLocator+"span")).click();
//            $(By.xpath(filterLocator+"a[text()='"+filterName+"']")).click();
//
//            webElementList = $$(By.xpath(arrayCardsLocator));//обновили карточки
//            if (webElementList.size() != 0) {
//                boolean isRequiredType = isCardRequiredType(webElementList,filterName);
//                assertThat(isRequiredType)
//                        .as("The event type in the card does not match the selected filter")
//                        .isEqualTo(true);
//
//            } else {
//                assertThat(false)
//                        .as("Event cards for current type not found")
//                        .isEqualTo(true);
//            }
//        } else {
//            assertThat(false)
//                    .as("Event cards are missing")
//                    .isEqualTo(true);
//        }
//    }

    private boolean isCardRequiredType(List<WebElement> webElementList, String filterName) {
        for (WebElement element : webElementList) {
            String name = element.findElement(By.xpath(typeMeetLocator)).getText();
            if (!name.equals(filterName)) {
                return false;
            }
        }
        return true;
    }


    private boolean isNotBefore(List<WebElement> webElementList) {
        LocalDate currentDate = LocalDate.now();

        for (WebElement element : webElementList) {
            LocalDate date = newDate(element.findElement(By.xpath(dateEventLocator)).getText());
            if (date.isBefore(currentDate)) {
                return false;
            }
        }
        return true;
    }

    private LocalDate newDate(String date) {
        String[] parts = replacementMonth(date).split("\\s+");
        int day = Integer.parseInt(parts[0]);
        int month = Month.valueOf(parts[1].toUpperCase()).getValue(); // Получаем числовое значение месяца по его названию
        return LocalDate.of(LocalDate.now().getYear(), month, day);
    }

    private String replacementMonth(String dateText) {
        Map<String, String> monthMap = new HashMap<>();
        monthMap.put("января", "January");
        monthMap.put("февраля", "February");
        monthMap.put("марта", "March");
        monthMap.put("апреля", "April");
        monthMap.put("мая", "May");
        monthMap.put("июня", "June");
        monthMap.put("июля", "July");
        monthMap.put("августа", "August");
        monthMap.put("сентября", "September");
        monthMap.put("октября", "October");
        monthMap.put("ноября", "November");
        monthMap.put("декабря", "December");

        for (Map.Entry<String, String> entry : monthMap.entrySet()) {
            dateText = dateText.replace(entry.getKey(), entry.getValue());
        }
        return dateText;
    }
}
