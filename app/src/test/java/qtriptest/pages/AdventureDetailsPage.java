package qtriptest.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdventureDetailsPage {
    private WebDriver driver;

    public AdventureDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='name']")
    WebElement NameBox;
    @FindBy(xpath = "//input[@name='date']")
    WebElement date;
    @FindBy(xpath = "//input[@name='person']")
    WebElement CountBox;
    @FindBy(xpath = "//button[@class='reserve-button']")
    WebElement ReserveButon;
    @FindBy(xpath = "//div[@class='alert alert-success']")
    WebElement SuccessMessageElement;


    public void GuestDetails(String Name, String Date, String PersonCount)
            throws InterruptedException {
        NameBox.sendKeys(Name);
        Thread.sleep(2000);
        date.sendKeys(Date);
        Thread.sleep(2000);
        CountBox.clear();
        CountBox.sendKeys(PersonCount);
        Thread.sleep(2000);
        ReserveButon.click();
        Thread.sleep(2000);
    }

    public boolean VerifyBookingSuccessful() throws InterruptedException {
        if (SuccessMessageElement.isDisplayed()) {
            System.out.println("Reservation Successful");
            return true;
        } else {
            System.out.println("Reservation Unsuccessful");
            return false;
        }
    }



}
