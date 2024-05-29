import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ExtentTestNGITestListener.class)

public class CheckoutPageTest extends Hooks {
    public HomePage homePage;
    public ProductPage productPage;
    public CartPage cartPage;
    public CheckoutPage checkoutPage;

    @BeforeMethod
    public void setupPageObject() {
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    // 10 tests

    @Test ()
    public void verifyDeliveryInformation(){
        homePage.clickOnProductOne();
        productPage.addToCart();
        productPage.goToShoppingCart();
        cartPage.goToCheckoutPage();
        checkoutPage.provideInfo("Cristi","Kanyaro", "Cluj");
        String shippingInfo = checkoutPage.getShippingInfo();
        Assert.assertEquals(shippingInfo, "CHOO CHOO DELIVERY!");
        ExtentTestNGITestListener.getTest().log(Status.INFO, "Delivery info is correctly displayed");
    }

    @Test ()
    public void verifyPaymentInformation(){
        homePage.clickOnProductOne();
        productPage.addToCart();
        productPage.goToShoppingCart();
        cartPage.goToCheckoutPage();
        checkoutPage.provideInfo("Cristi", "Kanyaro", "Cluj");
        String paymentInfo = checkoutPage.getPaymentInfo();
        Assert.assertEquals(paymentInfo, "Cash on delivery");
        ExtentTestNGITestListener.getTest().log(Status.INFO, "Payment info is correctly displayed");
    }

    @Test ()
    public void returnToCartPageFromCheckoutInformation(){
        homePage.clickOnProductOne();
        productPage.addToCart();
        productPage.goToShoppingCart();
        cartPage.goToCheckoutPage();
        checkoutPage.goBackToCartPage();
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The user has successfully returned to cart page from checkout info");
    }

    @Test ()
    public void returnToCartPageFromCheckoutSummary(){
        homePage.clickOnProductOne();
        productPage.addToCart();
        productPage.goToShoppingCart();
        cartPage.goToCheckoutPage();
        checkoutPage.provideInfo("Cristi", "Kanyaro", "Cluj");
        checkoutPage.goBackToCartPage();
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The user has successfully returned to cart page from checkout summary");
    }

    @Test ()
    public void verifyTotalPrice(){
        homePage.clickOnProductOne();
        productPage.addToCart();
        productPage.goToShoppingCart();
        cartPage.goToCheckoutPage();
        checkoutPage.provideInfo("Cristi", "Kanyaro", "Cluj");
        String totalPrice = checkoutPage.getTotalPrice();
        Assert.assertEquals(totalPrice, "$15.99");
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The product price is correctly displayed on checkout page");
    }

    @Test ()
    public void verifyErrorMessageWhileFirstNameIsNotSelected(){
        homePage.clickOnProductOne();
        productPage.addToCart();
        productPage.goToShoppingCart();
        cartPage.goToCheckoutPage();
        checkoutPage.provideInfo("","Kanyaro", "Cluj");
        String errorMessage = checkoutPage.getOrderMessage();
        Assert.assertEquals(errorMessage, "First Name is required");
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The correct error message is displayed when the first name field is empty");
    }

    @Test ()
    public void verifyErrorMessageWhileLastNameIsNotSelected(){
        homePage.clickOnProductOne();
        productPage.addToCart();
        productPage.goToShoppingCart();
        cartPage.goToCheckoutPage();
        checkoutPage.provideInfo("Cristi", "", "Cluj");
        String errorMessage = checkoutPage.getOrderMessage();
        Assert.assertEquals(errorMessage, "Last Name is required");
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The correct error message is displayed when the last name field is empty");
    }

    @Test ()
    public void verifyErrorMessageWhileAddressIsNotSelected(){
        homePage.clickOnProductOne();
        productPage.addToCart();
        productPage.goToShoppingCart();
        cartPage.goToCheckoutPage();
        checkoutPage.provideInfo("Cristi", "Kanyaro", "");
        String errorMessage = checkoutPage.getOrderMessage();
        Assert.assertEquals(errorMessage, "Address is required");
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The correct error message is displayed when the address field is empty");
    }

    @Test ()
    public void completeOrder(){
        homePage.clickOnProductOne();
        productPage.addToCart();
        productPage.goToShoppingCart();
        cartPage.goToCheckoutPage();
        checkoutPage.provideInfo("Cristi","Kanyaro", "Cluj");
        checkoutPage.completeOrder();
        String successfulMessage = checkoutPage.getCompleteOrderSuccessfulMessage();
        Assert.assertEquals(successfulMessage, "Thank you for your order!");
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The user has successfully placed the order");
    }

    @Test ()
    public void returnToShoppingAfterOrderCompletion(){
        homePage.clickOnProductOne();
        productPage.addToCart();
        productPage.goToShoppingCart();
        cartPage.goToCheckoutPage();
        checkoutPage.provideInfo("Cristi","Kanyaro", "Cluj");
        checkoutPage.completeOrder();
        String successfulMessage = checkoutPage.getCompleteOrderSuccessfulMessage();
        Assert.assertEquals(successfulMessage, "Thank you for your order!");
        checkoutPage.continueShopping();
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The user has successfully returned to shopping after he placed the order");
    }

}
