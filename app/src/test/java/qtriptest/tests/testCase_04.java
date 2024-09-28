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

public class testCase_04 {
    private WebDriver driver;
    public String lastGeneratedUsername;
    private ExtentReports report;
    private ExtentTest test;



    // Initialize the driver using the Singleton class
    @BeforeSuite(alwaysRun = true)
    public void createDriver() {
        driver = DriverSingleton.getDriverInstance(); // Get driver from singleton
        report = ReportSingleton.getInstance().getExtent();
        test = report.startTest("TestCase 04");
        System.out.println("Driver initialized using DriverSingleton");
    }

    @Test(description = "Verify that adventure booking and cancellation works fine", priority = 4,
            enabled = true, dataProvider = "data-provider", dataProviderClass = DP.class,
            groups = {"Reliability Flow"})
    public void TestCase04(String NewUserName, String Password, String dataset1, String dataset2,
            String dataset3) throws InterruptedException {
        boolean status;
        try {
            RegisterPage register = new RegisterPage(driver);
            register.navigateToRegisterPage();
            register.RegisterNewUser(NewUserName, Password, true);
            lastGeneratedUsername = register.lastGeneratedUsername;
            LoginPage login = new LoginPage(driver);
            login.PerformLogin(lastGeneratedUsername, Password);
            status = login.VerifyUserLoggedIn();
            AdventurePage adventure = new AdventurePage(driver);
            String[] Dataset1 = dataset1.split(";");
            adventure.SearchCity(Dataset1[0]);
            adventure.SearchAdventure(Dataset1[1]);
            AdventureDetailsPage advdp = new AdventureDetailsPage(driver);

            advdp.GuestDetails(Dataset1[2], Dataset1[3], Dataset1[4]);
            status = advdp.VerifyBookingSuccessful();
            assertTrue(status, "failed to verify that booking is usccessful");
            test.log(LogStatus.PASS, " Successfully verified booking successful");
            HomePage home = new HomePage(driver);
            home.navHomePageButton();
            String[] Dataset2 = dataset2.split(";");
            adventure.SearchCity(Dataset2[0]);
            adventure.SearchAdventure(Dataset2[1]);
            advdp.GuestDetails(Dataset2[2], Dataset2[3], Dataset2[4]);
            status = advdp.VerifyBookingSuccessful();
            assertTrue(status, "failed to verify that booking is sccessful");
            test.log(LogStatus.PASS, " Successfully verified booking successful");
            home.navHomePageButton();
            String[] Dataset3 = dataset3.split(";");
            adventure.SearchCity(Dataset3[0]);
            adventure.SearchAdventure(Dataset3[1]);
            advdp.GuestDetails(Dataset3[2], Dataset3[3], Dataset3[4]);
            status = advdp.VerifyBookingSuccessful();
            assertTrue(status, "failed to verify that booking is usccessful");
            test.log(LogStatus.PASS, " Successfully verified booking successful");
            HistoryPage history = new HistoryPage(driver);
            history.GotoReservationPage();
            status = history.VerifyAllBookings();
            String screenshotPath =
                    SeleniumWrapper.takeScreenshot("TestCase04 - Reliability Flow", driver);
            assertTrue(status, "failed to verify all bookings");

            test.log(LogStatus.PASS, " Successfully verified all bookings successful");
            test.addScreenCapture(screenshotPath);
            login.Logout();
        } catch (Exception e) {
            String screenshotPath =
                    SeleniumWrapper.takeScreenshot("TestCase04 - Reliability Flow", driver);

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


