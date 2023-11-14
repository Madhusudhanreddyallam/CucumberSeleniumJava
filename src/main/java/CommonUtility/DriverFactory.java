package CommonUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

	public static WebDriver doBrowserSetup(String browserName){
		WebDriver driver = null;
		if (browserName.equalsIgnoreCase("chrome")){
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		return driver;
	}
}