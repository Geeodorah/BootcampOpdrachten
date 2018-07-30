package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.List;

public class WishListPage extends GenericPage {
    private final WebDriver driver;

    int listPositionToDelete;

    public int lengthOfInitialList;

    private By tableElement = By.cssSelector("#block-history > table");

    private By tableContents = By.cssSelector("tr[id*=\"wishlist_\"]");

    private By newWishListNameInputField = By.cssSelector("#name");

    private By saveWishListButton = By.cssSelector("#submitWishlist");

    public WishListPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public List<WebElement> getTable() {
        return findElementBy(tableElement).findElements(By.tagName("tr"));
    }

    public int findCell(List<WebElement> element, String rowNameContains) {
        int positionInList = 0;
        for (WebElement e : element) {
            if (e.getText().contains(rowNameContains)) {
                break;
            }
            positionInList++;
        }
        return positionInList;
    }

    public int getButtonLocation(String action) {
        // get the table
        List<WebElement> table = getTable();
        // select the header line by looking for the action
        WebElement header = table.get(findCell(table, action));
        // find out action location in the header / horizontal
        return findCell(header.findElements(By.tagName("th")), action);
    }

    public int getWishListLocation(String wishListToDelete) {
        // get the table
        List<WebElement> table = getTable();
        // find out wishlist position / vertical
        return  findCell(table, wishListToDelete);
    }

    public void deleteWishList(String wishListToDelete, String action) {
        int button = getButtonLocation(action);
        int wishList = getWishListLocation(wishListToDelete);
        List<WebElement> table = getTable();
        WebElement cell = table.get(wishList).findElements(By.tagName("td")).get(button);
        cell.findElement(By.cssSelector("a")).click();
        driver.switchTo().alert().accept();
    }

//    public void deleteWishList() {
//        List<WebElement> table = getTableContents();
//        table.get(listPositionToDelete).findElementBy(By.cssSelector("td.wishlist_delete > a")).click();
//        driver.switchTo().alert().accept();
//    }

    public List<WebElement> getTableContents() {
        List<WebElement> table = driver.findElements(tableContents);
        return table;
    }


    public Boolean checkForWishListPresence(String wishListToDelete) {
        int positionInList = 0;
        List<WebElement> table = getTableContents();
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


    public void createWishList(String wishListToDelete) {
        driver.findElement(newWishListNameInputField).clear();
        driver.findElement(newWishListNameInputField).sendKeys(wishListToDelete);
        driver.findElement(saveWishListButton).click();
    }

    public int getLengthOfInitialList() {
        return lengthOfInitialList;
    }

    public int getCurrentListLength() {
        return getTableContents().size();
    }

    @Override
    public String getHeaderName() {
        return driver.findElement(By.className("page-heading")).getText();
    }
}
