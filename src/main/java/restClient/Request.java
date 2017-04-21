package restClient;

import java.util.HashMap;

public class Request {
	private String path;
    private HashMap<String, Object> params = new HashMap<>();
    private HashMap<String, String> headers = new HashMap<>();
    private HashMap<String, Object> queryParams = new HashMap<>();
    private String body = "";
    private String contentType;

    public Request() {
    }

//    public Request() {
//        this.path = "";
//        this.params = new HashMap<>();
//        this.headers = new HashMap<>();
//        this.queryParams = new HashMap<>();
//        this.body = "";
//        this.contentType = "application/json";
//        this.version = "v1";
//    }

    //    @Override
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    //    @Override
    public HashMap<String, Object> getParams() {
        return params;
    }

    public void setParams(HashMap<String, Object> params) {
        this.params = params;
    }

    //    @Override
    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    //    @Override
    public HashMap<String, Object> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(HashMap<String, Object> queryParams) {
        this.queryParams = queryParams;
    }

    //    @Override
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    //    @Override
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

     public void addAuthorization(String authString) {
        headers.put("Authorization", authString);
    }

    public void deleteAuthorization() {
        headers.remove("Authorization");
    }

    @Override
    public String toString() {
        return "Request{" +
                "path='" + path + '\'' +
                ", body='" + body + '\'' +
                ", params=" + params +
                ", headers=" + headers +
                ", queryParams=" + queryParams +
                ", contentType='" + contentType + '\'' +
                '}';
    }


}
