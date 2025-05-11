package utility;

import org.openqa.selenium.By;

public class Locators {
    public static By byId(final String id) {
        return By.id(id);
    }

    public static By byXpath(final String xpath) {
        return By.xpath(xpath);
    }

    public static By byCss(final String css) {
        return By.cssSelector(css);
    }

}
