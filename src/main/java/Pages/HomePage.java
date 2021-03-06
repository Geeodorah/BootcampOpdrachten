package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends GenericPage {
    private final WebDriver driver;



//    private By getMyAccountLogInButton = By.cssSelector("a.login");
    private By loginButton = By.className("header_user_info");

    private By contactUSButton = By.id("contact-link");


    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    public void clickOnMyAccountLogInButton() {
        waitAndClick(driver.findElement(loginButton), 4);
    }

    public void clickOnContactUsButton(){
        waitAndClick(driver.findElement(contactUSButton), 4);

    }

    public WebElement getMyAccountLogInButton(){
        WebElement loginAccountButton = driver.findElement(loginButton);
        return loginAccountButton;
    }
}
