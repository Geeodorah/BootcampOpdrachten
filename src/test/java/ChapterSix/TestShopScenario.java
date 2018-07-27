package ChapterSix;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestShopScenario {
    protected WebDriver driver;


    @BeforeMethod
    public void setUp() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        String url = "https://techblog.polteq.com/testshop/index.php";


        driver.get(url);
        driver.manage().window().maximize();
    }



//    @AfterMethod
//    public void tearDown() {
//        driver.quit();
//    }
}
