package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.SeleniumWrapper;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class testCase_03 {
    private WebDriver driver;
    public String lastGeneratedUsername;
    
	private static ExtentReports report;
    private static ExtentTest test;
    

    // Initialize the driver using the Singleton class
    @BeforeSuite(alwaysRun = true)
    public void createDriver() {
        driver = DriverSingleton.getDriverInstance(); // Get driver from singleton
        report = ReportSingleton.getInstance().getExtent();
        test = report.startTest("TestCase 03");
        System.out.println("Driver initialized using DriverSingleton");
    }

    @Test(description = "Verify that adventure booking and cancellation works fine", priority = 3,
            enabled = true, dataProvider = "data-provider", dataProviderClass = DP.class,
            groups = {"Booking and Cancellation Flow"})
    public void TestCase03(String NewUserName, String Password, String SearchCity,
            String AdventureName, String GuestName, String Date, String count)
            throws InterruptedException {
        boolean status;
        try {
            RegisterPage register = new RegisterPage(driver);
            register.navigateToRegisterPage();
            register.RegisterNewUser(NewUserName, Password, true);
            lastGeneratedUsername = register.lastGeneratedUsername;
            LoginPage login = new LoginPage(driver);
            login.PerformLogin(lastGeneratedUsername, Password);
            status = login.VerifyUserLoggedIn();
            assertTrue(status, "Failed to verify login");
            test.log(LogStatus.PASS, "Successfully logged in");
            AdventurePage adventure = new AdventurePage(driver);
            adventure.SearchCity(SearchCity);
            adventure.SearchAdventure(AdventureName);
            AdventureDetailsPage advdp = new AdventureDetailsPage(driver);
            advdp.GuestDetails(GuestName, Date, count);
            status = advdp.VerifyBookingSuccessful();
            assertTrue(status, "Failed to verify Booking Successful message");
            test.log(LogStatus.PASS, "Successfully verified booking successful message");
            HistoryPage history = new HistoryPage(driver);
            history.GotoReservationPage();
            history.TransactionId();
            history.CancelReservation();
            history.VerifyCancelReservation();
            String screenshotPath = SeleniumWrapper
                    .takeScreenshot("TestCase03 - Booking and Cancellation Flow", driver);
            assertTrue(status, "Failed to verify that bookings are cancelled");

            test.log(LogStatus.PASS, "Successfully verified that bookings are cancelled");
            test.addScreenCapture(screenshotPath);
            login.Logout();
        } catch (Exception e) {
            String screenshotPath = SeleniumWrapper
                    .takeScreenshot("TestCase03 - Booking and Cancellation Flow", driver);
            test.log(LogStatus.FAIL, "Test failed due to exception: " + e.getMessage());
            test.addScreenCapture(screenshotPath);
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
