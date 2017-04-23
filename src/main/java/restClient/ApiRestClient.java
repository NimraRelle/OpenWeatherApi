package restClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.ReadConfig;

import static io.restassured.RestAssured.given;

public class ApiRestClient {

	private List<String[]> formattedParams = new ArrayList<String[]>();
	private Map<String, String> paramMap = new HashMap<String, String>();
	private RequestSpecification requestParameters = null;

	/**
	 * 
	 * @param tableParams
	 *            - taken from cucumber scenarios as string separators between
	 *            parameters ",," separators between key:value pairs "="
	 * @return - List with formatted parameters [key,value][key,value]..
	 */

	public List<String[]> formatDataTableParams(String tableParams) {
		formattedParams.clear();
		if (tableParams.contains(",,")) {
			String[] first = tableParams.split(",,");
			for (String el : first) {
				String[] second = el.split("=");
				formattedParams.add(second);
			}
		} else {
			String[] second = tableParams.split("=");
			formattedParams.add(second);
		}

		return formattedParams;
	}

	public String getKey(int index) {
		return formattedParams.get(index)[0];
	}

	public String getValue(int index) {
		return formattedParams.get(index)[1];
	}

	/**
	 * @param tableParams - taken from cucumber table
	 * @return formatted all query parameters needed for the URL
	 */
	public RequestSpecification addParametersTest(String tableParams) {
		formatDataTableParams(tableParams);
		int index = formattedParams.size();
		for (int i = 0; i < index; i++) {
			paramMap.put(getKey(i), getValue(i));
		}
		requestParameters = given().params(paramMap);
		requestParameters.queryParam("APPID", ReadConfig.getPropValue("key")).contentType("application/json").log()
				.all();
		return requestParameters;
	}
	
	public RequestSpecification addParameters(String tableParams) {
		
		paramMap.put("APPID",ReadConfig.getPropValue("key"));
		
		formatDataTableParams(tableParams);
		int index = formattedParams.size();
		for (int i = 0; i < index; i++) {
			if(getKey(i).equals("APPID")){
				paramMap.remove("APPID");
				paramMap.put(getKey(i), getValue(i));
				continue;
			}

			paramMap.put(getKey(i), getValue(i));
		}
		requestParameters = given().params(paramMap);
		requestParameters.contentType("application/json").log()
				.all();
		return requestParameters;
	}
	
	public JsonPath getResponseAsString(Response response){
		JsonPath jsonPath = new JsonPath(response.body().asString());
		return 	jsonPath;
	}

	/**
	 * 
	 * @param path - the path in the URL
	 * @param tableParams - query parameters taken from cucumber table
	 * Performs a get request
	 * @return response 
	 */
	public Response getMethod(String path, String tableParams) {
		return addParameters(tableParams).get(ReadConfig.getPropValue("api_url") + path);
	}

}
