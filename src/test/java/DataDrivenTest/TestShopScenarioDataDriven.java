package DataDrivenTest;

import Browser.BrowserFactoryAdvanced;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestShopScenarioDataDriven {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = BrowserFactoryAdvanced.getDriver(BrowserFactoryAdvanced.DriversEnum.CHROME);
        String url = "https://techblog.polteq.com/testshop/index.php";

        driver.get(url);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
