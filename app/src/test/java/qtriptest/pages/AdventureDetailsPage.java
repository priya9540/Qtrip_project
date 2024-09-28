package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdventureDetailsPage {
    private WebDriver driver;

    public AdventureDetailsPage(WebDriver driver) {
        this.driver = driver;
        // PageFactory.initElements(driver, this);
    }

    // @FindBy(xpath = "//input[@name='name']")
    // WebElement NameBox;
    // @FindBy(xpath = "//input[@name='date']")
    // WebElement date;
    // @FindBy(xpath = "//input[@name='person']")
    // WebElement CountBox;
    // @FindBy(xpath = "//button[@class='reserve-button']")
    // WebElement ReserveButon;
    // @FindBy(xpath = "//div[@class='alert alert-success']")
    // WebElement SuccessMessageElement;


    public void GuestDetails(String Name, String Date, String PersonCount)
            throws InterruptedException {
        WebElement NameBox = driver.findElement(By.xpath("//input[@name='name']"));
        // NameBox.sendKeys(Name);
        // Thread.sleep(2000);
        SeleniumWrapper.sendKeys(NameBox, Name);
        WebElement date = driver.findElement(By.xpath("//input[@name='date']"));
        // date.sendKeys(Date);
        // Thread.sleep(2000);
        SeleniumWrapper.sendKeys(date, Date);
        WebElement CountBox = driver.findElement(By.xpath("//input[@name='person']"));
        // CountBox.clear();
        // CountBox.sendKeys(PersonCount);
        // Thread.sleep(2000);
        SeleniumWrapper.sendKeys(CountBox, PersonCount);
        WebElement ReserveButon = driver.findElement(By.xpath("//button[@class='reserve-button']"));
        // ReserveButon.click();
        // Thread.sleep(2000);
        SeleniumWrapper.click(ReserveButon, driver);
    }

    public boolean VerifyBookingSuccessful() throws InterruptedException {
        WebElement SuccessMessageElement =
                driver.findElement(By.xpath("//div[@class='alert alert-success']"));
        if (SuccessMessageElement.isDisplayed()) {
            System.out.println("Reservation Successful");
            return true;
        } else {
            System.out.println("Reservation Unsuccessful");
            return false;
        }
    }



}
