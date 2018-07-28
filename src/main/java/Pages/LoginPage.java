package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    private By emailTextField = By.cssSelector("#email");

    private By passwordTextField = By.cssSelector("#passwd");

    private By submitButton = By.cssSelector("#SubmitLogin");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String email, String password) {
        driver.findElement(emailTextField).sendKeys(email);
        driver.findElement(passwordTextField).sendKeys(password);
        driver.findElement(submitButton).click();
    }
}
