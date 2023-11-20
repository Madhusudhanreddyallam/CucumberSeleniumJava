package Runner;

import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features={"@target/failed.txt"},
		glue= "StepDefinition",
		tags = "@Reg and not @sanity",
		plugin = {"pretty","rerun:target/failed.txt",
				  "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}, 
		monochrome = true, //Cucumber will produce o/p without using colors, and when set to false, it will attempt to use colors to enhance the readability of o/p
		//publish = true, // will publish report on "https://reports.cucumber.io/reports" path
		dryRun = false //Cucumber will not execute the actual scenario steps but will check if there are matching step definitions for each step in your feature files
		)

public class TestrunnerParallelRerunFailed extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}

/*
 * If the parallel execution fails move this runner class to Step Definition folder 
 * If you want to declare hooks out of Step definition then mention the hooks path in Runner glue
 */