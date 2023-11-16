package Runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/resources/Features",
		glue= "StepDefinition",
		tags = "@Reg",
		plugin = {"pretty",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}, 
		monochrome = true, //Cucumber will produce o/p without using colors, and when set to false (or omitted), it will attempt to use colors to enhance the readability of o/p
		//publish = true, // will publish report on "https://reports.cucumber.io/reports" path
		dryRun = false //Cucumber will not execute the actual scenario steps but will check if there are matching step definitions for each step in your feature files
		)

public class TestRunner {

}
