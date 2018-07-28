package Pages;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WishListPage {
    private final WebDriver driver;

    private By tableContents = By.cssSelector("tr[id*=\"wishlist_\"]");

    private By newWishListNameInputField = By.cssSelector("#name");

    private By saveWishListButton = By.cssSelector("#submitWishlist");

    public WishListPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getTable() {
        return driver.findElements(tableContents);
    }

    public void unravelTable(List<WebElement> table, String wishListToDelete) {
        for (WebElement e : table) {
            if (e.getText().contains(wishListToDelete)) {
                e.findElement(By.cssSelector("td.wishlist_delete > a")).click();
                //* todo change to accept and run createWishList
                driver.switchTo().alert().dismiss();
//                createWishList();
                break;
            }
        }
    }

    public void checkForWishListPresence(List<WebElement> table, String wishListToDelete) {
        ArrayList<Boolean> checkList = new ArrayList<Boolean>();
        for (WebElement e : table) {
            if (e.getText().contains(wishListToDelete)) {
                checkList.add(true);
            } else {
                checkList.add(false);
            }
        }
        if (!checkList.contains(true))
            createWishList();
    }

    public void createWishList() {
        //*todo change to variable String
        driver.findElement(newWishListNameInputField).clear();
//        driver.findElement(newWishListNameInputField).sendKeys(wishListToDelete);
        driver.findElement(newWishListNameInputField).sendKeys("FietsTas");
        driver.findElement(saveWishListButton).click();


    }
}
