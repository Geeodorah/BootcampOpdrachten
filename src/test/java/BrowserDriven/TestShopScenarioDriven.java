package BrowserDriven;

import Browser.BrowserFactoryAdvanced;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class TestShopScenarioDriven {
    protected WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(BrowserFactoryAdvanced.DriversEnum browser ) {
        driver = BrowserFactoryAdvanced.getDriver(browser);
        String url = "https://techblog.polteq.com/testshop/index.php";

        driver.get(url);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
