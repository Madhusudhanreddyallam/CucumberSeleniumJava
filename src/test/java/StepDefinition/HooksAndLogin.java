package StepDefinition;

import CommonUtility.BaseClass;
import POM.LoginClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;

public class HooksAndLogin extends BaseClass {

	//If you want to declare hooks out of Step definition then mention the hooks path in Runner glue
	@Before
	public void openBrowser(Scenario scenario) {
		browserSetup();
		setScenario(scenario);
	}
	
	@After 
	public void terminateSession() {
		tearDown();
	}

	@Given("^I login into application$")
	public void login() {
		LoginClass loginClass = new LoginClass(getDriver());
		loginClass.logintoApp("madhusudhanreddy.allam@gmail.com", "Atul@123"); 
	}

}
