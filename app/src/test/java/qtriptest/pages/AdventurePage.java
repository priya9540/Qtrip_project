package qtriptest.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AdventurePage {
    private WebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/";

    public AdventurePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToAdventurePage() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }


    @FindBy(xpath = "//select[@id='duration-select']")
    WebElement filters;
    @FindBy(xpath = "//select[@id='category-select']")
    WebElement category;
    @FindBy(xpath = "//div[@onClick='clearDuration(event)']")
    WebElement ClearFilters;
    @FindBy(xpath = "//div[@onClick='clearCategory(event)']")
    WebElement ClearCategory;
    @FindBy(xpath = "//div[@class='category-banner']")
    List<WebElement> CategoryBanner;
    @FindBy(xpath = "//div[@class='col-6 col-lg-3 mb-4']")
    List<WebElement> AllRecords;
   // @FindBy(xpath="//div/h5[text()]")
    @FindBy(xpath="//div[@class='tile-text text-center']/h5")
    List<WebElement> cities;
    @FindBy(xpath="//div[@class='activity-card-text text-md-center w-100 mt-3']")
    List<WebElement> adventures;

    public void SelectFilters(String DurationFilter) throws InterruptedException {
        ClearFilters.click();
        Thread.sleep(1000);
        filters.click();
        Thread.sleep(2000);
        Select dropdown1 = new Select(filters);
        dropdown1.selectByVisibleText(DurationFilter);
        Thread.sleep(2000);
       // System.out.println("DurationFilter" + DurationFilter);

    }

    public void SelectCategory(String Category_Filter) throws InterruptedException {
        ClearCategory.click();
        Thread.sleep(1000);
        category.click();
        Thread.sleep(2000);
        Select dropdown2 = new Select(category);
        dropdown2.selectByVisibleText(Category_Filter);
        Thread.sleep(2000);
        //System.out.println("Category_Filter"+ Category_Filter);
    }

    public boolean VerifyFilters(String ExpectedFilteredResults) throws InterruptedException {


        // for (WebElement banner : CategoryBanner) {
        // if (!banner.getText().contains(CategoryValue)) {
        // return false;
        // }
        // }
        // return true;
        //System.out.println("ExpectedFilteredResults" + ExpectedFilteredResults);
        int FilterdExpectedResults= Integer.valueOf(ExpectedFilteredResults);
        if (CategoryBanner.size() != FilterdExpectedResults) {
            return false;
        }
        //System.out.println("CategoryBanner.size()" + CategoryBanner.size());
        return true;
    }


    public boolean VerifyAllRecords(String ExpectedUnFilteredResults) throws InterruptedException {
        ClearFilters.click();
        Thread.sleep(2000);
        ClearCategory.click();
        Thread.sleep(2000);
        // for(WebElement record:AllRecords){
        // if(!record.isDisplayed()){
        // return false;
        // }
        // }
        //System.out.println("ExpectedUnFilteredResults" + ExpectedUnFilteredResults);
        int UnfilterdExpectedResults= Integer.valueOf(ExpectedUnFilteredResults);
        if (AllRecords.size() != UnfilterdExpectedResults) {
            return false;
        }
        //System.out.println("AllRecords.size()"+ AllRecords.size());
        return true;
    }
public void SearchCity(String SearchCity ) throws InterruptedException{
    Boolean cityFound=false;
    JavascriptExecutor js = (JavascriptExecutor) driver;
    for (WebElement cityElement : cities) {
        String cityText = cityElement.getText();
        if (cityText.equalsIgnoreCase(SearchCity)) {
            // cityElement.click();   // Click on the city element
            // Wait for the action to complete (could be replaced with WebDriverWait)
            
           
    js.executeScript("arguments[0].scrollIntoView(true);", cityElement);
    Thread.sleep(3000);    
    js.executeScript("arguments[0].click();", cityElement);
    Thread.sleep(2000);
    cityFound = true;
            break;
        }
    }

    if (!cityFound) {
        System.out.println("City " + SearchCity + " not found in the list.");
    }
}
    public void SearchAdventure(String AdventureName)throws InterruptedException{
        boolean adventureFound = false;
    
    for (int i = 0; i < adventures.size(); i++) {
        WebElement adventureElement = adventures.get(i);
        String adventureText = adventureElement.getText();
        
        if (adventureText.contains(AdventureName)) {
            adventureElement.click();   // Click on the matching adventure element
            Thread.sleep(5000);         // Wait for any action to complete (e.g., page load)
            adventureFound = true;
            break;                      // Exit the loop once the adventure is found and clicked
        }
    }

    if (!adventureFound) {
        System.out.println("Adventure " + AdventureName + " not found.");
    }
}

    }



    

