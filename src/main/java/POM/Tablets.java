package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import CommonUtility.BaseClass;

public class Tablets extends BaseClass {
	private WebDriver driver;
	
	private static By tablets_menu = By.xpath("//a[contains(text(),'Tablets')]");
	private static By galaxy_tab = By.xpath("//div[contains(@class,'product')]//a[contains(text(),'Samsung Galaxy Tab')]");
	
	public Tablets(WebDriver driver) {
		this.driver = driver;
//		getScenario().log("Tablets instance created: " + this.hashCode());
//		System.out.println("Tablets instance created: " + this.hashCode());
	}
	public void navigateToTabletsScreen() {
		click(driver, tablets_menu);	
	}
	public void addGalaxyTabToCart() {
		click(driver, galaxy_tab);
		clickAddToCartBtn(driver);
	}
	
	

}
