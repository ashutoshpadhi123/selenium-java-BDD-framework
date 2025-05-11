package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.HomePage;

public class HomeSteps {

    HomePage homePage = new HomePage();

    @Then("user clicks on a product and sees the product detail page of the {string}")
    public void verifyProductDetailPageIsDisplayed(String product){
        homePage.viewProductDetails(product);
    }
    @Then("user adds {string} and {string} to the cart")
    public void addingProductsToTheCart(String product1, String product2){
        homePage.addProductToTheCart(product1);
        homePage.addProductToTheCart(product2);
    }
    @And("user sees the cart icon indicate the number of products in the cart")
    public void checkProductNumberInCart(){
        homePage.verifyNumberOfProductsInCart("2");
    }
}
