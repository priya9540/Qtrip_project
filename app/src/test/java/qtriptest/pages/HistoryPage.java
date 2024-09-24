
package qtriptest.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HistoryPage {
    private WebDriver driver;

    public HistoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div/a[@href='../reservations/']")
    WebElement ReservationPageLink;
    @FindBy(xpath = "//button[@class='cancel-button']")
    WebElement CancelButton;
    // @FindBy(xpath = "//table/tbody/tr/th[1]")
    @FindBy(xpath = "//tbody//tr//th")
    List<WebElement> TransactionIdElement;
    @FindBy(xpath = "//div[@class='alert alert-dark']")
    WebElement NoReservationMsg;



    public void TransactionId() throws InterruptedException {

        for (int i = 0; i < TransactionIdElement.size(); i++) {
            WebElement Id = TransactionIdElement.get(i);
            String IdText = Id.getText();
            System.out.println("Id " + IdText);

        }
    }

    public boolean VerifyAllBookings() {
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
        ReservationPageLink.click();
        Thread.sleep(2000);
    }

    public void CancelReservation() throws InterruptedException {

        CancelButton.click();
        Thread.sleep(2000);
    }

    public boolean VerifyCancelReservation() throws InterruptedException {
        for (int i = 0; i < TransactionIdElement.size(); i++) {
            if (TransactionIdElement.get(i).isDisplayed()) {
                return false;
            }
        }
        return true;

    }
}
