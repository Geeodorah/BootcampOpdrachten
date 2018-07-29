package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends GenericPage {
    private final WebDriver driver;



//    private By loginButton = By.cssSelector("a.login");
    private By loginButton = By.className("header_user_info");


    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    public void navigateToLogInPage() {
        clickElement(loginButton);
//        driver.findElement(loginButton).click();
    }

    public WebElement loginButton(){
        return driver.findElement(loginButton);
    }

    public WebElement logOutButton(){
        return driver.findElement(loginButton);
    }
}
