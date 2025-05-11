package pages;

import Interfaces.IWebElement;
import enums.ElementType;
import factories.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utility.Locators;

import static factories.ElementFactory.getElement;


public class LoginPage {

    private WebDriver driver;

    IWebElement userNameInput = getElement(ElementType.TEXTFIELD,"Login Username Input", Locators.byXpath("//input[@id='user-name']"));
    IWebElement passwordInput = getElement(ElementType.TEXTFIELD,"Password Input", Locators.byXpath("//input[@id='password']"));
    IWebElement loginButton = getElement(ElementType.BUTTON,"Password Input", Locators.byXpath("//input[@id='login-button']"));
    IWebElement homePageLabel = getElement(ElementType.LABEL,"Homepage label", Locators.byXpath("//div[text()='Swag Labs']"));
    IWebElement lockedOutBanner = getElement(ElementType.LABEL,"Locked out Banner", Locators.byXpath("//h3[text()='Epic sadface: Sorry, this user has been locked out.']"));


    public LoginPage() {
        this.driver = DriverFactory.getDriver();
    }

    public LoginPage login(String username, String password) {
        userNameInput.waitTillElementFound();
        userNameInput.clearAndEnter(username);
        passwordInput.waitTillElementFound();
        passwordInput.clearAndEnter(password);
        loginButton.waitTillElementFound();
        loginButton.click();
        return this;
    }
    public boolean isAtHomePage() {
        return DriverFactory.getDriver().getCurrentUrl().contains("inventory.html");
    }
    public LoginPage verifyHomePageIsDisplayed(){
        homePageLabel.waitTillElementFound();
        Assert.assertTrue(homePageLabel.isDisplayed(),"Login did not work");
        return this;
    }
    public LoginPage verifyUserIsLockedOut(){
        lockedOutBanner.waitTillElementFound();
        Assert.assertTrue(lockedOutBanner.isDisplayed(),"The user is not locked out");
        return this;
    }


}
