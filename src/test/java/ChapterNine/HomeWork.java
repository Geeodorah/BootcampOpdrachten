package ChapterNine;

import ChapterSix.TestShopScenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static Pages.Parameters.*;
import static org.assertj.core.api.Assertions.assertThat;

public class HomeWork extends TestShopScenario {
    public String wishListToUse = GAIN;
    public String actionToPerform = DELETE;
    public String[] credentials = AT_BRUGMAN;

    @Test
    private void deleteWishList() {

        openLoginPage();

        // uses email & password variable
        logInWithCredentials(credentials);

        openWishListPage();

        // check if the wishList exists, uses wishListToUse variable
        checkForWishListPresence();

        // insert performActionOnCell
        deleteWishList(wishListToUse);


        // reset start situation, uses wishListToUse variable
        if (!wishListPage.checkForWishListPresence(wishListToUse)) {
            createWishListForNextRun();
        }
    }

    @Test
    private void changeDefaultWishList(){
        this.actionToPerform = DEFAULT_TOGGLE;

        openLoginPage();

        logInWithCredentials(AT_BRUGMAN);

        openWishListPage();

        changeDefault();
    }

    @Test
    private void shareLinkUsed(){
        this.actionToPerform = VIEWED;
        this.wishListToUse = CARDIO;

        openLoginPage();

        logInWithCredentials(AT_POLTEQ);

        openWishListPage();

        validateShareLinkIncrease();
    }

    private void validateShareLinkIncrease() {
        wishListPage.performActionOnCell(wishListToUse, actionToPerform);
        int initialViews = Integer.parseInt(wishListPage.getCellContent());
        openShareURL();
        driver.navigate().refresh();
        wishListPage.performActionOnCell(wishListToUse, actionToPerform);
        int viewsAfterAction = Integer.parseInt(wishListPage.getCellContent());
        assertThat(viewsAfterAction).isGreaterThan(initialViews).as("the link didn't increase the of views in table");
    }

    private void openShareURL(){
        driver.get("https://techblog.polteq.com/testshop/index.php?token=B359E1A7F6BA20A6&fc=module&module=blockwishlist&controller=view ");
        WebElement element = driver.findElement(By.className("page-heading"));
        new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOf(element));
        driver.navigate().back();
    }

    private void openLoginPage() {
        assertThat(homePage.getMyAccountLogInButton().isDisplayed()).isTrue().as("There is already a user logged in");
        homePage.clickOnMyAccountLogInButton();
        assertThat(driver.getTitle()).isEqualTo("Authentication - TestShop").as("whoops, we arn't on the login / authentication page" + getCurrentPage());
    }

    private void logInWithCredentials(String[] credentials) {
        loginPage.login(credentials);
        assertThat(myAccountPage.getHeaderName()).isEqualTo("MY ACCOUNT").as("not logged in" + getCurrentPage());
        assertThat(homePage.getMyAccountLogInButton().isDisplayed()).isTrue().as("Login failed");
    }

    private void openWishListPage() {
        myAccountPage.clickButton(myAccountPage.wishListButton);
        assertThat(wishListPage.getHeaderName()).isEqualTo("MY WISHLISTS").as("you are not on the wishList page" + getCurrentPage());
    }

    private void checkForWishListPresence() {
        if (!wishListPage.checkForWishListPresence(wishListToUse)) {
            wishListPage.createWishList(wishListToUse);
        }
        assertThat(wishListPage.checkForWishListPresence(wishListToUse)).isEqualTo(true).as("the table doesn't contain your wishList even after attempting to create one");
    }

    private void deleteWishList(String wishListToDelete) {
        wishListPage.performActionOnCell(wishListToDelete, actionToPerform);

        openMyAccountPage();
        openWishListPage();
        assertDeletion();
        wishListPage.openHomePage();
    }

    private void changeDefault(){
        wishListPage.performActionOnCell(wishListToUse, actionToPerform);
        //todo ad assertion
    }

    private void createWishListForNextRun() {
        navigateToMyAccountViaHomepage();
        assertDeletion();
        wishListPage.createWishList(wishListToUse);
        homePage.openHomePage();
    }

    // helper methods
    private void navigateToMyAccountViaHomepage() {
        homePage.openHomePage();
        if (homePage.getMyAccountLogInButton().getText().contains("Log in")) {
            openLoginPage();
            logInWithCredentials(credentials);
        } else {
            openMyAccountPage();
        }
        openWishListPage();
    }

    private void assertDeletion() {
        if (actionToPerform == DELETE) {
            assertThat(wishListPage.checkForWishListPresence(wishListToUse)).isEqualTo(false).as("the wishList is not deleted");
        }
    }

    private String getCurrentPage() {
        return ", you are one the " + driver.getCurrentUrl() + " page when this happened";
    }

    private void openMyAccountPage() {
        homePage.clickOnMyAccountLogInButton();
    }
}
