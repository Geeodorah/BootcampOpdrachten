package ChapterNine;

import ChapterSix.TestShopScenario;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.MyAccountPage;
import Pages.WishListPage;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HomeWork extends TestShopScenario {
    public String email = "ben@brugman.com";
    public String password = "1qazxsw2";
    public String wishListToDelete = "No Pain No Gain";


    @Test
    private void fixTableChallenge() {

        // load page objects
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        WishListPage wishListPage = new WishListPage(driver);

        // navigate to login page
        assertThat(homePage.loginButton().isDisplayed()).isTrue().as("There is already a user logged in");
        homePage.navigateToLogInPage();

        // login
        assertThat(driver.getTitle().equals("Authentication - TestShop")).as("whoops, we arn't on the login / authentication page.");
        loginPage.login(email, password);
        assertThat(myAccountPage.getHeaderName().getText().equals("My Account")).as(" not logged in");

        // open wishList page
        myAccountPage.clickButton(myAccountPage.wishListButton);

        // check if the wishList exists
        // Paramater: the table and name of wishlist to be delete
        wishListPage.checkForWishListPresence(wishListPage.getTable(), wishListToDelete);

        // insert magic
        // Paramater: the table and name of wishlist to be delete
        wishListPage.unravelTable(wishListPage.getTable(), wishListToDelete);


    }




}
