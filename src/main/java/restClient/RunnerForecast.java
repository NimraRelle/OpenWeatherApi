package restClient;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

public class RunnerForecast {
	
	@CucumberOptions(monochrome = true, 
			plugin = { "html:target/cucumber-report/report1",
	                "json:target/cucumber1.json",
	                },
			features="src/test/resources/features", 
			tags={"@forecast"},
			glue = "classpath:stepDef")
	public class RunnerA extends AbstractTestNGCucumberTests { 
		
	}

}
