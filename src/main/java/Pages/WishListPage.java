package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.List;

import static Pages.Parameters.DEFAULT_TOGGLE;
import static Pages.Parameters.DELETE;

public class WishListPage extends GenericPage {
    private final WebDriver driver;

    private final int cellSize = 40;

    private final int[] screenSizeToValidateCheckMark = {750, 650};

    int listPositionToDelete;

    String cellContent;

    public int lengthOfInitialList;

    private By tableElement = By.cssSelector("#block-history > table");

    private By tableContents = By.cssSelector("tr[id*=\"wishlist_\"]");

    private By newWishListNameInputField = By.cssSelector("#name");

    private By saveWishListButton = By.cssSelector("#submitWishlist");

    public WishListPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void performActionOnCell(String wishListToDelete, String action) {
        int column = getButtonLocation(action);
        int row = getWishListLocation(wishListToDelete);
        List<WebElement> table = getTable();
        WebElement cell = table.get(row).findElements(By.tagName("td")).get(column);
        clickCell(cell, action);
    }

    // search for cell horizontally and vertically
    public int getCell(List<WebElement> element, String rowNameContains) {
        int positionInList = 0;
        for (WebElement e : element) {
            if (e.getText().contains(rowNameContains)) {
                break;
            }
            positionInList++;
        }
        return positionInList;
    }

    // get the right column
    public int getButtonLocation(String action) {
        // get the table
        List<WebElement> table = getTable();
        // select the header line by looking for the action
        WebElement header = table.get(getCell(table, action));
        // find out action location in the header / horizontal
        return getCell(header.findElements(By.tagName("th")), action);
    }

    // get the right row
    public int getWishListLocation(String wishListToDelete) {
        // get the table
        List<WebElement> table = getTable();
        // find out wishList position / vertical
        return getCell(table, wishListToDelete);
    }

    public void createWishList(String wishListToDelete) {
        driver.findElement(newWishListNameInputField).clear();
        driver.findElement(newWishListNameInputField).sendKeys(wishListToDelete);
        driver.findElement(saveWishListButton).click();
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

    public void clickCell(WebElement cell, String action) {
        if (action == DEFAULT_TOGGLE) {
            driver.manage().window().setSize(new Dimension
                    (screenSizeToValidateCheckMark[0], screenSizeToValidateCheckMark[1]));
            if (cell.getSize().height < cellSize) {
                cellClick(cell);
                driver.manage().window().maximize();
            }
        } else if (action == DELETE) {
            cellClick(cell);
            driver.switchTo().alert().accept();
        } else {
            cellContent = getCellContent(cell);
        }
    }


    public List<WebElement> getTableContents() {
        List<WebElement> table = driver.findElements(tableContents);
        return table;
    }

    public void cellClick(WebElement cell) {
        WebElement buttonToClick = cell.findElement(By.cssSelector("a"));
        waitAndClick(buttonToClick, 2);
    }

    public String getCellContent(WebElement cell) {
        return cell.getText();
    }

    public List<WebElement> getTable() {
        return findElementBy(tableElement).findElements(By.tagName("tr"));
    }

    public String getCellContent() {
        return cellContent;
    }
}
