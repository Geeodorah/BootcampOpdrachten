package ChapterSix;

import Pages.*;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestShopScenario  {
    protected WebDriver driver;
    protected HomePage homePage;
    protected LoginPage loginPage;
    protected MyAccountPage myAccountPage;
    protected WishListPage wishListPage;



    @BeforeMethod
    public void setUp() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        String url = "https://techblog.polteq.com/testshop/index.php";
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        myAccountPage = new MyAccountPage(driver);
        wishListPage = new WishListPage(driver);


        driver.get(url);
        driver.manage().window().maximize();
    }



//    @AfterMethod
//    public void tearDown() {
//        driver.quit();
//    }
}
