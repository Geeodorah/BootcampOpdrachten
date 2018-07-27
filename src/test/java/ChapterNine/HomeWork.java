package ChapterNine;

import ChapterSix.TestShopScenario;
import Pages.HomePage;
import Pages.MyAccountPage;
import Pages.WishListPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class HomeWork extends TestShopScenario {
    public String email = "ben@brugman.com";
    public String password = "1qazxsw2";


    @Test
    private void fixTableChallenge() {

        // load page objects
        HomePage homePage = new HomePage(driver);
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        WishListPage wishListPage = new WishListPage(driver);

        // login
        homePage.logIn(email, password);

        // open wishList page
        myAccountPage.clickButton(myAccountPage.wishListButton);

        // check if the wishList exists
        wishListPage.checkForWishListPresence(wishListPage.getTable());

        // insert magic
        wishListPage.unravelTable(wishListPage.getTable());


    }




}
