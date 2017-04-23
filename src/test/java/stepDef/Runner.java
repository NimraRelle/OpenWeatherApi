package stepDef;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

public class Runner {
	
	@CucumberOptions(monochrome = true, format = {"pretty", "html:target/cucumber","json:target/cucumber-report.json"}, features="src/test/resources/features", 
			tags={"@OpenWeather"},
			glue = "classpath:stepDef")
	public class RunnerA extends AbstractTestNGCucumberTests { 
		
	}

}
