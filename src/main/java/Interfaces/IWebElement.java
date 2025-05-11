package Interfaces;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface IWebElement {
    void click();
    void clear();
    void clearAndEnter(CharSequence value);
    boolean isEnabled();
    boolean isDisplayed();
    void waitTillElementPresent();
    void scrollTo();
    void scrollToTop();
    boolean isDisabled();
    WebElement find(WebDriver driver);
    void waitTillElementFound();
    void waitTillElementInvisible();
    WebElement getElement();
}
