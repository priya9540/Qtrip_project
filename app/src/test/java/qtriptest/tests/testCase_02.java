package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class testCase_02 {
    private WebDriver driver;
    public String lastGeneratedUsername;


    @BeforeSuite(alwaysRun = true)
    public void createDriver() {
        driver = DriverSingleton.getDriverInstance(); // Get driver from singleton
        System.out.println("Driver initialized using DriverSingleton");
    }


    @Test(description = "Verify that searchfilters work fine", priority = 2, enabled = true,
            dataProvider = "userData", dataProviderClass = DP.class,groups = { " Search and Filter flow " })
    public void TestCase02(String CityName, String Category_Filter, String DurationFilter,
            String ExpectedFilteredResults, String ExpectedUnFilteredResults)
            throws InterruptedException {
        Boolean status;
        HomePage home = new HomePage(driver);
        home.navigateToHomePage();
        status = home.VerifyNoCityFound(CityName);
        home.SearchPresentCity(CityName);
        AdventurePage adventure = new AdventurePage(driver);
        adventure.SelectFilters(DurationFilter);
        adventure.SelectCategory(Category_Filter);
        status = adventure.VerifyFilters(ExpectedFilteredResults);
        assertTrue(status, "could not verify no.of filtered results ");
        status = adventure.VerifyAllRecords(ExpectedUnFilteredResults);
        assertTrue(status, "could not verify no. of unfiltered results");
    }
}
