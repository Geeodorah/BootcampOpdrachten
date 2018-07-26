package ChapterSix;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SignOutTest {
    @Test

    private void logIn() {
        ChromeDriverManager.getInstance().setup();
        WebDriver driver = new ChromeDriver();
        String url = "https://techblog.polteq.com/testshop/index.php";
        String emailAdress = "ben.brugman@polteq.com";
        String password = "polteq";

        driver.get(url);
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector("a.login")).click();
        driver.findElement(By.cssSelector("#email")).sendKeys(emailAdress);
        driver.findElement(By.cssSelector("#passwd")).sendKeys(password);
        driver.findElement(By.cssSelector("#SubmitLogin")).click();

        WebElement logOutButton = driver.findElement(By.cssSelector("a.logout"));
        assertThat(logOutButton.isDisplayed());
        String myAccountButton = driver.findElement(By.cssSelector(".breadcrumb")).getText();
        assertThat(myAccountButton).as("check if this reads my account or Authentication").contains("My account");

        driver.quit();
    }

    @Test
    private void logOut() {
        ChromeDriverManager.getInstance().setup();
        WebDriver driver = new ChromeDriver();
        String url = "https://techblog.polteq.com/testshop/index.php";
        String emailAdress = "ben.brugman@polteq.com";
        String password = "polteq";

        driver.get(url);
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector("a.login")).click();
        driver.findElement(By.cssSelector("#email")).sendKeys(emailAdress);
        driver.findElement(By.cssSelector("#passwd")).sendKeys(password);
        driver.findElement(By.cssSelector("#SubmitLogin")).click();

        WebElement logOutButton = driver.findElement(By.cssSelector("a.logout"));
        assertThat(logOutButton.isDisplayed());
        String myAccountButton = driver.findElement(By.cssSelector(".breadcrumb")).getText();
        assertThat(myAccountButton).as("check if this reads my account or Authentication").contains("My account");
        assertThat("Sign in").contains(driver.findElement(By.cssSelector("#header > div.nav > div > div > nav > div:nth-child(2) > a")).getText());


        logOutButton.click();
        WebElement buttonToValidate = driver.findElement(By.cssSelector("a.login"));
        assertThat (buttonToValidate.isDisplayed()).as("Logout button is missing");
        assertThat("Sign in").contains(driver.findElement(By.cssSelector("#header > div.nav > div > div > nav > div:nth-child(2) > a")).getText());
        driver.quit();
    }
}
