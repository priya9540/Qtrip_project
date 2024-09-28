
package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HistoryPage {
    private WebDriver driver;

    public HistoryPage(WebDriver driver) {
        this.driver = driver;
        // PageFactory.initElements(driver, this);
    }

    // @FindBy(xpath = "//div/a[@href='../reservations/']")
    // WebElement ReservationPageLink;
    // @FindBy(xpath = "//button[@class='cancel-button']")
    // WebElement CancelButton;
    // // @FindBy(xpath = "//table/tbody/tr/th[1]")
    // @FindBy(xpath = "//tbody//tr//th")
    // List<WebElement> TransactionIdElement;
    // @FindBy(xpath = "//div[@class='alert alert-dark']")
    // WebElement NoReservationMsg;



    public void TransactionId() throws InterruptedException {
        List<WebElement> TransactionIdElement =
                driver.findElements(By.xpath("//tbody//tr//th"));
        for (int i = 0; i < TransactionIdElement.size(); i++) {
            WebElement Id = TransactionIdElement.get(i);
            String IdText = Id.getText();
            System.out.println("Id " + IdText);

        }
    }

    public boolean VerifyAllBookings() {
        List<WebElement> TransactionIdElement =
                driver.findElements(By.xpath("//tbody//tr//th"));
        for (int i = 0; i < TransactionIdElement.size(); i++) {
            if (!TransactionIdElement.get(i).isDisplayed()) {
                System.out.println("Bookings not getting Dispalyed");
                return false;
            }

        }
        System.out.println("Bookings are getting dispalyed");
        return true;
    }


    public void GotoReservationPage() throws InterruptedException {
        WebElement ReservationPageLink = SeleniumWrapper.findElementWithRetry(driver,
                By.xpath("//div/a[@href='../reservations/']"), 2);
        // ReservationPageLink.click();
        // Thread.sleep(2000);
        SeleniumWrapper.click(ReservationPageLink, driver);
    }

    public void CancelReservation() throws InterruptedException {
        WebElement CancelButton = SeleniumWrapper.findElementWithRetry(driver,
                By.xpath("//button[@class='cancel-button']"), 2);
        // CancelButton.click();
        // Thread.sleep(2000);
        SeleniumWrapper.click(CancelButton, driver);
    }

    public boolean VerifyCancelReservation() throws InterruptedException {
        List<WebElement> TransactionIdElement =
        driver.findElements(By.xpath("//tbody//tr//th"));
        for (int i = 0; i < TransactionIdElement.size(); i++) {
            if (TransactionIdElement.get(i).isDisplayed()) {
                return false;
            }
        }
        return true;

    }
}
