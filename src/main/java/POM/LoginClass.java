package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import CommonUtility.BaseClass;

public class LoginClass extends BaseClass {
	private WebDriver driver;
	private By userName = By.id("input-email");
	private By pwd = By.id("input-password");
	private By loginBtn = By.xpath("//input[@type='submit' and @value='Login']");
	
	public LoginClass(WebDriver driver) {
		this.driver = driver;
	}

	public void logintoApp(String userNm , String password) {	
		sendKeys(driver, userName, userNm);
		sendKeys(driver, pwd, password);
		click(driver, loginBtn);
		try {Thread.sleep(2000);}catch(Exception e) {}		
	}

}
