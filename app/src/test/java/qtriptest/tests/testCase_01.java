package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import static org.testng.Assert.*;

public class testCase_01 {
    private WebDriver driver; // Use WebDriver, not RemoteWebDriver (generalized)
    public String lastGeneratedUsername;

    // Initialize the driver using the Singleton class
    @BeforeSuite(alwaysRun = true)
    public void createDriver() {
        driver = DriverSingleton.getDriverInstance(); // Get driver from singleton
        System.out.println("Driver initialized using DriverSingleton");
    }


    @Test(description = "Check Registration and Login Functionality", priority = 1, enabled = true,
            dataProvider = "userData", dataProviderClass = DP.class, groups = { "Login Flow" })
    public void TestCase01(String UserName, String Password) throws InterruptedException {
        Boolean status;
        RegisterPage register = new RegisterPage(driver);
        register.navigateToRegisterPage();
        status = register.RegisterNewUser(UserName, Password, true);
        assertTrue(status, "Failed to Register");
        // lastGeneratedUsername = register.lastGeneratedUsername;
        lastGeneratedUsername = register.lastGeneratedUsername;
        LoginPage login = new LoginPage(driver);


        login.PerformLogin(lastGeneratedUsername, Password);
        System.out.println(lastGeneratedUsername);
        status = login.VerifyUserLoggedIn();
        assertTrue(status, "Failed to Login");
        login.Logout();
        assertTrue(status, "Failed to Logout");

    }

}


