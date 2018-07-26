package ChapterSix;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;


public class AdjustPersonalInfoTest {
    @Test
    public void logIn() {
        ChromeDriverManager.getInstance().setup();
        WebDriver driver = new ChromeDriver();
        String url = "https://techblog.polteq.com/testshop/index.php";


        driver.get(url);
//        driver.manage().window().maximize();

        String usernameOld = "Ben Brugman";
        String usernameChangeInto = "Ben";
        String emailAdress = "ben.brugman@polteq.com";
        String password = "polteq";

        // navigate to page
        driver.findElement(By.cssSelector("a.login")).click();
        driver.findElement(By.cssSelector("#email")).sendKeys(emailAdress);
        driver.findElement(By.cssSelector("#passwd")).sendKeys(password);
        driver.findElement(By.cssSelector("#SubmitLogin")).click();

        driver.findElement(By.cssSelector("#my-account")).click();
        driver.findElement(By.className("icon-user")).click();


        WebElement firstName = driver.findElement(By.cssSelector("#header > div.nav > div > div > nav > div:nth-child(2) > a"));
        if (!firstName.getText().equals(usernameOld)) {
            System.out.println("in the IF");
            driver.findElement(By.cssSelector("#firstname")).click();
            driver.findElement(By.cssSelector("#firstname")).clear();
            driver.findElement(By.cssSelector("#firstname")).sendKeys(usernameChangeInto);
            driver.findElement(By.cssSelector("#old_passwd")).sendKeys(password);
            driver.findElement(By.cssSelector("#center_column > div > form > fieldset > div:nth-child(11) > button")).click();
        } else {
            System.out.println("in the ELSE");
            driver.findElement(By.cssSelector("#firstname")).click();
            driver.findElement(By.cssSelector("#firstname")).clear();
            driver.findElement(By.cssSelector("#firstname")).sendKeys("username");
            driver.findElement(By.cssSelector("#old_passwd")).sendKeys(password);
            driver.findElement(By.cssSelector("#center_column > div > form > fieldset > div:nth-child(11) > button")).click();
        }
//        assertThat(driver.findElement(By.cssSelector("#header > div.nav > div > div > nav > div:nth-child(2) > a")).getText().equals(usernameChangeInto)).as("name isn't stored correctly");
        String setName = driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/nav/div[2]/a")).getText();
        assertThat(setName).isEqualTo(usernameOld).as("name isn't stored correctly");

    }
}
