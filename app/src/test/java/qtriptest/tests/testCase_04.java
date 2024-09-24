package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class testCase_04 {
    private WebDriver driver;
    public String lastGeneratedUsername;


    // Initialize the driver using the Singleton class
    @BeforeSuite(alwaysRun = true)
    public void createDriver() {
        driver = DriverSingleton.getDriverInstance(); // Get driver from singleton
        System.out.println("Driver initialized using DriverSingleton");
    }

    @Test(description = "Verify that adventure booking and cancellation works fine", priority = 4,
            enabled = true, dataProvider = "userData", dataProviderClass = DP.class,groups = { "Reliability Flow" })
    public void TestCase04(String NewUserName, String Password, String dataset1, String dataset2,
            String dataset3) throws InterruptedException {
        boolean status;

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
        assertTrue(status,"failed to verify that booking is usccessful");
        HomePage home = new HomePage(driver);
        home.navHomePageButton();
        String[] Dataset2 = dataset2.split(";");
        adventure.SearchCity(Dataset2[0]);
        adventure.SearchAdventure(Dataset2[1]);
        advdp.GuestDetails(Dataset2[2], Dataset2[3], Dataset2[4]);
        status = advdp.VerifyBookingSuccessful();
        assertTrue(status,"failed to verify that booking is usccessful");
        home.navHomePageButton();
        String[] Dataset3 = dataset3.split(";");
        adventure.SearchCity(Dataset3[0]);
        adventure.SearchAdventure(Dataset3[1]);
        advdp.GuestDetails(Dataset3[2], Dataset3[3], Dataset3[4]);
        status = advdp.VerifyBookingSuccessful();
        assertTrue(status,"failed to verify that booking is usccessful");
        HistoryPage history = new HistoryPage(driver);
        history.GotoReservationPage();
       status= history.VerifyAllBookings();
       assertTrue(status,"failed to verify all bookings");
        login.Logout();
    }

}
