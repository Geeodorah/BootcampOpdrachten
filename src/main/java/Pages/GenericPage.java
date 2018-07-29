package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GenericPage {
    private final WebDriver driver;

    public GenericPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickElement(By element){
        driver.findElement(element).click();
    }
    public String getHeaderName(){
        return driver.findElement(By.className("page-heading")).getText();
    }

}
