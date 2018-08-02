package ChapterNine;

import ChapterSix.TestShopScenario;
import Pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static Pages.MyAccountPage.wishListButton;
import static Pages.Parameters.*;
import static org.assertj.core.api.Assertions.assertThat;

public class HomeWork extends TestShopScenario {
    public String wishListToUse = GAIN;
    public String actionToPerform = DELETE;
    public String[] credentials = AT_BRUGMAN;


    HomePage homePage;
    LoginPage loginPage;
    MyAccountPage myAccountPage;
    WishListPage wishListPage;
    ContactUsPage contactUsPage;

    @Test
    public void deleteWishList() {
        this.homePage = new HomePage(driver);
        this.loginPage = new LoginPage(driver);
        this.myAccountPage = new MyAccountPage(driver);
        this.wishListPage = new WishListPage(driver);

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
    public void changeDefaultWishList(){
        this.homePage = new HomePage(driver);
        this.loginPage = new LoginPage(driver);
        this.myAccountPage = new MyAccountPage(driver);
        this.wishListPage = new WishListPage(driver);
        this.actionToPerform = DEFAULT_TOGGLE;

        openLoginPage();

        logInWithCredentials(AT_BRUGMAN);


        openWishListPage();

        changeDefault();
    }

    @Test
    public void shareLinkUsed(){
        this.homePage = new HomePage(driver);
        this.loginPage = new LoginPage(driver);
        this.myAccountPage = new MyAccountPage(driver);
        this.wishListPage = new WishListPage(driver);
        this.actionToPerform = VIEWED;
        this.wishListToUse = CARDIO;

        openLoginPage();

        logInWithCredentials(AT_POLTEQ)
        .clickButton(wishListButton);

        openWishListPage();

        validateShareLinkIncrease();
    }

    @Test
    public void contactUs(){
        this.homePage = new HomePage(driver);
        this.contactUsPage = new ContactUsPage(driver);

        openContactUsPAge();
        WebElement alert =  contactUsPage.fillinContactFormTest("dikke massage gek!", "ben@burmgan.com", "79159");
        assertThat(alert.getText()).as("this is not a or the current succes message").isEqualTo(contactUsPage.getSuccesMessage());

    }

    @Test
    public void contactUsNOK(){
        this.homePage = new HomePage(driver);
        this.contactUsPage = new ContactUsPage(driver);
        String email = "werktniethe";

        openContactUsPAge();
        WebElement alert = contactUsPage.fillinContactFormTest("dikke massage gek!", email, "79159");
        assertThat(driver.findElement(By.className("alert")).isDisplayed()).as("account was created with " + email).isTrue();
        assertThat(alert.getText()).as("This is not a or the current error message").contains("There is 1 error");
        assertThat(driver.findElement(By.className("form-control")).isDisplayed()).as("no error feedback given in the field").isTrue();
    }


    private void openContactUsPAge() {
        homePage.clickOnContactUsButton();
    }


    private void validateShareLinkIncrease() {
        wishListPage.performActionOnCell(wishListToUse, actionToPerform);
        int initialViews = Integer.parseInt(wishListPage.getCellContent());
        openShareURL();
        driver.navigate().refresh();
        wishListPage.performActionOnCell(wishListToUse, actionToPerform);
        int viewsAfterAction = Integer.parseInt(wishListPage.getCellContent());
        assertThat(viewsAfterAction).as("the link didn't increase the of views in table").isGreaterThan(initialViews);
    }

    private void openShareURL(){
        driver.get("https://techblog.polteq.com/testshop/index.php?token=B359E1A7F6BA20A6&fc=module&module=blockwishlist&controller=view ");
        WebElement element = driver.findElement(By.className("page-heading"));
        new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOf(element));
        driver.navigate().back();
    }

    private void openLoginPage() {
//        assertThat(homePage.getMyAccountLogInButton().isDisplayed()).as("There is already a user logged in").isTrue();
        homePage.clickOnMyAccountLogInButton();
        assertThat(driver.getTitle()).as("whoops, we arn't on the login / authentication page" + getCurrentPage()).isEqualTo("Authentication - TestShop");
    }

    private MyAccountPage logInWithCredentials(String[] credentials) {
        loginPage.login(credentials);
        assertThat(myAccountPage.getHeaderName()).as("not logged in" + getCurrentPage()).isEqualTo("MY ACCOUNT");
        assertThat(homePage.getMyAccountLogInButton().isDisplayed()).as("Login failed").isTrue();
        return new MyAccountPage(driver);
    }

    private void openWishListPage() {
        myAccountPage.clickButton(wishListButton);
        assertThat(wishListPage.getHeaderName()).as("you are not on the wishList page" + getCurrentPage()).isEqualTo("MY WISHLISTS");
    }

    private void checkForWishListPresence() {
        if (!wishListPage.checkForWishListPresence(wishListToUse)) {
            wishListPage.createWishList(wishListToUse);
        }
        assertThat(wishListPage.checkForWishListPresence(wishListToUse)).as("the table doesn't contain your wishList even after attempting to create one").isEqualTo(true);
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
            assertThat(wishListPage.checkForWishListPresence(wishListToUse)).as("the wishList is not deleted").isEqualTo(false);
        }
    }

    private String getCurrentPage() {
        return ", you are one the " + driver.getCurrentUrl() + " page when this happened";
    }

    private void openMyAccountPage() {
        homePage.clickOnMyAccountLogInButton();
    }
}
