package restClient;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.ReadConfig;

import static io.restassured.RestAssured.given;

public class RESTClient {
	private String baseUrl;
    private Request currentRequest;
    private String contentType;
    private Map<String, String> params = new HashMap<>();
    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> urlParams = new HashMap<>();
    private List<ResponseAPI> responses = new ArrayList<>();

    public RESTClient(){
        this.baseUrl = getEnv();
    }

       public String getEnv(){
        return ReadConfig.getPropValue("api_url");
    }
 

    public Map<String, String> getParams() {
        return params;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setCurrentRequest(Request currentRequest) {
        this.currentRequest = currentRequest;
    }

    public Request getCurrentRequest() {
        return currentRequest;
    }

    public Map<String, String> getUrlParams() {
        return urlParams;
    }


    public List<ResponseAPI> getResponses() {
        return responses;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getContentType() {
        return contentType;
    }

	
	public Response getMethod(String path, Map<String, String> parameters) throws Throwable{
		 String temp = this.baseUrl + path + parameters;
	        System.out.println(parameters);
	        System.out.println(temp);
	        return given()
	        		.queryParam("APPID", ReadConfig.getPropValue("key"))
	        		.queryParams(parameters)
	                .contentType("application/json")
	                .log().all()
	                .get(this.baseUrl + path);
	}
	
	
	@SuppressWarnings("rawtypes")
	public ResponseAPI getResponse(String path, Map<String, String> parameters) throws Throwable{
		ExtractableResponse response=getMethod(path,parameters).then()
        .log().all()
        .extract();
		responses.add(new ResponseAPI(response.body().asString(), response.statusCode(), response.headers().toString()));
		return getLastResponse();
	}


    public void post(String path,String param, String body, Map<String,String> urlParams) throws Throwable {
        RequestSpecification rs = given().contentType(ContentType.JSON);
        if(headers.size() > 0) {
            rs = rs.headers(headers);
        }
        if(urlParams.size() > 0) {
            rs = rs.queryParams(urlParams);
        }
        if(params.size() > 0) {
            rs =  rs.params(params);
        }
        if(!body.equals("")) {
            rs = rs.body(body);
        }
        @SuppressWarnings("rawtypes")
		ExtractableResponse response = rs
                .log().all()
                .post(this.baseUrl + path+param)
                .then()
                .log().all()
                .extract();
        responses.add(new ResponseAPI(response.body().asString(), response.statusCode(), response.headers().toString()));
    }

    @SuppressWarnings("rawtypes")
	public void delete(String path, String param) throws Throwable {
        System.out.println(path);
        System.out.println(param);
		ExtractableResponse response = given()
                .contentType(ContentType.JSON)
                .log().all()
                .delete(this.baseUrl + path + param)
                .then()
                .log().all()
                .extract();
        responses.add(new ResponseAPI(response.body().asString(), response.statusCode(), response.headers().toString()));
        System.out.println(getLastResponse().toString());
    }

    @SuppressWarnings("rawtypes")
	public void put(String path, String body) throws Throwable {
        ExtractableResponse response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .put(this.baseUrl + path)
                .then()
                .log().all()
                .extract();
        responses.add(new ResponseAPI(response.body().asString(), response.statusCode(), response.headers().toString()));
    }

    public ResponseAPI getLastResponse() {
        return responses.get(responses.size() - 1);
    }


}
