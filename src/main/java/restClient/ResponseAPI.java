package restClient;

import org.testng.log4testng.Logger;

public class ResponseAPI {

	private String header;
    private String message;
    private int statusCode;
    static final Logger logger = Logger.getLogger(ResponseAPI.class);


    public ResponseAPI() {
    }

    public ResponseAPI(String message, int statusCode, String header) {
        this.message = message;
        this.statusCode = statusCode;
        this.header = header;
    }

    public String getHeader() {
        return header;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String toString() {
        return "Response{" +
                "header='" + header + '\'' +
                ", message='" + message + '\'' +
                ", statusCode=" + statusCode +
                '}';
    }

}
