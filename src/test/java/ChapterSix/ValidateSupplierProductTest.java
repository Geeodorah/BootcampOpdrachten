package ChapterSix;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.TreeMap;

public class ValidateSupplierProductTest {
    WebElement elementToUse;

    @Test
    public void logIn() {
        ChromeDriverManager.getInstance().setup();
        WebDriver driver = new ChromeDriver();
        String url = "https://techblog.polteq.com/testshop/index.php";
        String productName = "MacBook Air";

        driver.get(url);

        Select dropdown = new Select(driver.findElement(By.cssSelector("#suppliers_block_left > div > form > div > div > select")));
        dropdown.selectByIndex(1);

        List<WebElement> centreColumn = driver.findElements(By.className("right-block"));

        boolean elementFound = false;
        for (WebElement element : centreColumn) {

            if (element.getText().contains(productName)) {
                elementToUse = element;
                assertThat(elementToUse.getText()).contains(productName).as("MacBook Air not in stock");
                System.out.println("Element found");
                elementFound = true;
            } else if (element.getAttribute("title").contains(productName)) {
                System.out.println("Element found in Attribute");
            } else {
                System.out.println(element.getText() + " No such element found \n");
            }
        }
        assertThat(elementFound).isTrue().as("no object found to validate");


//            WebElement textOnPage = element.findElementBy(By.cssSelector("#product_list > li.ajax_block_product.col-xs-12.col-sm-6.col-md-4.last-in-line.last-line.last-item-of-tablet-line.last-item-of-mobile-line.last-mobile-line"));
//            WebElement itemInWrapper = textOnPage.findElementBy(By.className("product-container"));
//            WebElement rightBlock = itemInWrapper.findElementBy(By.className("right-block"));
//            WebElement itemProp = rightBlock.findElementBy(By.cssSelector("#product_list > li.ajax_block_product.col-xs-12.col-sm-6.col-md-4.last-in-line.last-line.last-item-of-tablet-line.last-item-of-mobile-line.last-mobile-line > div > div.right-block > h5"));
//            WebElement namePart = itemProp.findElementBy(By.className("product-name"));

//            assertThat(namePart.getText()).isEqualTo("MacBook Air").as("MacBook Air not in stock");
//            assertThat(element.getText()).contains("MacBook Air").as("MacBook Air not in stock");

    }
}
