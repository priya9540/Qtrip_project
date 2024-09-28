package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.SeleniumWrapper;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
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
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class testCase_02 {
    private WebDriver driver;
    public String lastGeneratedUsername;
    private ExtentReports report;
    private ExtentTest test;


    @BeforeSuite(alwaysRun = true)
    public void createDriver() {

        driver = DriverSingleton.getDriverInstance(); // Get driver from singleton
        report = ReportSingleton.getInstance().getExtent();
        test = report.startTest("TestCase 02");
        System.out.println("Driver initialized using DriverSingleton");
    }


    @Test(description = "Verify that searchfilters work fine", priority = 2, enabled = true,
            dataProvider = "data-provider", dataProviderClass = DP.class,
            groups = {" Search and Filter flow "})
    public void TestCase02(String CityName, String Category_Filter, String DurationFilter,
            String ExpectedFilteredResults, String ExpectedUnFilteredResults)
            throws InterruptedException {
        Boolean status;
        try {
            HomePage home = new HomePage(driver);
            home.navigateToHomePage();
            home.VerifyNoCityFound(CityName);
            home.SearchPresentCity(CityName);
            AdventurePage adventure = new AdventurePage(driver);
            adventure.SelectFilters(DurationFilter);
            adventure.SelectCategory(Category_Filter);
            status = adventure.VerifyFilters(ExpectedFilteredResults);

            assertTrue(status, "could not verify no.of filtered results ");
            test.log(LogStatus.PASS, "Successfully verified no. of filters");
            status = adventure.VerifyAllRecords(ExpectedUnFilteredResults);
            String screenshotPath = SeleniumWrapper
                    .takeScreenshot("TestCase02 - Verify that searchfilters work fine", driver);
            assertTrue(status, "could not verify no. of unfiltered results");

            test.log(LogStatus.PASS, "Successfully verified no. of unfiltered results");
            test.addScreenCapture(screenshotPath);
        } catch (Exception e) {
            String screenshotPath = SeleniumWrapper
                    .takeScreenshot("TestCase02 - Verify that searchfilters work fine", driver);
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
