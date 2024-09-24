package qtriptest.pages;

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
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/login/";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToLoginPage() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    @FindBy(xpath = "//input[@id='floatingInput']")
    WebElement usernameTextBox;
    @FindBy(xpath = "//input[@id='floatingPassword']")
    WebElement passwordTextBox;
    @FindBy(xpath = "//button[@class='btn btn-primary btn-login']")
    WebElement loginToQtripButton;
    @FindBy(xpath = "//div[@class='nav-link login register']")
    WebElement logoutButton;


    public void PerformLogin(String Username, String Password) throws InterruptedException {
        usernameTextBox.sendKeys(Username);
        System.out.println(Username);
        Thread.sleep(1000);
        passwordTextBox.sendKeys(Password);
        System.out.println(Password);
        loginToQtripButton.click();
        Thread.sleep(5000);

        // FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
        //         .withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofMillis(600))
        //         .ignoring(NoSuchElementException.class);
        // wait.until(ExpectedConditions.invisibilityOf(loginToQtripButton));

        // synchronized (driver) {
        //     driver.wait(2000);
        // }

        
    }

    public Boolean VerifyUserLoggedIn() {
       
            if (logoutButton.isDisplayed()){
                return true;
            }
            else{
                return false;
            }

    }

    public Boolean Logout() {
        logoutButton.click();
        
        if (driver.getCurrentUrl().equals("https://qtripdynamic-qa-frontend.vercel.app/")) {
            return true;
        } else {
            return false;
        }

    }


}
