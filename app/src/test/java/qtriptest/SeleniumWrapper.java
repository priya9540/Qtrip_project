package qtriptest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class SeleniumWrapper {



    // Click method
    public static boolean click(WebElement elementToClick, WebDriver driver) {
        try {
            // Check if the element exists and is not null
            if (elementToClick != null && elementToClick.isDisplayed()) {
                // Scroll into view
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                        elementToClick);
                elementToClick.click(); // Click the element
                Thread.sleep(2000);
                return true; // Return true if click was successful
            }
        } catch (Exception e) {
            System.out.println("Click failed: " + e.getMessage());
        }
        return false; // Return false if click failed
    }


    // SendKeys method
    public static boolean sendKeys(WebElement inputBox, String keysToSend) {
        try {
            // Check if the input box exists and is not null
            if (inputBox != null) {
                inputBox.clear(); // Clear existing text
                inputBox.sendKeys(keysToSend); // Send new text
                Thread.sleep(2000);
                return true; // Return true if keys were sent successfully
            }
        } catch (Exception e) {
            System.out.println("SendKeys failed: " + e.getMessage());
        }
        return false; // Return false if sendKeys failed
    }

    // NavigateToURL method
    public static boolean navigate(WebDriver driver, String url) {

        try {
            String currentUrl = driver.getCurrentUrl();
            // Navigate only if the given URL is different from the current URL
            if (!currentUrl.equals(url)) {
                driver.get(url); // Navigate to the new URL
                Thread.sleep(3000);
                return true; // Return true if navigation was successful
            }
        } catch (Exception e) {
            System.out.println("Navigation failed: " + e.getMessage());
        }
        return false; // Return false if navigation failed
    }

    // FindElementWithRetry method
    public static WebElement findElementWithRetry(WebDriver driver, By by, int retyCount) {
        WebElement element;
        for (int i = 0; i < retyCount; i++) {
            try {
                element = driver.findElement(by); // Try to find the element
                return element; // Return the found element
            } catch (NoSuchElementException e) {
                System.out.println("Element not found, retrying... Attempt: " + (i + 1));
                try {
                    Thread.sleep(1000); // Wait before retrying
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        return null; // Return null if the element is not found after retries
    }

    public static String takeScreenshot(String testName, WebDriver driver) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filePath = "screenshots/" + testName + "_" + timestamp + ".png";

        new File("screenshots").mkdirs(); // Create directory if it doesn't exist

        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(filePath));
            System.out.println("Screenshot saved at: " + filePath);
            return filePath; // Return the screenshot path
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
            return null;
        }
    }
}

