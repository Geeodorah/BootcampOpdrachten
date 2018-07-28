package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GenericPage {
    private final WebDriver driver;

    public GenericPage(WebDriver driver) {
        this.driver = driver;
    }
    public String getHeaderName(){
        return driver.findElement(By.className("page-heading")).getText();
    }

}
