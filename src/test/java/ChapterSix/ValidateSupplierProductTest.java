package ChapterSix;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class ValidateSupplierProductTest {

    @Test
    public void logIn() {
        ChromeDriverManager.getInstance().setup();
        WebDriver driver = new ChromeDriver();
        String url = "https://techblog.polteq.com/testshop/index.php";


        driver.get(url);
//        driver.manage().window().maximize();

        String usernameOld = "Ben Brugman";
        String usernameChangeInto = "Ben";
        String emailAdress = "ben.brugman@polteq.com";
        String password = "polteq";

        // navigate to page
        driver.findElement(By.cssSelector("a.login")).click();
        driver.findElement(By.cssSelector("#email")).sendKeys(emailAdress);
        driver.findElement(By.cssSelector("#passwd")).sendKeys(password);
        driver.findElement(By.cssSelector("#SubmitLogin")).click();

        Select dropdown = new Select(driver.findElement(By.cssSelector("#suppliers_block_left > div > form > div > div > select")));
        dropdown.selectByIndex(0);
    }
}
