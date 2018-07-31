package ChapterNine;

import ChapterSix.TestShopScenario;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static Pages.Parameters.*;
import static org.assertj.core.api.Assertions.assertThat;

public class HomeWork extends TestShopScenario {
    public String email = "ben@brugman.com";
    public String password = "1qazxsw2";
    public String wishListToDelete = "No Pain No Gain";
    public String actionToPerform = DELETE;
    private By loginButton = By.className("header_user_info");

    @Test
    private void fixTableChallenge() {

        openLoginPage();

        // uses email & password variable
        logInWithCredentials();

        openWishListPage();

        // check if the wishList exists, uses wishListToDelete variable
        checkForWishListPresence();

        // insert deleteWishList
        deleteWishList(wishListToDelete);


        // reset start situation, uses wishListToDelete variable
        if (!wishListPage.checkForWishListPresence(wishListToDelete)) {
            createWishListForNextRun();
        }


    }

    private void openLoginPage() {
        assertThat(homePage.getMyAccountLogInButton().isDisplayed()).isTrue().as("There is already a user logged in");
        homePage.clickOnMyAccountLogInButton();
        assertThat(driver.getTitle()).isEqualTo("Authentication - TestShop").as("whoops, we arn't on the login / authentication page" + getCurrentPage());
    }

    private void logInWithCredentials() {
        loginPage.login(email, password);
        assertThat(myAccountPage.getHeaderName()).isEqualTo("MY ACCOUNT").as("not logged in" + getCurrentPage());
        assertThat(homePage.getMyAccountLogInButton().isDisplayed()).isTrue().as("Login failed");
    }

    private void checkForWishListPresence() {
        if (!wishListPage.checkForWishListPresence(wishListToDelete)) {
            wishListPage.createWishList(wishListToDelete);
        }
        assertThat(wishListPage.checkForWishListPresence(wishListToDelete)).isEqualTo(true).as("the table doesn't contain your wishList even after attempting to create one");
    }

    private void deleteWishList(String wishListToDelete) {
        wishListPage.deleteWishList(wishListToDelete, actionToPerform);
        openMyAccountPage();
        openWishListPage();
        assertDeletion();
        wishListPage.openHomePage();
    }

    private void createWishListForNextRun() {
        navigateToMyAccountViaHomepage();
        assertDeletion();
        wishListPage.createWishList(wishListToDelete);
        homePage.openHomePage();
    }

    private void navigateToMyAccountViaHomepage() {
        homePage.openHomePage();
        if (homePage.getMyAccountLogInButton().getText().contains("Log in")) {
            openLoginPage();
            logInWithCredentials();
        } else {
            openMyAccountPage();
        }

        openWishListPage();
    }

    private String getCurrentPage() {
        return ", you are one the " + driver.getCurrentUrl() + " page when this happened";
    }

    private void openWishListPage() {
        myAccountPage.clickButton(myAccountPage.wishListButton);
        assertThat(wishListPage.getHeaderName()).isEqualTo("MY WISHLISTS").as("you are not on the wishList page" + getCurrentPage());
    }

    private void openMyAccountPage() {
        driver.findElement(loginButton).click();
    }

    private void assertDeletion() {
        if (actionToPerform == DELETE) {
            assertThat(wishListPage.checkForWishListPresence(wishListToDelete)).isEqualTo(false).as("the wishList is not deleted");
        }
    }
}
