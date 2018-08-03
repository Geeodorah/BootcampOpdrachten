package DataDrivenTest;

import Pages.HomePage;
import Pages.LoginPage;
import com.opencsv.CSVReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataDrivenLogInCSV extends TestShopScenarioDataDriven {

    @DataProvider(name = "Authentication")
    public Iterator<Object []> provider( ) throws InterruptedException, IOException {
        List<Object []> testCases = new ArrayList<>();
        String[] data= null;
        String line;

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:/Users/Ben Brugman/Documents/CSVmap/csvFile.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while ((line = br.readLine()) != null) {
            data = line.split(",");
            testCases.add(data);
        }

        return testCases.iterator();
    }

    @Test (dataProvider = "Authentication")
    public void login(String email, String password) {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnMyAccountLogInButton();

        LoginPage loginPage = new LoginPage(driver);
        String[] credentials = {email, password};
        loginPage.login(credentials);
    }
}

