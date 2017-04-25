package restClient;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

public class RunnerWeather {
	
	@CucumberOptions(monochrome = true, format = {"pretty", 
			"html:target/cucumber","json:target/cucumber-report.json"}, 
			features="src/test/resources/features", 
			tags={"@weather"},
			glue = "classpath:stepDef")
	public class RunnerA extends AbstractTestNGCucumberTests { 
		
	}

}
