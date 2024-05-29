import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage extends BasePage {

    public WebDriverWait wait;

    public HomePage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 10);
    }

    @FindBy (css = "svg[data-icon='cart-plus']")
    private WebElement addFirstProduct;
    @FindBy (css = ".svg-inline--fa.fa-heart.fa-w-16.fa-2x")
    private WebElement productToWishlist;
    @FindBy(css = "svg[data-icon='sign-in-alt']")
    private WebElement login;
    @FindBy(css = "svg[data-icon='sign-out-alt']")
    private WebElement logout;
    @FindBy(id = "user-name")
    private WebElement userName;
    @FindBy(id = "password")
    private WebElement pwd;
    @FindBy(css = ".btn-primary")
    private WebElement loginButton;
    @FindBy(linkText = "dino")
    private WebElement accountName;
    @FindBy(xpath = "//span[contains(text(), 'Hello guest!')]")
    private WebElement greetingMessage;
    @FindBy (css = "svg[data-icon='heart']")
    private WebElement whishlist;
    @FindBy (id = "input-search")
    private WebElement searchButton;
    @FindBy (linkText = "Refined Frozen Mouse")
    private WebElement getFrozenMouse ;
    @FindBy (linkText = "Practical Metal Mouse")
    private WebElement getMetalMouse;
    @FindBy (linkText = "Awesome Granite Chips")
    private WebElement awesomeGraniteChips;
    @FindBy (linkText = "Refined Frozen Mouse")
    private WebElement refinedFrozenMouse;
    @FindBy(css = ".card-text span")
    private WebElement productPrice;
    @FindBy(css = "svg[data-icon='shopping-cart']")
    private WebElement shoppingCart;
    @FindBy(css = ".sort-products-select.form-control.form-control-sm")
    private WebElement sortingProducts;

    public void addProductToCart() {
        addFirstProduct.click();
    }
    public void addProductToWishlist() {
        productToWishlist.click();
    }
    public boolean verifyProductAddedToCart(String product){
        shoppingCart.click();
        String productName = driver.findElement(By.linkText(product)).getText();
        if(productName.equals(product))
            return true;
        return false;
    }
    public boolean verifyProductAddedToWishList(String product){
        whishlist.click();
        String productName = driver.findElement(By.linkText(product)).getText();
        if(productName.equals(product))
            return true;
        return false;
    }
    public void clickOnSearchButton (){
        searchButton.sendKeys("mouse");
    }
    public void clickSearchBar (){
        searchButton.click();
    }
    public WebElement getGetFrozenMouse (){
        return getFrozenMouse;
    }
    public WebElement getGetMetalMouse (){
        return getMetalMouse;
    }
    public void logIntoApplication(){
        login.click();
        waitForElementToAppear(userName);
        userName.sendKeys("dino");
        pwd.sendKeys("choochoo");
        loginButton.click();
    }
    public String getAccountName(){
        return accountName.getText();
    }
    public void logOutOfApplication(){
        logout.click();
    }
    public String getGreetingMessage(){
        return greetingMessage.getText();
    }
    public void clickOnProductOne(){
        awesomeGraniteChips.click();
    }

    Select select = new Select(sortingProducts);

    public void selectProductBy(String text){
        select.selectByVisibleText(text);
    }
    public boolean isSortedByNameAsc(){
        if(awesomeGraniteChips.getText().equals("Awesome Granite Chips")){
            return true;
        }
        return false;
    }
    public boolean isSortedByNameDesc(){
        if(refinedFrozenMouse.getText().equals("Refined Frozen Mouse")){
            return true;
        }
        return false;
    }
    public boolean isSortedByPriceAsc(){
        if(productPrice.getText().equals("1.99")){
            return true;
        }
        return false;
    }
    public boolean isSortedByPriceDesc(){
        if(productPrice.getText().equals("29.99")){
            return true;
        }
        return false;
    }

}
