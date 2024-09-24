package qtriptest;

import java.net.MalformedURLException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

public class DriverSingleton {


    private static WebDriver driver;

    // Private constructor to prevent instantiation
    private DriverSingleton() {}

    // Public method to provide access to the instance
    public static WebDriver getDriverInstance() {
        if (driver == null) {
            synchronized (DriverSingleton.class) {
                if (driver == null) {
                    // Initialize Remote WebDriver here
                    try {

                        final DesiredCapabilities capabilities = new DesiredCapabilities();
                        capabilities.setBrowserName(BrowserType.CHROME);
                        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"),
                                capabilities);
                        System.out.println("createDriver()");
                        driver.manage().window().maximize();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                        throw new RuntimeException("Failed to initialize Remote WebDriver");
                    }
                }
            }
        }
        return driver;
    }

    // Optional method to quit the driver
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

