package factories;

import enums.WaitStrategy;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import java.time.Duration;
import java.util.function.Function;

public class ExplicitWaitFactory {
    private ExplicitWaitFactory(){}

    private static void waitForCondition(By by, Function condition, long time){
        try{
            new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(time))
                    .ignoring(StaleElementReferenceException.class, ElementNotInteractableException.class)
                    .pollingEvery(Duration.ofMillis(500))
                    .until(condition);
            if(!condition.toString().contains("no longer be visible")){
                new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(time))
                        .ignoring(StaleElementReferenceException.class, ElementNotInteractableException.class)
                        .pollingEvery(Duration.ofMillis(500))
                        .until(new ExpectedCondition<Boolean>() {
                            public Boolean apply(WebDriver d){
                                return d.findElement(by).isDisplayed();
                            }
                        });
            }
        }catch (WebDriverException we){
            System.out.println(we.toString());
            System.out.println("WebDriver Exception for Element: "+by);
        }
    }

    public static void performExplicitWait(WaitStrategy waitStrategy, By by){
//        switch(waitStrategy) {
//            case WaitStrategy.CLICKABLE: {
//                waitForCondition(by, ExpectedConditions.elementToBeClickable(by), 60);
//            }
//            case WaitStrategy.PRESENCE:{
//                waitForCondition(by,ExpectedConditions.presenceOfElementLocated(by),60);
//            }
//            case WaitStrategy.VISIBLE:{
//                waitForCondition(by,ExpectedConditions.visibilityOfElementLocated(by),60);
//            }
//            case WaitStrategy.INVISIBLE:{
//                waitForCondition(by,ExpectedConditions.invisibilityOfElementLocated(by),60);
//            }
//            default:
//                DriverFactory.getDriver().findElement(by);
//        }
        if (waitStrategy == WaitStrategy.CLICKABLE) {
            waitForCondition(by,ExpectedConditions.elementToBeClickable(by), 60);}
        else if (waitStrategy == WaitStrategy.PRESENCE) {    waitForCondition(by,ExpectedConditions.presenceOfElementLocated(by), 60);}
        else if (waitStrategy == WaitStrategy.VISIBLE) {    waitForCondition(by,ExpectedConditions.visibilityOfElementLocated(by), 60);}
        else if (waitStrategy == WaitStrategy.INVISIBLE) {    waitForCondition(by,ExpectedConditions.invisibilityOfElementLocated(by), 60);}
        else if (waitStrategy == WaitStrategy.NONE) {    DriverFactory.getDriver().findElement(by);}
    }
    public static void performExplicitWait(WaitStrategy waitStrategy, By by, long seconds){
        switch(waitStrategy) {
            case WaitStrategy.CLICKABLE: {
                waitForCondition(by, ExpectedConditions.elementToBeClickable(by), seconds);
            }
            case WaitStrategy.PRESENCE:{
                waitForCondition(by,ExpectedConditions.presenceOfElementLocated(by),seconds);
            }
            case WaitStrategy.VISIBLE:{
                waitForCondition(by,ExpectedConditions.visibilityOfElementLocated(by),seconds);
            }
            case WaitStrategy.INVISIBLE:{
                waitForCondition(by,ExpectedConditions.invisibilityOfElementLocated(by),seconds);
            }
            default:
                DriverFactory.getDriver().findElement(by);
        }
    }
}
