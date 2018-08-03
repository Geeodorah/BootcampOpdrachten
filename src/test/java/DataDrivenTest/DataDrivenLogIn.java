package DataDrivenTest;

import BrowserDriven.TestShopScenarioDriven;
import Pages.HomePage;
import Pages.LoginPage;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DataDrivenLogIn extends TestShopScenarioDataDriven {

    @Parameters({"email", "password"})
    @Test
    public void login(String email, String password) {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnMyAccountLogInButton();

        LoginPage loginPage = new LoginPage(driver);
        String[] credentials = {email, password};
        loginPage.login(credentials);
    }
}

