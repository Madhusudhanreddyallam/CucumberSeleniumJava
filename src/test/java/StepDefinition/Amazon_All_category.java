package StepDefinition;

import CommonUtility.BaseClass;
import POM.Cameras;
import POM.Phones;
import POM.Tablets;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Amazon_All_category extends BaseClass{
	Tablets tablets;
	Phones phones;
	Cameras cameras;

	@When("I click on add to cart of Samsung Galaxy Tab")
	public void i_click_on_add_to_cart_of_samsung_galaxy_tab() {
		tablets = new Tablets(getDriver());System.out.println("Tablets: "+Thread.currentThread().getId());
		tablets.navigateToTabletsScreen();
		tablets.addGalaxyTabToCart();
	}

	@Then("Galaxy Tab is added to cart")
	public void galaxy_tab_is_added_to_cart() {
		System.out.println("Added to cart");
	}

	@When("I click on add to cart of iphone")
	public void i_click_on_add_to_cart_of_iphone() {
		phones = new Phones(getDriver()); System.out.println("Phones: "+Thread.currentThread().getId());
		phones.navigateToPhonesScreen();
		phones.addiphoneToCart();
	}

	@Then("iphone is added to cart")
	public void iphone_is_added_to_cart() {
		System.out.println("Added to cart");
	}

	@When("I click on add to cart of Nikon")
	public void i_click_on_add_to_cart_of_nikon() {
		cameras = new Cameras(getDriver()); System.out.println("Cameras: "+Thread.currentThread().getId());
		cameras.navigateToCamerasScreen();
		cameras.addNikonToCart();
	}

	@Then("Nikon camera is added to cart")
	public void nikon_camera_is_added_to_cart() {
		System.out.println("Added to cart");
	}
}
