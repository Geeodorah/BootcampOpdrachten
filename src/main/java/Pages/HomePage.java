package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;

    private By loginButton = By.cssSelector("a.login");
    private By emailTextField = By.cssSelector("#email");
    private By passwordTextField = By.cssSelector("#passwd");
    private By submitButton = By.cssSelector("#SubmitLogin");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }


    public void logIn(String email, String password) {
        driver.findElement(loginButton).click();
        driver.findElement(emailTextField).sendKeys(email);
        driver.findElement(passwordTextField).sendKeys(password);
        driver.findElement(submitButton).click();

    }
}
