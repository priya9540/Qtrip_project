package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.SeleniumWrapper;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.net.URL;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import static org.testng.Assert.*;

public class testCase_01 {
    private WebDriver driver; // Use WebDriver, not RemoteWebDriver (generalized)
    public String lastGeneratedUsername;
    
	private static ExtentReports report;
    private static ExtentTest test;
    // private ExtentTest test;
    // Initialize the driver using the Singleton class
    @BeforeSuite(alwaysRun = true)
    public void createDriver() {
        driver = DriverSingleton.getDriverInstance(); // Get driver from singleton
        report = ReportSingleton.getInstance().getExtent();
        test = report.startTest("TestCase 01");
        System.out.println("Driver initialized using DriverSingleton");
    }


    @Test(description = "Check Registration and Login Functionality", priority = 1, enabled = true,
            dataProvider = "data-provider", dataProviderClass = DP.class, groups = {"Login Flow"})
    public void TestCase01(String UserName, String Password) throws InterruptedException {
        Boolean status;

        try {
            RegisterPage register = new RegisterPage(driver);
            register.navigateToRegisterPage();
            test.log(LogStatus.INFO, "Navigated to Register Page");
            status = register.RegisterNewUser(UserName, Password, true);
            assertTrue(status, "Failed to Register");


            // String screenshotPath = SeleniumWrapper.takeScreenshot("testValidLogin", driver);

            test.log(LogStatus.PASS, "User registered successfully");
            // lastGeneratedUsername = register.lastGeneratedUsername;
            lastGeneratedUsername = register.lastGeneratedUsername;
            LoginPage login = new LoginPage(driver);


            login.PerformLogin(lastGeneratedUsername, Password);
            System.out.println(lastGeneratedUsername);
            status = login.VerifyUserLoggedIn();
            String screenshotPath =
                    SeleniumWrapper.takeScreenshot("TestCase01 - Registration and Login", driver);
            assertTrue(status, "Failed to Login");

            // test.log(LogStatus.PASS, "User login successful");
            // test.addScreencast(screenshotPath);
            login.Logout();
            assertTrue(status, "Failed to Logout");
            // String screenshotPath = SeleniumWrapper.takeScreenshot("testValidLogin", driver);
            test.log(LogStatus.PASS, "User logged out successfully",
                    test.addScreenCapture(screenshotPath));
        } catch (Exception e) {
            String screenshotPath =
                    SeleniumWrapper.takeScreenshot("TestCase01 - Registration and Login", driver);
            test.log(LogStatus.FAIL, "Test failed due to exception: " + e.getMessage(),
                    test.addScreenCapture(screenshotPath));
            throw e;
        } finally {
            // End the test in ExtentReports
            report.endTest(test);
        }
    }

    @AfterSuite
    public void tearDownSuite() {
        // Flush the report to write all logs and results to the output file
        report.flush();
        System.out.println("ExtentReports flushed and finalized");
    }
}


