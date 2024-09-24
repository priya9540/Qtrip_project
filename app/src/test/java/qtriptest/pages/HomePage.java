package qtriptest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/";

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToHomePage() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    @FindBy(xpath = "//a[text()='Home']")
    WebElement navToHomebutton;
    @FindBy(id = "autocomplete")
    WebElement searchbox;
    @FindBy(xpath = "//ul[@id='results']")
    WebElement searchsuggestion;
    @FindBy(xpath = "//ul[@id='results']")
    WebElement NoCityFound;

    public void SearchPresentCity(String CityName) throws InterruptedException {
        searchbox.clear();
        searchbox.sendKeys(CityName);
        System.out.println("Homepage.city"+ CityName);
        Thread.sleep(2000);
        if (searchsuggestion.getText().equalsIgnoreCase(CityName)) {
            searchsuggestion.click();
        }


    }
    public boolean  VerifyNoCityFound(String CityName)throws InterruptedException{
        searchbox.clear();
        searchbox.sendKeys(CityName);
        Thread.sleep(2000);
         
        if (NoCityFound.isDisplayed())
       {
        return true;
       }
        return false;

    }
public void navHomePageButton() throws InterruptedException{
    navToHomebutton.click();
    Thread.sleep(2000);  
}
    


}
