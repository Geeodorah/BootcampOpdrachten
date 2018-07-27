package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage {
    private final WebDriver driver;

    public By wishListButton = By.className("lnk_wishlist");

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickButton(By button){
        driver.findElement(button).click();
    }
}
