package ChapterSix;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SignOutTest extends TestShopScenario {

    @Test
    public void logIn() {
        String emailAdress = "ben.brugman@polteq.com";
        String password = "polteq";


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
    public void logOut() {
        String emailAdress = "ben.brugman@polteq.com";
        String password = "polteq";

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
