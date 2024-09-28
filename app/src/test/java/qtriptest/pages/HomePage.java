package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import org.openqa.selenium.By;
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
        //PageFactory.initElements(driver, this);
    }

    public void navigateToHomePage() {
        // if (!this.driver.getCurrentUrl().equals(this.url)) {
        //     this.driver.get(this.url);
        SeleniumWrapper.navigate(driver, url);
        
    }

    // @FindBy(xpath = "//a[text()='Home']")
    // WebElement navToHomebutton;
    // @FindBy(id = "autocomplete")
    // WebElement searchbox;
    // @FindBy(xpath = "//ul[@id='results']")
    // WebElement searchsuggestion;
    // @FindBy(xpath = "//ul[@id='results']")
    // WebElement NoCityFound;

    public void SearchPresentCity(String CityName) throws InterruptedException {
        // searchbox.clear();
        // searchbox.sendKeys(CityName);
        // System.out.println("Homepage.city"+ CityName);
        // Thread.sleep(2000);
        // if (searchsuggestion.getText().equalsIgnoreCase(CityName)) {
        //     searchsuggestion.click();
        // }
        WebElement searchbox = driver.findElement(By.id("autocomplete"));
        SeleniumWrapper.sendKeys(searchbox, CityName);
        WebElement searchsuggestion = driver.findElement(By.xpath("//ul[@id='results']"));
        if (searchsuggestion.getText().equalsIgnoreCase(CityName)) {
                //searchsuggestion.click();
                
                SeleniumWrapper.click(searchsuggestion, driver);
            }



    }
    public boolean  VerifyNoCityFound(String CityName)throws InterruptedException{
        // searchbox.clear();
        // searchbox.sendKeys(CityName);
        // Thread.sleep(2000);
        WebElement searchbox = driver.findElement(By.id("autocomplete"));
         SeleniumWrapper.sendKeys(searchbox, CityName);
         WebElement NoCityFound = driver.findElement(By.xpath("//ul[@id='results']"));
        if (NoCityFound.isDisplayed())
       {
        return true;
       }
        return false;

    }
public void navHomePageButton() throws InterruptedException{
    // navToHomebutton.click();
    // Thread.sleep(2000);  
    WebElement navToHomebutton = driver.findElement(By.xpath("//div//a[@href='/']"));
    SeleniumWrapper.click(navToHomebutton, driver);

}
    


}
