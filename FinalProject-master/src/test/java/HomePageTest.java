import com.aventstack.extentreports.Status;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Listeners(ExtentTestNGITestListener.class)

public class HomePageTest extends Hooks {

    public HomePage homePage;
    public WebDriverWait wait;

    @BeforeMethod
    public void SetupPageObject() {
        homePage = new HomePage(driver);
        wait = new WebDriverWait(driver, 10);
    }

    // 9 tests

    @Test
    public void login() {
        homePage.logIntoApplication();
        assertEquals(homePage.getAccountName(), "dino");
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The user has successfully logged in");
    }

    @Test
    public void logout() {
        homePage.logIntoApplication();
        homePage.logOutOfApplication();
        assertEquals(homePage.getGreetingMessage(), "Hello guest!");
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The user has successfully logged out");
    }

    @Test
    public void addProductToCart() { // add product on shopping cart from homepage
        homePage.addProductToCart();
        boolean message = homePage.verifyProductAddedToCart("Awesome Granite Chips");
        Assert.assertTrue(message);
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The product was added to cart");
    }

    @Test
    public void addProductToWishList() { // add product on wishlist from homepage
        homePage.addProductToWishlist();
        boolean message = homePage.verifyProductAddedToWishList("Awesome Granite Chips");
        Assert.assertTrue(message);
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The product was added to wishlist");
    }

    @Test(description = "Click on search button")
    public void clickOnSearchButton() { //testing search bar functionality
        homePage.clickSearchBar();
        homePage.clickOnSearchButton();
        assertEquals("Refined Frozen Mouse", homePage.getGetFrozenMouse().getText());
        assertEquals("Practical Metal Mouse", homePage.getGetMetalMouse().getText());
        ExtentTestNGITestListener.getTest().log(Status.INFO, "Two products was found after searching");
    }

    @Test
    public void productSortedByNameAsc() {
        homePage.selectProductBy("Sort by name (A to Z)");
        boolean isSorted = homePage.isSortedByNameAsc();
        Assert.assertTrue(isSorted);
        ExtentTestNGITestListener.getTest().log(Status.INFO, "Products have been sorted in ascending order by name");
    }

    @Test
    public void productSortedByNameDesc() {
        homePage.selectProductBy("Sort by name (Z to A)");
        boolean isSorted = homePage.isSortedByNameDesc();
        Assert.assertTrue(isSorted);
        ExtentTestNGITestListener.getTest().log(Status.INFO, "Products have been sorted in descending order by name");
    }

    @Test
    public void productSortedByPriceAsc() {
        homePage.selectProductBy("Sort by price (low to high)");
        boolean isSorted = homePage.isSortedByPriceAsc();
        Assert.assertTrue(isSorted);
        ExtentTestNGITestListener.getTest().log(Status.INFO, "Products have been sorted in ascending order by price");
    }

    @Test
    public void productSortedByPriceDesc() {
        homePage.selectProductBy("Sort by price (high to low)");
        boolean isSorted = homePage.isSortedByPriceDesc();
        Assert.assertTrue(isSorted);
        ExtentTestNGITestListener.getTest().log(Status.INFO, "Products have been sorted in descending order by price");
    }

}
