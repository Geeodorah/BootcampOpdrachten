package ChapterNine;

import ChapterSix.TestShopScenario;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HomeWork extends TestShopScenario {
    public String email = "ben@brugman.com";
    public String password = "1qazxsw2";
    public String wishListToDelete = "No Pain No Gain";


    @Test
    private void fixTableChallenge() {

        navigateToLoginPage();

        // uses email & password variable
        logInWithCredentials();

        openWishListPage();

        // check if the wishList exists, uses wishListToDelete variable
        checkForWishListPresence();

        // insert deleteWishList
        deleteWishList();

        // reset start situation, uses wishListToDelete variable

        createWishListForNextRun();

//        navigateToLoginPage();
//        logInWithCredentials();
    }

    private void navigateToLoginPage() {
        assertThat(homePage.loginButton().isDisplayed()).isTrue().as("There is already a user logged in");
        homePage.navigateToLogInPage();
        assertThat(driver.getTitle()).isEqualTo("Authentication - TestShop").as("whoops, we arn't on the login / authentication page" + getCurrentPage());
    }

    private void logInWithCredentials() {
        loginPage.login(email, password);
        assertThat(myAccountPage.getHeaderName()).isEqualTo("MY ACCOUNT").as("not logged in" + getCurrentPage());
        assertThat(homePage.logOutButton().isDisplayed()).isTrue().as("Login failed");
    }

    private void openWishListPage() {
        myAccountPage.clickButton(myAccountPage.wishListButton);
        assertThat(wishListPage.getHeaderName()).isEqualTo("MY WISHLISTS").as("you are not on the wishList page" + getCurrentPage());
    }

    private void checkForWishListPresence() {
        if (!wishListPage.checkForWishListPresence(wishListToDelete)) {
            wishListPage.createWishList(wishListToDelete);
        }
        assertThat(wishListPage.checkForWishListPresence(wishListToDelete)).isEqualTo(true).as("the table doesn't contain your wishList even after attempting to create one");
        //* todo assert if wishList to delete exists
    }

    private void deleteWishList() {
        wishListPage.deleteWishList(wishListToDelete);
        driver.manage().deleteAllCookies();
        while (wishListPage.getCurrentListLength() == wishListPage.getLengthOfInitialList()) {
            if (wishListPage.getCurrentListLength() < wishListPage.getLengthOfInitialList()) {
                continue;
            } else {
                continue;
            }
        }
        assertThat(wishListPage.waitForTableToAppear().isDisplayed()).isTrue();
        assertThat(wishListPage.checkForWishListPresence(wishListToDelete)).isEqualTo(false).as("the wishList is not deleted");
    }

    private void createWishListForNextRun() {
        navigateToLoginPage();
        logInWithCredentials();
        openWishListPage();
        assertThat(wishListPage.checkForWishListPresence(wishListToDelete)).isEqualTo(false).as("the wishList already exists");
        wishListPage.createWishList(wishListToDelete);
    }



    private String getCurrentPage() {
        return ", you are one the " + driver.getCurrentUrl() + " page when this happened";
    }
}
