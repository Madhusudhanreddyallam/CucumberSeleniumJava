package CommonUtility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;


public class BaseClass {
	//if you see any issue with Driver or screenshot set these 3 method as static getDriver, setScenario, getScenario.
	private static ThreadLocal<Scenario> scenarioThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();
	private static String browserType = ConfigFileReader.getConfigData("browser");
	private static String url = ConfigFileReader.getConfigData("url");

	//Locators
	private static By addCartBtn = By.id("button-cart");	
	public void clickAddToCartBtn(WebDriver driver) {
		click(addCartBtn , driver);
	}

	//------------- Browser (Setup & TearDown) --------------------//
	public void browserSetup(){
		WebDriver driver = DriverFactory.doBrowserSetup(browserType);
		threadLocalDriver.set(driver); 
		getDriver().get(url);
	}

	public WebDriver getDriver(){
		return threadLocalDriver.get();
	}

	public void tearDown(){
		getDriver().quit();
		scenarioThreadLocal.remove();
		threadLocalDriver.remove();
	}

	//------------- Scenario initialization --------------------//
	public void setScenario(Scenario scenario) {
		scenarioThreadLocal.set(scenario);
	}

	public Scenario getScenario() {
		return scenarioThreadLocal.get();
	}

	//------------- common Methods --------------------//

	public void sendKeys(By locator, String text, WebDriver driver) {
		WebElement element = waitForElementPresence(driver,locator,15);
		element.clear();
		element.sendKeys(text);
		takeScreenshot("Entered Text(SendKeys)" , driver);
	}

	public void click(By locator , WebDriver driver) {

		WebElement element  = waitForElementPresence(driver,locator,15);
		element.click();
		takeScreenshot("Clicked an element", driver );
	}

	public WebElement waitForElementPresence(WebDriver driver, By elementLocator, int timeoutInSeconds) {
		Duration timeout = Duration.ofSeconds(timeoutInSeconds); 
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
		}catch(TimeoutException e) {
			e.printStackTrace();
			System.out.println("Verify whether the xpath(or) the locator has changed");
			return null;
		}	
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

	public void takeScreenshot(String msg, WebDriver driver) {
		Scenario scenario = getScenario();
		try {
			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", msg );
		} catch (Exception e) {
			e.printStackTrace();
		} 
	} 

}
