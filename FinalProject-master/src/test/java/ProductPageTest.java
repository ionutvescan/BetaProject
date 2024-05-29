import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ExtentTestNGITestListener.class)

public class ProductPageTest extends Hooks {
    public HomePage homePage;
    public ProductPage productPage;

    @BeforeMethod
    public void setupPageObject() {
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
    }

    // 6 tests

    @Test ()
    public void addProductToCart(){
        homePage.clickOnProductOne();
        productPage.addToCart();
        boolean hasProductName = productPage.verifyProductNameInShoppingCart("Awesome Granite Chips");
        Assert.assertTrue(hasProductName);
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The product was added to cart from Product Page");
    }

    @Test()
    public void addProductToWishlist(){
        homePage.clickOnProductOne();
        productPage.addToWishlist();
        boolean hasProductName = productPage.verifyProductNameInWishlist("Awesome Granite Chips");
        Assert.assertTrue(hasProductName);
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The product was added to wishlist from Product Page");
    }

    @Test ()
    public void verifyPrice(){
        homePage.clickOnProductOne();
        String actualPrice = productPage.getProductPrice();
        Assert.assertEquals(actualPrice, "15.99");
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The correct product price is displayed");
    }

    @Test ()
    public void verifyProductName(){
        homePage.clickOnProductOne();;
        String productName = productPage.getProductName();
        Assert.assertEquals(productName, "Awesome Granite Chips");
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The correct product name is displayed");
    }

    @Test ()
    public void verifyProductInStock(){
        homePage.clickOnProductOne();;
        String productName = productPage.isInStock();
        Assert.assertEquals(productName, "in stock");
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The product is in stock");
    }

    @Test ()
    public void goToHomePage(){
        homePage.clickOnProductOne();
        productPage.returnToHomePage();
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The user has successfully returned to homepage");
    }

}
