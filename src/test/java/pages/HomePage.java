package pages;

import Interfaces.IWebElement;
import enums.ElementType;
import factories.DriverFactory;
import org.testng.Assert;
import utility.Locators;

import static factories.ElementFactory.getElement;


public class HomePage {

    IWebElement productsLabel = getElement(ElementType.LABEL,"Products Label", Locators.byXpath("//span[text()='Products']"));
    IWebElement backToProductsButton = getElement(ElementType.BUTTON,"Back to Products Button", Locators.byId("back-to-products"));
    IWebElement shoppingCartLink = getElement(ElementType.LINK,"Shopping Cart", Locators.byXpath("//span[@class='shopping_cart_badge']"));

    private IWebElement productName(String productName){
        String xpath= "//div[text()='"+productName+"']";
        return getElement(ElementType.LINK,productName,Locators.byXpath(xpath));
    }
    private IWebElement productPrice(String productPrice){
        String xpath = "//div[text()='"+productPrice+"']/../../following-sibling::div/div[@data-test='inventory-item-price']";
        return getElement(ElementType.DEFAULT,productPrice,Locators.byXpath(xpath));
    }
    private IWebElement productPriceDetail(String productPrice){
        String xpath = "//div[text()='"+productPrice+"']/following-sibling::div[@data-test='inventory-item-price']";
        return getElement(ElementType.DEFAULT,productPrice,Locators.byXpath(xpath));
    }
    private IWebElement addToCartButton(String productName){
        String xpath= "//div[text()='"+productName+"']/../../following-sibling::div/div/following-sibling::button[text()='Add to cart']";
        return getElement(ElementType.BUTTON,productName,Locators.byXpath(xpath));
    }
    private IWebElement removeFromCartButton(String productName){
        String xpath= "//div[text()='"+productName+"']/../../following-sibling::div/div/following-sibling::button[text()='Remove']";
        return getElement(ElementType.BUTTON,productName,Locators.byXpath(xpath));
    }

    public boolean isAt() {
        return DriverFactory.getDriver().getCurrentUrl().contains("inventory.html");
    }

    public HomePage viewProductDetails(String productName){
        productName(productName).waitTillElementFound();
        String name= productName(productName).getElement().getText();
        productPrice(productName).waitTillElementFound();
        String price = productPrice(productName).getElement().getText();
        productName(productName).scrollTo();
        productsLabel.waitTillElementFound();
        productName(productName).click();
        backToProductsButton.waitTillElementFound();
        Assert.assertTrue(backToProductsButton.isDisplayed());
        Assert.assertTrue(productName(name).isDisplayed());
        String detailPrice=productPriceDetail(name).getElement().getText();
        Assert.assertEquals(detailPrice, price);
        return this;
    }
    public HomePage addProductToTheCart(String productName){
        addToCartButton(productName).waitTillElementFound();
        addToCartButton(productName).click();
        removeFromCartButton(productName).waitTillElementFound();
        Assert.assertTrue(removeFromCartButton(productName).isDisplayed(),"Product is not added to the cart");
        return this;
    }
    public HomePage verifyNumberOfProductsInCart(String number){
        shoppingCartLink.waitTillElementFound();
        String numberOfProducts=shoppingCartLink.getElement().getText();
        Assert.assertEquals(number, numberOfProducts);
        return this;
    }
    public HomePage removeProductFromCart(String productName){
        removeFromCartButton(productName).waitTillElementFound();
        removeFromCartButton(productName).click();
        addToCartButton(productName).waitTillElementFound();
        Assert.assertTrue(removeFromCartButton(productName).isDisplayed(),"Product is not removed from the cart");
        return this;
    }
}
