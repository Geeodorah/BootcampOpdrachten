package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private final WebDriver driver;

    private By loginButton = By.cssSelector("a.login");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }


    public void navigateToLogInPage() {
        driver.findElement(loginButton).click();
    }

    public WebElement loginButton(){
        return driver.findElement(By.cssSelector("a.login"));
    }
}
