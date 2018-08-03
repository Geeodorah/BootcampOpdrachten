package DataDrivenTest;

import Pages.ContactUsPage;
import Pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class DataDrivenTest extends TestShopScenarioDataDriven {

    @Parameters({"email", "orderID", "message", "subject"})
    @Test
    public void fillInForm(String email, String orderID, String message, String subject) {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnContactUsButton();

        ContactUsPage contactUsPage = new ContactUsPage(driver);
        contactUsPage.fillinContactFormTest(message,email,orderID,subject);

        assertThat(driver.findElement(By.cssSelector("#center_column > p")).isDisplayed()).isTrue();
    }

}
