package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;


public class ContactUsPage {

    private final WebDriver driver;

    private By emailTextField = By.cssSelector("input#email");

    private By orderIdTextField = By.cssSelector("input#id_order");

    private By messageTextField = By.cssSelector("textarea#message");

    private By sendButton = By.cssSelector("button#submitMessage");

    private By SubjectDropdown = By.id("id_contact");

    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillinContactFormTest(String subject, String message, String email, String orderNumber) {
        Select dropdown = new Select(driver.findElement(SubjectDropdown));
        dropdown.selectByIndex(1);
        driver.findElement(messageTextField).sendKeys(message);
        driver.findElement(emailTextField).sendKeys(email);
        driver.findElement(orderIdTextField).sendKeys(orderNumber);
        driver.findElement(sendButton).click();



    }
}
