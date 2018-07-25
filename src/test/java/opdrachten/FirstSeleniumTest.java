package opdrachten;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstSeleniumTest {
    WebDriver driver = new FirefoxDriver();
    String url = "https://techblog.polteq.com/testshop/index.php";
    String emailAdress = "ben.brugman@polteq.com";
    String password = "polteq";

    private void logIn() {
        driver.get(url);
        driver.findElement(By.cssSelector("a.login")).click();
        driver.findElement(By.cssSelector("#email")).sendKeys(emailAdress);
        driver.findElement(By.cssSelector("#passwd")).sendKeys(password);
        driver.findElement(By.cssSelector("#SubmitLogin")).click();


//        driver.findElement(By.className("login")).click();
//        driver.findElement(By.id("email")).sendKeys(emailAdress);
//        driver.findElement(By.id("passwd")).sendKeys(password);
//        driver.findElement(By.id("SubmitLogin")).click();
//        driver.findElement(By.xpath("a[class='login']")).click();


    }

    @Test
    private void logInSuccesfull() {
        logIn();
        WebElement logOutButton = driver.findElement(By.cssSelector("a.logout"));
        assertThat(logOutButton.isDisplayed());
        String myAccountButton = driver.findElement(By.cssSelector(".breadcrumb")).getText();
        assertThat(myAccountButton).as("check if this reads my account or Authentication").contains("My account");

        //        driver.quit();
    }
}
