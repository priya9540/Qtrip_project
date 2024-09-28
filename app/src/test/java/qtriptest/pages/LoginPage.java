package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class LoginPage {
    private WebDriver driver;
    private String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/login/";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
       //  PageFactory.initElements(driver, this);
    }

    public void navigateToLoginPage() {
        // if (!this.driver.getCurrentUrl().equals(this.url)) {
        // this.driver.get(this.url);
        // }
        SeleniumWrapper.navigate(driver, url);
    }

    // @FindBy(xpath = "//input[@id='floatingInput']")
    
    // @FindBy(xpath = "//input[@id='floatingPassword']")
    
    // @FindBy(xpath = "//button[@class='btn btn-primary btn-login']")
    // WebElement loginToQtripButton = SeleniumWrapper.findElementWithRetry(driver,
    //         By.xpath("//button[@class='btn btn-primary btn-login']"), 3);
    // @FindBy(xpath = "//div[@class='nav-link login register']")
   


    public void PerformLogin(String Username, String Password) throws InterruptedException {
        // usernameTextBox.sendKeys(Username);
        // System.out.println(Username);
        // Thread.sleep(1000);
        // passwordTextBox.sendKeys(Password);
        // System.out.println(Password);
        // loginToQtripButton.click();
        // Thread.sleep(5000);
        WebElement usernameTextBox = SeleniumWrapper.findElementWithRetry(driver,
            By.xpath("//input[@id='floatingInput']"), 2);
        SeleniumWrapper.sendKeys(usernameTextBox, Username);
        WebElement passwordTextBox = SeleniumWrapper.findElementWithRetry(driver,
            By.xpath("//input[@id='floatingPassword']"), 2);
        SeleniumWrapper.sendKeys(passwordTextBox, Password);
        WebElement loginToQtripButton= driver.findElement(By.xpath("//button[@class='btn btn-primary btn-login']"));
        SeleniumWrapper.click(loginToQtripButton, driver);


    }

    public Boolean VerifyUserLoggedIn() {
        WebElement logoutButton = SeleniumWrapper.findElementWithRetry(driver,
        By.xpath("//div[@onclick='Logout()']"), 2);
        if (logoutButton.isDisplayed()) {
            return true;
        } else {
            return false;
        }

    }

    public boolean Logout() {
        // logoutButton.click();
        
         WebElement logoutButton = SeleniumWrapper.findElementWithRetry(driver,
           By.xpath("//div[@onclick='Logout()']"), 1);
           SeleniumWrapper.click(logoutButton, driver);

        if (driver.getCurrentUrl().equals("https://qtripdynamic-qa-frontend.vercel.app/")) {
            return true;
        }
        return false;


    }


}
