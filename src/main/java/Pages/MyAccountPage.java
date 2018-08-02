package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyAccountPage extends GenericPage{
    private final WebDriver driver;

    public static By wishListButton = By.className("lnk_wishlist");

    public MyAccountPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void clickButton(By button){
        driver.findElement(button).click();
    }


}
