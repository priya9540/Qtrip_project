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

public class testCase_03 {
    private WebDriver driver;
    public String lastGeneratedUsername;


    // Initialize the driver using the Singleton class
    @BeforeSuite(alwaysRun = true)
    public void createDriver() {
        driver = DriverSingleton.getDriverInstance(); // Get driver from singleton
        System.out.println("Driver initialized using DriverSingleton");
    }

    @Test(description = "Verify that adventure booking and cancellation works fine", priority = 3,
            enabled = true, dataProvider = "userData", dataProviderClass = DP.class)
    public void TestCase03(String NewUserName, String Password, String SearchCity,
            String AdventureName, String GuestName, String Date, String count)
            throws InterruptedException {
        boolean status;

        RegisterPage register = new RegisterPage(driver);
        register.navigateToRegisterPage();
        register.RegisterNewUser(NewUserName, Password, true);
        lastGeneratedUsername = register.lastGeneratedUsername;
        LoginPage login = new LoginPage(driver);
        login.PerformLogin(lastGeneratedUsername, Password);
        status = login.VerifyUserLoggedIn();
        assertTrue(status, "Failed to verify login");
        AdventurePage adventure = new AdventurePage(driver);
        adventure.SearchCity(SearchCity);
        adventure.SearchAdventure(AdventureName);
        AdventureDetailsPage advdp = new AdventureDetailsPage(driver);
        advdp.GuestDetails(GuestName, Date, count);
        status = advdp.VerifyBookingSuccessful();
        assertTrue(status, "Failed to verify Booking Successful message");
        HistoryPage history = new HistoryPage(driver);
        history.GotoReservationPage();
        history.TransactionId();
        history.CancelReservation();
        history.VerifyCancelReservation();
        assertTrue(status, "Failed to verify that bookings are cancelled");
        login.Logout();


    }

}
