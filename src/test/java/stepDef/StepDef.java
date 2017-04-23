package stepDef;

import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.apache.log4j.Logger;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import restClient.ApiRestClient;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class StepDef {

	private Response response;
	private ApiRestClient restClient = new ApiRestClient();
	static final Logger logger = Logger.getLogger(StepDef.class);

	@Given("I open urlpath (.*) with parameters (.*)")
	public void open_url(String path, String parameters) {
		logger.info("RUNNING "+Thread.currentThread().getStackTrace()[1].getMethodName() + " ...... \n");
		response = restClient.getMethod(path, parameters);
	}

	@Then("Response is valid according to (.*)")
	public void validate_scheme(String schemeFile) {
		logger.info("RUNNING "+Thread.currentThread().getStackTrace()[1].getMethodName() + " ...... \n");
		response.then().body(matchesJsonSchemaInClasspath(schemeFile));
	}

	@Then("Response code is (\\d+)")
	public void status_code(int code) {
		logger.info("RUNNING "+Thread.currentThread().getStackTrace()[1].getMethodName() + " ...... \n");
		response.then().statusCode(code);
	}

	@And("Response contains (.*)")
	public void response_contains(String values) {
		logger.info("RUNNING "+Thread.currentThread().getStackTrace()[1].getMethodName() + " ...... \n");
		List<String[]> formattedValues = restClient.formatDataTableParams(values);
		for (int i = 0; i < formattedValues.size(); i++) {
			if (restClient.getValue(i).contains("asNumber")) {
				String param = restClient.getValue(i).replace("asNumber", "");
				response.then().body(restClient.getKey(i), equalTo(Integer.parseInt(param)));
			} else
				response.then().body(restClient.getKey(i), equalTo(restClient.getValue(i)));
		}
		logger.info((response.body().prettyPrint()));
	}
	
	
}
