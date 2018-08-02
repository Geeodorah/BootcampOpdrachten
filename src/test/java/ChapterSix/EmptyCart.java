package ChapterSix;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class EmptyCart {

    @Test
    public void emptyCart() {
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

        //comment in or out to change if route
//        driver.findElementBy(By.cssSelector(".logo")).click();
//        driver.findElementBy(By.cssSelector("#tags_block_left > div > a.tag_level3.first_item")).click();
//        driver.findElementBy(By.cssSelector("#product_list > li.ajax_block_product.col-xs-12.col-sm-6.col-md-4.last-line.last-item-of-tablet-line.last-mobile-line > div > div.left-block > div > a > img")).click();
//        driver.findElementBy(By.cssSelector("#add_to_cart")).click();
//        WebElement buttonOne = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"btn btn-default button button-medium\"")));
//        buttonOne.click();

        WebElement emptyCartAlert2 = driver.findElement(By.cssSelector(".alert"));
        if (!emptyCartAlert2.getText().contains("empty")) {
            System.out.println("in IF statement");
            driver.findElement(By.cssSelector("#product_2_2_0_0 > td.cart_delete.text-center")).click();
            WebElement cartButton = new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='alert alert-warning']")));
            assertThat(cartButton.getText().contains("empty")).as("cart is not empty");
        } else if (emptyCartAlert2.getText().contains("empty")) {
            System.out.println("in ELSE statement");

            driver.findElement(By.cssSelector(".logo")).click();
            driver.findElement(By.cssSelector("#tags_block_left > div > a.tag_level3.first_item")).click();
            driver.findElement(By.cssSelector("#product_list > li.ajax_block_product.col-xs-12.col-sm-6.col-md-4.last-line.last-item-of-tablet-line.last-mobile-line > div > div.left-block > div > a > img")).click();
            driver.findElement(By.cssSelector("#add_to_cart")).click();

            WebElement buttonTwo = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"btn btn-default button button-medium\"")));
            buttonTwo.click();

            WebElement checkOutButton = driver.findElement(By.cssSelector("#center_column > p.cart_navigation.clearfix > a.button.btn.btn-default.standard-checkout.button-medium"));
            assertThat(checkOutButton.isDisplayed()).as("can out checkout, thus nothing added");
            WebElement checkOutHeader = driver.findElement(By.cssSelector("#cart_title > span"));
            assertThat(checkOutHeader.getText()).contains("1").as("cannot checkout, thus nothing added");

            driver.findElement(By.cssSelector("#product_2_2_0_0 > td.cart_delete.text-center")).click();
            WebElement cartButton = new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='alert alert-warning']")));
            assertThat(cartButton.getText().contains("empty")).as("cart is not empty");
        }
    }
}
