package ChapterSix;

import com.sun.org.apache.bcel.internal.generic.DREM;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;


public class FillCartTest {

    @Test
    private void fillCart() {
        ChromeDriverManager.getInstance().setup();
        WebDriver driver = new ChromeDriver();
        String url = "https://techblog.polteq.com/testshop/index.php";

        String emailAdress = "ben.brugman@polteq.com";
        String password = "polteq";


        driver.get(url);
        driver.manage().window().maximize();
        driver.findElement(By.className("ajax_cart_no_product")).click();

        WebElement emptyCartAlert = driver.findElement(By.cssSelector(".alert"));
        assertThat(emptyCartAlert.getText()).contains("empty").as("cart is not empty");

        driver.findElement(By.cssSelector(".logo")).click();
        driver.findElement(By.cssSelector("#tags_block_left > div > a.tag_level3.first_item")).click();
//        driver.findElementBy(By.className("replace-2x")).click();
        driver.findElement(By.cssSelector("#product_list > li.ajax_block_product.col-xs-12.col-sm-6.col-md-4.last-line.last-item-of-tablet-line.last-mobile-line > div > div.left-block > div > a > img")).click();
        driver.findElement(By.cssSelector("#add_to_cart")).click();

        WebElement button = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"btn btn-default button button-medium\"")));
        button.click();

        WebElement checkOutButton = driver.findElement(By.cssSelector("#center_column > p.cart_navigation.clearfix > a.button.btn.btn-default.standard-checkout.button-medium"));
        assertThat(checkOutButton.isDisplayed()).as("can out checkout, thus nothing added");
        WebElement checkOutHeader = driver.findElement(By.cssSelector("#cart_title > span"));
        assertThat(checkOutHeader.getText()).contains("1").as("cannot checkout, thus nothing added");


//        assertThat(emptyCartAlert.getText()).contains("Product").as("cart is not empty");
//        driver.findElementBy(By.className("span.ajax_cart_product_txt:nth-child(3)")).click();

//
//        WebElement proceedButton = driver.findElementBy(By.cssSelector(".standard-checkout > span:nth-child(1)"));
//        assertThat(emptyCartAlert.getText()).contains("empty").as("cart is not empty");
        // or via the overlay
//        driver.findElementBy(By.cssSelector("ul.active > li:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(4) > a:nth-child(1) > span:nth-child(1)")).click();

    }
}
