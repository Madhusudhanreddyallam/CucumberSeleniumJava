package CommonUtility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
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
		click(driver,addCartBtn);
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



	public WebElement waitForElementPresence(WebDriver driver, By locator, int timeoutInSeconds) {
		Duration timeout = Duration.ofSeconds(timeoutInSeconds); 
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		try {
			WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return ele;
		}catch(TimeoutException e) {	
			throw e;
		}	
	}

	public void click(WebDriver driver, By locator ) {
		int attempts = 0;
		while(attempts <= 2) {
			try {
				WebElement element  = waitForElementPresence(driver,locator,10);
				element.click();
				takeScreenshot("Clicked an element", driver );
				break;
			} catch(StaleElementReferenceException e) {
				if (attempts==2) {
					takeScreenshot("Exception occured on this screen", driver );
					throw e;
				}
				attempts++;				
			} catch (Exception e) {
				takeScreenshot("Exception occured on this screen", driver );
				throw e;
			}
		}
	}

	public void sendKeys(WebDriver driver, By locator, String text) {
		int attempts = 0;
		while(attempts <= 2) {
			try {
				WebElement element  = waitForElementPresence(driver,locator,10);
				element.clear();
				element.sendKeys(text);
				takeScreenshot("Entered Text(SendKeys)" , driver);
				break;
			}catch(StaleElementReferenceException e) {
				if (attempts==2) {
					takeScreenshot("Exception occured on this screen", driver );
					throw e;
				}
				attempts++;
			}catch (Exception e) {
				takeScreenshot("Exception occured on this screen", driver );
				throw e;
			}
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
