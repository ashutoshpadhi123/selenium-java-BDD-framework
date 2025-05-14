package steps;

import enums.Users;
import factories.DriverFactory;
import factories.UserFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.LoginPage;
import pojo.User;

import java.net.MalformedURLException;


public class LoginSteps {

    private final LoginPage loginPage;

    public LoginSteps(){this.loginPage = new LoginPage();}

    @Given("user launches the application")
    public void user_launches_the_application() throws MalformedURLException {
        DriverFactory.launchApplication();
    }

    @When("user logs in as {string}")
    public void user_logs_in_as(String userTypeStr) {
    Users userType = Users.valueOf(userTypeStr.toUpperCase());
    User user = UserFactory.getUser(userType);
    loginPage.login(user.getUsername(), user.getPassword());
    }

    @Then("user should be navigated to homepage")
    public void user_should_be_navigated_to_homepage() {
        Assert.assertTrue(loginPage.isAtHomePage(),"Home page is not displayed");
    }
    @And("user sees the homepage")
    public void verifyHomepage(){
        loginPage.verifyHomePageIsDisplayed();
    }
    @Then("user sees the locked out banner")
    public void verifyLockedOutManner(){
        loginPage.verifyUserIsLockedOut();
    }
}
