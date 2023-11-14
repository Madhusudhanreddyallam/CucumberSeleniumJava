package StepDefinition;

import CommonUtility.BaseClass;
import POM.LoginClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;

public class HooksAndLogin extends BaseClass {
	
	//If you want to declare hooks out of Step definition then mention the hooks path in Runner glue
	@Before
	public void openBrowser() {
		browserSetup();
	}

	@Given("^I login into application$")
	public void login() {
		LoginClass loginClass = new LoginClass(BaseClass.getDriver());
		loginClass.logintoApp("madhusudhanreddy.allam@gmail.com", "Atul@123"); 
	}
	
	@After 
	public void terminateSession() {
		tearDown();
	}
}
