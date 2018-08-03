package BrowserDriven;

import Pages.ContactUsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class BrowserDrivenTest extends TestShopScenarioDriven {

    @Test
    public void fillInForm() {
        // open the contactpage
        WebElement logInButton = driver.findElement(By.cssSelector("#header > div.nav > div > div > nav > div.header_user_info > a"));
        assertThat(logInButton.isDisplayed()).isTrue().as("a user is logged in");
        driver.findElement(By.cssSelector("li#header_link_contact > a")).click();

        String message = "dikke massage gek!";
        String email = "bootcamper@feelthepain.com";
        String orderNumber = "4321234";


        ContactUsPage contactUsPage = new ContactUsPage(driver);
        contactUsPage.fillinContactFormTest(message, email, orderNumber);
        assertThat(driver.findElement(By.cssSelector("#center_column > p")).isDisplayed()).isTrue();

    }

}
