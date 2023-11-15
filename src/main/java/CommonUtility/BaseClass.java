package CommonUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;


public class BaseClass {
	
	private static Scenario scenario;
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
		WebElement element = waitForStableElement(driver,locator,40);
		element.clear();
		element.sendKeys(text);
		takeScreenshot("Entered Text(SendKeys)" );
	}

	public void click(By locator , WebDriver driver) {

		WebElement element  = waitForStableElement(driver,locator,40);
		element.click();
		takeScreenshot("Clicked an element" );
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

	public static void captureScreenshot(String screenshotName) {

		try {
			TakesScreenshot ts = (TakesScreenshot) getDriver();
			File source = ts.getScreenshotAs(OutputType.FILE);
			String destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png";
			File finalDestination = new File(destination);
			FileUtils.copyFile(source, finalDestination);
			System.out.println("Screenshot captured: " + screenshotName);
		} catch (IOException e) {
			System.out.println("Exception while taking screenshot: " + e.getMessage());
		}
	}
	
	public static void setScenario(Scenario scenario) {
        BaseClass.scenario = scenario;
    }

	public static void takeScreenshot(String msg) {
		try {
			final byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", msg);
		} catch (Exception e) {
			//e.printStackTrace();
		} 
	} 
	

}
