package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import CommonUtility.BaseClass;

public class Phones extends BaseClass{
	private WebDriver driver;
	private static By Phones_menu = By.xpath("//a[contains(text(),'Phones')]");
	private static By iphone = By.xpath("//div[contains(@class,'product')]//a[contains(text(),'iPhone')]");
	
	public Phones(WebDriver driver) {
		this.driver = driver;
	}
	public void navigateToPhonesScreen() {
		click(driver, Phones_menu);	
	}
	public void addiphoneToCart() {
		click(driver, iphone);
		clickAddToCartBtn(driver);
	}

}
