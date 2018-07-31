package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends GenericPage {
    private final WebDriver driver;



//    private By getMyAccountLogInButton = By.cssSelector("a.login");
    private By loginButton = By.className("header_user_info");


    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    public void clickOnMyAccountLogInButton() {
        clickElementBy(loginButton);
    }

    public WebElement getMyAccountLogInButton(){
        return driver.findElement(loginButton);
    }
}
