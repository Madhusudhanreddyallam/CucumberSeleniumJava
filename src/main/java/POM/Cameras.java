package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import CommonUtility.BaseClass;

public class Cameras extends BaseClass {
	private WebDriver driver;
	private static By cameras_menu = By.xpath("//a[contains(text(),'Cameras') and not(contains(text(),'Web'))]");
	private static By nikon = By.xpath("//div[contains(@class,'product')]//a[contains(text(),'Nikon')]");
	
	public Cameras (WebDriver driver) {
		this.driver = driver;
	}
	
	public void navigateToCamerasScreen() {
		click(cameras_menu ,driver);	
	}
	public void addNikonToCart() {
		click(nikon,driver);
		clickAddToCartBtn(driver);
	}

}
