package qtriptest.tests;

import qtriptest.DP;
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
    static RemoteWebDriver driver;
     public  String lastGeneratedUsername;

    @BeforeSuite(alwaysRun = true)
    public static void createDriver() throws MalformedURLException {
        // Launch Browser using Zalenium
        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
        System.out.println("createDriver()");
        driver.manage().window().maximize();
    }
    @Test(enabled = true,dataProvider="userData", dataProviderClass = DP.class)
    public void TestCase01(String UserName,String Password) throws InterruptedException {
        Boolean status;
        RegisterPage register = new RegisterPage(driver);
        register.navigateToRegisterPage();
      status=  register.RegisterNewUser(UserName, Password, true);
       assertTrue(status, "Failed to Register");
      // lastGeneratedUsername = register.lastGeneratedUsername;
      lastGeneratedUsername = register.lastGeneratedUsername;
        LoginPage login = new LoginPage(driver);
        

        login.PerformLogin(lastGeneratedUsername, Password);
        System.out.println(lastGeneratedUsername);
       status= login.VerifyUserLoggedIn();
       assertTrue(status, "Failed to Login");
        login.Logout();
         assertTrue(status, "Failed to Logout");

    }

}


