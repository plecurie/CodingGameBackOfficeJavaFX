package services;

public class ErrorHandler {

    public ErrorHandler() {
        //todo default constructor
    }

    public String getErrorCode(String message) {

        String error;

        if(message.contains("401")) {
            error = "Error 401 : UNAUTHORIZED";
        }
        else if(message.contains("403")) {
            error = "Error 403 : FORBIDDEN";
        }
        else if(message.contains("404")) {
            error = "Error 404 : NOT FOUND";
        }
        else if(message.contains("409")) {
            error = "Error 409 : CONFLICT";
        }
        else if(message.contains("500")) {
            error = "Error 500 : INTERNAL SERVOR ERROR";
        }
        else if(message.contains("503")) {
            error = "Error 503 : SERVICE UNAVAILABLE";
        }
        else if(message.contains("504")) {
            error = "Error 504 : GATEWAY TIMEOUT";
        }
        else if(message.contains("Connection refused: connect")) {
            error = "Error 503 : SERVICE UNAVAILABLE";
        }
        else {
            error = message;
        }

        return error;
    }
}
