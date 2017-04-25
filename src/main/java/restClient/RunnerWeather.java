package restClient;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

public class RunnerWeather {
	
	@CucumberOptions(monochrome = true, 
			plugin = { "html:target/cucumber-report/report2",
	                "json:target/cucumber.json",
	                },
			features="src/test/resources/features", 
			tags={"@weather"},
			glue = "classpath:stepDef")
	public class RunnerA extends AbstractTestNGCucumberTests { 
		
	}

}
