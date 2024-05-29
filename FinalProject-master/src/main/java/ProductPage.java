import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {

    public WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 10);
    }
    @FindBy(css = "svg[data-icon='cart-plus']")
    private WebElement cartButton;
    @FindBy(css = "svg[class='svg-inline--fa fa-heart fa-w-16 fa-3x ']")
    private WebElement wishlist;
    @FindBy(css = "small[class='text-muted']")
    private WebElement productName;
    @FindBy(css = "p small")
    private WebElement productInStock;
    @FindBy(css = "div[class='col col-lg-2 text-center col'] p")
    private WebElement productPrice;
    @FindBy(css = "svg[data-icon='shopping-cart']")
    private WebElement shoppingCart;
    @FindBy(css = "svg[data-icon='heart']")
    private WebElement wishlist2;
    @FindBy(css = "svg[data-icon='shopping-bag']")
    private WebElement shoppingBag;

    public void addToCart(){
        cartButton.click();
    }
    public void addToWishlist(){
        waitForElementToAppear(wishlist);
        wishlist.click();
    }
    public boolean verifyProductNameInShoppingCart(String product){
        return productNameInShoppingCart(product);
    }
    public boolean verifyProductNameInWishlist(String product){
        return productNameInWishlist(product);
    }
    public void returnToHomePage(){
        backToHomePage();
    }
    public String getProductName(){
        return productName.getText();
    }
    public String isInStock(){
        return productInStock.getText();
    }
    public String getProductPrice(){
        return productPrice.getText().split(" ")[0].trim();
    }
    public void goToShoppingCart(){
       getShoppingCart().click();
   }
    public boolean productNameInShoppingCart(String product){
        shoppingCart.click();
        String productName = driver.findElement(By.linkText(product)).getText();
        if(productName.equals(product))
            return true;
        return false;
    }
    public boolean productNameInWishlist(String product){
        wishlist2.click();
        String productName = driver.findElement(By.className("card-link")).getText();
        if(productName.equals(product))
            return true;
        return false;
    }
    public void backToHomePage(){
        shoppingBag.click();
    }
    public WebElement getShoppingCart(){
        return shoppingCart;
    }

}
