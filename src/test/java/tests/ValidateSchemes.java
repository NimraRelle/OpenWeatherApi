package tests;


import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import restClient.RESTClient;
import restClient.ResponseAPI;
import utils.ReadConfig;

import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.equalTo;
public class ValidateSchemes {
	
	RESTClient rc = new RESTClient();
	ResponseAPI response;
	
	@Test
	public void validateSchemeForecast() throws Throwable {
		Map<String, String> urlParams = new HashMap<>();
		urlParams.put("q", "Utrecht");
		Response response=rc.getMethod(ReadConfig.getPropValue("api_forecast"), urlParams)
		.then()
		.body(matchesJsonSchemaInClasspath("ForecastScheme.json"))
		.statusCode(200)
		.body("city.name", equalTo("Utrecht"))
		.body("city.country",equalTo("NL"))
		.body("city.id", equalTo(Integer.parseInt("2745912")))
		.extract().response();
		System.out.println(response.asString());
		
	}
	
	@Test
	public void assertStatusCodes1() throws Throwable{
		Map<String, String> urlParams = new HashMap<>();
		urlParams.put("q", "Utrecht,NL");
		Response response=rc.getMethod(ReadConfig.getPropValue("api_forecast"), urlParams)
		.then()
		.body(matchesJsonSchemaInClasspath("ForecastScheme.json"))
		.statusCode(200).and()
		.body("city.name", equalTo("Utrecht"))
		.body("city.country",equalTo("NL"))
		.body("city.id", equalTo(Integer.parseInt("2745912")))
		.extract().response();
		System.out.println(response.asString());
	}
	
	@Test
	public void assertStatusCodes2() throws Throwable{
		Map<String, String> urlParams = new HashMap<>();
		urlParams.put("lat", "52.0908");
		urlParams.put("lon", "5.1222");
		Response response=rc.getMethod(ReadConfig.getPropValue("api_forecast"), urlParams)
		.then()
		.body(matchesJsonSchemaInClasspath("ForecastScheme.json"))
		.statusCode(200).and()
		.body("city.name", equalTo("Utrecht"))
		.body("city.country",equalTo("NL"))
		.body("city.id", equalTo(Integer.parseInt("2745912")))
		.extract().response();
		System.out.println(response.asString());
	}
	
	@Test
	public void assertStatusCodes3() throws Throwable{
		Map<String, String> urlParams = new HashMap<>();
		urlParams.put("id", "2745912");
		Response response=rc.getMethod(ReadConfig.getPropValue("api_forecast"), urlParams)
		.then()
		.body(matchesJsonSchemaInClasspath("ForecastScheme.json"))
		.statusCode(200).and()
		.body("city.name", equalTo("Utrecht"))
		.body("city.country",equalTo("NL"))
		.body("city.id", equalTo(Integer.parseInt("2745912")))
		.extract().response();
		System.out.println(response.asString());
	}
	
	@Test
	public void assertStatusCodes4() throws Throwable{
		Map<String, String> urlParams = new HashMap<>();
		urlParams.put("zip", "94040,us");
		Response response=rc.getMethod(ReadConfig.getPropValue("api_forecast"), urlParams)
		.then()
		.body(matchesJsonSchemaInClasspath("ForecastScheme.json"))
		.statusCode(200).and()
		.body("city.name", equalTo("Mountain View"))
		.body("city.country",equalTo("US"))
		.body("city.id", equalTo(Integer.parseInt("5375480")))
		.extract().response();
		System.out.println(response.asString());
	}
	
	@Test
	public void assertErrorCodes() throws Throwable {
		Map<String, String> urlParams = new HashMap<>();
		urlParams.put("q", "jjjj");
		Response response =rc.getMethod(ReadConfig.getPropValue("api_forecast"), urlParams)
		.then()
		.statusCode(404)
		.and()
		.body("message",equalTo("city not found"))
		.extract().response();
		System.out.println(response.asString());
	}
	
		

}
