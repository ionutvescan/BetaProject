import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class Hooks {

    public WebDriver driver;

    @BeforeMethod
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ionut\\Desktop\\FinalProject-master\\FinalProject-master\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://fasttrackit-test.netlify.app/#/");
    }

    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }
}
