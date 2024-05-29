import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ExtentTestNGITestListener.class)

public class CartPageTest extends Hooks {
    public HomePage homePage;
    public ProductPage productPage;
    public CartPage cartPage;

    @BeforeMethod
    public void setupPageObject() {
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    // 5 tests

    @Test ()
    public void verifyPrice(){
        homePage.clickOnProductOne();
        productPage.addToCart();
        productPage.goToShoppingCart();
        String actualPrice = cartPage.getUnitPrice();
        Assert.assertEquals(actualPrice, "$15.99");
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The product price from product page is the same with the one from cart page");
    }

    @Test ()
    public void removeProduct(){
        homePage.clickOnProductOne();
        productPage.addToCart();
        productPage.goToShoppingCart();
        cartPage.removeProduct();
        String message = cartPage.friendlyMessage();
        Assert.assertEquals(message, "How about adding some products in your cart?");
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The product has been removed");
    }

    @Test ()
    public void removeProductThroughProductQuantity(){
        homePage.clickOnProductOne();
        productPage.addToCart();
        productPage.goToShoppingCart();
        cartPage.decrementProductQuantity();
        String message = cartPage.friendlyMessage();
        Assert.assertEquals(message, "How about adding some products in your cart?");
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The product has been removed by reducing quantity");
    }

    @Test ()
    public void increaseQuantity(){
        homePage.clickOnProductOne();
        productPage.addToCart();
        productPage.goToShoppingCart();
        cartPage.incrementProductQuantity();
        String actualTotalPrice = cartPage.getTotalPrice();
        Assert.assertEquals(actualTotalPrice, "$31.98");
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The product quantity has been increased and the price updated accordingly");
    }

    @Test ()
    public void returnToProductPage(){
        homePage.clickOnProductOne();
        productPage.addToCart();
        productPage.goToShoppingCart();
        cartPage.goBackToProduct();
        String actualProductName = productPage.getProductName();
        Assert.assertEquals(actualProductName,"Awesome Granite Chips");
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The user has successfully returned to product page");
    }

}
