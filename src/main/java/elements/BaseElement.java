package elements;

import Interfaces.IWebElement;
import enums.WaitStrategy;
import factories.DriverFactory;
import factories.ExplicitWaitFactory;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
/*
Implements the various methods in the WebElement interface
 */

public class BaseElement implements IWebElement {
    private String label;
    private String locator;
    @Getter
    private By by =null;
    protected WebElement element=null;

    private static enum LocatorType{
        ID,NAME,CSS_SELECTOR,XPATH
    }
    private By getLocatorBy(final String locator, final LocatorType locatorType){
        return switch (locatorType) {
            case ID -> By.id(locator);
            case NAME -> By.name(locator);
            case CSS_SELECTOR -> By.cssSelector(locator);
            default -> By.xpath(locator);
        };
    }
    public BaseElement(final String label,final String locator,final LocatorType locatorType){
        this.label=label;
        this.locator=locator;
        this.by=getLocatorBy(locator,locatorType);
    }
    public BaseElement(final String label,final By by){
        this.label=label;
        this.by=by;
    }

    @Override
    public void click() {
        find(DriverFactory.getDriver());
        waitTillElementFound();
        element.click();
    }
    @Override
    public void waitTillElementFound() {
        ExplicitWaitFactory.performExplicitWait(WaitStrategy.VISIBLE,by);
    }
    @Override
    public void waitTillElementInvisible() {
        ExplicitWaitFactory.performExplicitWait(WaitStrategy.INVISIBLE,by);
    }
    public void waitTillElementClickable() {
        ExplicitWaitFactory.performExplicitWait(WaitStrategy.CLICKABLE,by);
    }

    @Override
    public void clear() {
        find(DriverFactory.getDriver());
        element.clear();
    }

    @Override
    public void clearAndEnter(CharSequence value) {
        waitTillElementClickable();
        new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(60))
                .ignoring(StaleElementReferenceException.class, ElementNotInteractableException.class)
                .until((WebDriver d) ->{
                    WebElement htmlElement = d.findElement(by);
                    htmlElement.clear();
                    htmlElement.sendKeys(value);
                    return true;
        });
    }

    @Override
    public boolean isEnabled() {
        element = DriverFactory.getDriver().findElement(by);
        return element.isEnabled();
    }

    @Override
    public boolean isDisplayed() {
        element = DriverFactory.getDriver().findElement(by);
        return element.isDisplayed();
    }

    @Override
    public void waitTillElementPresent() {
        ExplicitWaitFactory.performExplicitWait(WaitStrategy.PRESENCE,by);
    }

    @Override
    public void scrollTo() {
        find(DriverFactory.getDriver());
        waitTillElementPresent();
        ((JavascriptExecutor)DriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView({ behavior: \"instant\"})", element);
        waitTillElementFound();
    }

    @Override
    public void scrollToTop() {
        ((JavascriptExecutor)DriverFactory.getDriver()).executeScript("window.scrollTo({ behavior: \"instant\", top: 0, left: 0});");
    }

    @Override
    public boolean isDisabled() {
        find(DriverFactory.getDriver());
        boolean result=false;
        if(!element.isEnabled()){
            result=true;
            System.out.println("The Element is Disabled");
        }else{
            System.out.println("The element is enabled");
        }
        return result;
    }

    @Override
    public WebElement find(WebDriver driver) {
        if (element == null) {
            element = driver.findElement(by);
        }
        return element;
    }
    @Override
    public WebElement getElement(){
        element = DriverFactory.getDriver().findElement(by);
        return element;
    }
}
