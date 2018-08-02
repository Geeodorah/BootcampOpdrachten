package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GenericPage {
    private final WebDriver driver;

    private By homePage = By.cssSelector("#header_logo > a > img");

    public GenericPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickElementBy(By element) {
        driver.findElement(element).click();
    }

    public WebElement findElementBy(By element) {
        return driver.findElement(element);
    }

    public void useInputField(By element, String input) {
        driver.findElement(element).sendKeys("input");
    }

    public String getHeaderName() {
        return driver.findElement(By.className("page-heading")).getText();
    }

    public void waitAndClick(WebElement element, int waitFor) {
        WebDriverWait wait = new WebDriverWait(driver,waitFor);
        WebElement button = wait.until(ExpectedConditions.visibilityOf(element));
        button.click();
//        WebElement button = new WebDriverWait(driver, waitFor).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void openHomePage() {
        clickElementBy(homePage);
    }
}
