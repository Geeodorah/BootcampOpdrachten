package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class ContactUsPage extends GenericPage {

    private WebDriver driver;

    private By emailTextField = By.cssSelector("input#email");

    private By orderIdTextField = By.cssSelector("input#id_order");

    private By messageTextField = By.cssSelector("textarea#message");

    private By sendButton = By.cssSelector("button#submitMessage");

    private By SubjectDropdown = By.id("id_contact");

    public ContactUsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public WebElement fillinContactFormTest(String message, String email, String orderNumber) {
        Select dropdown = new Select(driver.findElement(SubjectDropdown));
        dropdown.selectByIndex(1);
        driver.findElement(messageTextField).sendKeys(message);
        driver.findElement(emailTextField).sendKeys(email);
        driver.findElement(orderIdTextField).sendKeys(orderNumber);
        driver.findElement(sendButton).click();

        return getAlert();


    }

    public WebElement getAlert() {
        return driver.findElement(By.className("alert"));
    }

    public String getSuccesMessage() {
        return "Your message has been successfully sent to our team.";
    }

    public void clickOnBody() {
        waitAndClick(driver.findElement(By.cssSelector("#message")), 2);
    }

    public void fillinEmail(String email){
        driver.findElement(emailTextField).sendKeys(email);
    }
}
