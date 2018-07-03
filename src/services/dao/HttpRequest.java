package services.dao;

import controllers.DisplayerController;
import models.User;
import services.ErrorHandler;
import services.InputStreamToJson;
import services.JsonToString;
import settings.ApiConstant;

import javax.json.JsonObject;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class HttpRequest {

    private String token;

    String getToken(JsonObject params, String route) {

        try {
            URL url = new URL(ApiConstant.HOST + route);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");

            OutputStream os = connection.getOutputStream();
            os.write(params.toString().getBytes("UTF-8"));
            os.close();

            token = new InputStreamToJson().parseInputStream(connection.getInputStream());

            connection.disconnect();
        } catch (IOException e) {
            ErrorHandler errorHandler = new ErrorHandler();
            String error = errorHandler.getErrorCode(e.getMessage());
            DisplayerController displayerController = new DisplayerController();
            displayerController.displayAlert(error);
        }
        return token;
    }

    List sendGetRequest(String route)  {

        List response = new ArrayList();

        try {
            URL serverURL = new URL( ApiConstant.HOST + route);
            HttpURLConnection connection = (HttpURLConnection) serverURL.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Authorization", User.getTOKEN());
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");

            JsonToString jsonToString = new JsonToString();
            response = jsonToString.parseJSON(new InputStreamToJson().parseInputStream(connection.getInputStream()));

        } catch (IOException e) {
            ErrorHandler errorHandler = new ErrorHandler();
            String error = errorHandler.getErrorCode(e.getMessage());
            DisplayerController displayerController = new DisplayerController();
            displayerController.displayAlert(error);
        }

        return response;
    }

    List sendPostRequest(JsonObject params, String route) {
        List response = new ArrayList();

        try {
            URL server = new URL(ApiConstant.HOST + route);
            HttpURLConnection connection = (HttpURLConnection) server.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Authorization", User.getTOKEN());
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");

            OutputStream os = connection.getOutputStream();
            os.write(params.toString().getBytes("UTF-8"));
            os.close();


            InputStreamReader in = new InputStreamReader(connection.getInputStream());
            response.add(new InputStreamToJson().parseInputStream(connection.getInputStream()));
            response = new JsonToString().parseJSON(response.toString());

            in.close();
            connection.disconnect();
        } catch (IOException e) {
            ErrorHandler errorHandler = new ErrorHandler();
            String error = errorHandler.getErrorCode(e.getMessage());
            DisplayerController displayerController = new DisplayerController();
            displayerController.displayAlert(error);
        }
        return response;
    }

    String sendPutRequest(JsonObject params, String route) {
        String response = "";

        try {
            URL server = new URL(ApiConstant.HOST + route);
            HttpURLConnection connection = (HttpURLConnection) server.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("PUT");

            OutputStream os = connection.getOutputStream();
            os.write(params.toString().getBytes("UTF-8"));
            os.close();

            InputStreamReader in = new InputStreamReader(connection.getInputStream());
            response = new InputStreamToJson().parseInputStream(connection.getInputStream());
            in.close();

            connection.disconnect();

        } catch (IOException e) {
            ErrorHandler errorHandler = new ErrorHandler();
            String error = errorHandler.getErrorCode(e.getMessage());
            DisplayerController displayerController = new DisplayerController();
            displayerController.displayAlert(error);
        }
        return response;
    }



}
