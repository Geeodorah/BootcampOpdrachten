package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class WishListPageWorking extends GenericPage {
    private final WebDriver driver;

    int listPositionToDelete;

    int deleteColumn;

    public int lengthOfInitialList;

    private By tableContents = By.cssSelector("tr[id*=\"wishlist_\"]");

    private By newWishListNameInputField = By.cssSelector("#name");

    private By saveWishListButton = By.cssSelector("#submitWishlist");

    private By headerElement = By.cssSelector("#block-history > table");

    public WishListPageWorking(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public List<WebElement> getTable() {
        List<WebElement> table = driver.findElements(tableContents);
        return table;
    }

    public void deleteWishList() {
        List<WebElement> table = getTable();
        table.get(listPositionToDelete).findElement(By.cssSelector("td.wishlist_delete > a")).click();
        driver.switchTo().alert().accept();
    }

    public Boolean checkForWishListPresence(String wishListToDelete) {
        int positionInList = 0;
        List<WebElement> table = getTable();
        ArrayList<Boolean> checkList = new ArrayList<Boolean>();
        lengthOfInitialList = table.size();
        for (WebElement e : table) {
            if (e.getText().contains(wishListToDelete)) {
                checkList.add(true);
                listPositionToDelete = positionInList;
            } else {
                checkList.add(false);
            }
            positionInList++;

        }

        return checkList.contains(true);
    }

    public WebElement waitForTableToAppear() {
        WebElement wishListHeader = new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOfElementLocated(headerElement));
        return wishListHeader;
    }

    public void createWishList(String wishListToDelete) {
        driver.findElement(newWishListNameInputField).clear();
        driver.findElement(newWishListNameInputField).sendKeys(wishListToDelete);
        driver.findElement(saveWishListButton).click();
    }

    public int getLengthOfInitialList() {
        return lengthOfInitialList;
    }

    public int getCurrentListLength(){
        return getTable().size();
    }

    @Override
    public String getHeaderName() {
        return driver.findElement(By.className("page-heading")).getText();
    }
}
