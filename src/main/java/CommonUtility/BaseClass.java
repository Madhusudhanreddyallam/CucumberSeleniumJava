package CommonUtility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BaseClass {

	private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();
    private static String browserType = ConfigFileReader.getConfigData("browser");
    private static String url = ConfigFileReader.getConfigData("url");
    
    //Locators
    private static By addCartBtn = By.id("button-cart");
    
    //------------- Browser (Setup & TearDown) --------------------//
    public void browserSetup(){
        WebDriver driver = DriverFactory.doBrowserSetup(browserType);
        threadLocalDriver.set(driver); 
        getDriver().get(url);
    }

    public static WebDriver getDriver(){
        return threadLocalDriver.get();
    }

    public void tearDown(){
        getDriver().quit();
        threadLocalDriver.remove();
    }
    
  //------------- common Methods --------------------//
    public void sendKeys(By locator, String text, WebDriver driver) {
    	waitForElementPresence(driver,locator,10);
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public void click(By locator , WebDriver driver) {
    	
    	WebElement element  = waitForStableElement(driver,locator,40);
    	element.click();     
    }
    
    public static void scrollAndClickElement(WebElement element, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        element.click();
    }
    
    public void clickAddToCartBtn(WebDriver driver) {
    	click(addCartBtn , driver);
    }
    
    public void waitForElementPresence(WebDriver driver, By elementLocator, int timeoutInSeconds) {
    	Duration timeout = Duration.ofSeconds(timeoutInSeconds); 
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
    }
    
    public WebElement waitForStableElement(WebDriver driver, By locator, int timeoutInSeconds)
    { 
    	WebDriverWait wait;
    	WebElement element;
    	Duration timeout = Duration.ofSeconds(timeoutInSeconds); 	
    	try {
    		waitForElementPresence(driver,locator,timeoutInSeconds);
    		element = driver.findElement(locator);
    		return element;
    	}catch(ElementNotInteractableException e) {
    		wait = new WebDriverWait(driver, timeout);
    	    element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    		return element;
    	}
    	catch(NoSuchElementException e) {
    		wait = new WebDriverWait(driver, timeout);
    	    element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    		return element;
    	}
    }

}
