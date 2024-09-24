package qtriptest.pages;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.Runtime.Timestamp;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {
    
    private WebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/";
    public  String lastGeneratedUsername = "";
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void navigateToRegisterPage() {
        if (!driver.getCurrentUrl().equals(this.url)) {
            driver.get(this.url);
        }
    }

    @FindBy(xpath = "//a[@href='./pages/register/']")
    WebElement RegistrationNavLink;
    @FindBy(xpath = "//input[@id='floatingInput']")
    WebElement UserNameTextBox;
    @FindBy(xpath = "//input[@placeholder='Type to create account password']")
    WebElement PasswordTextBox;
    @FindBy(xpath = "//input[@name='confirmpassword']")
    WebElement ConfirmPasswordTextBox;
    @FindBy(xpath = "//button[text()='Register Now']")
    WebElement RegisterNowButton;



    public boolean RegisterNewUser(String UserName, String Password, boolean DynamicUserName) throws InterruptedException {
        RegistrationNavLink.click();
        Thread.sleep(5000);
        String test_data_username;
        
        if (DynamicUserName){
            // Concatenate the timestamp to string to form unique timestamp
            test_data_username = UUID.randomUUID().toString() + UserName;
        }
            else{
            test_data_username = UserName;
            }

        // Type the generated username in the username field
        UserNameTextBox.sendKeys(test_data_username);
        PasswordTextBox.sendKeys(Password);
        ConfirmPasswordTextBox.sendKeys(Password);
        RegisterNowButton.click();
       System.out.println(test_data_username);
       System.out.println(Password);
       

        // try {
        //     WebDriverWait wait = new WebDriverWait(driver, 30);
        //     wait.until(ExpectedConditions.or(
        //             ExpectedConditions.urlToBe("")));
        // } catch (TimeoutException e) {
        //     return false;
        // }
        Thread.sleep(5000);

        this.lastGeneratedUsername = test_data_username;
        System.out.println(lastGeneratedUsername);
        return this.driver.getCurrentUrl().contains("/login");

    }
    
}
