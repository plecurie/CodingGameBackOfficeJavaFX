package services.dao;

import controllers.DisplayerController;
import models.User;
import services.DataFactory;
import services.ErrorHandler;
import settings.ApiConstant;

import javax.json.JsonObject;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class HttpRequest {

    String getToken(JsonObject params) {
        String token = "";
        try {
            URL url = new URL(ApiConstant.HOST + "/auth/signin");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");

            OutputStream os = connection.getOutputStream();
            os.write(params.toString().getBytes("UTF-8"));
            os.close();

            token = new DataFactory().parseInputStream(connection.getInputStream());

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

            DataFactory dataFactory = new DataFactory();
            response = dataFactory.parseJSON(new DataFactory().parseInputStream(connection.getInputStream()));

        } catch (IOException e) {
            ErrorHandler errorHandler = new ErrorHandler();
            String error = errorHandler.getErrorCode(e.getMessage());
            DisplayerController displayerController = new DisplayerController();
            displayerController.displayAlert(error);
        }

        return response;
    }

    String sendPostRequest(JsonObject params, String route) {
        String response = "";

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
            response = (new DataFactory().parseInputStream(connection.getInputStream()));

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
            response = new DataFactory().parseInputStream(connection.getInputStream());
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

    String sendDeleteRequest(String route) {
        String response = "";

        try {
            URL server = new URL(ApiConstant.HOST + route);
            HttpURLConnection connection = (HttpURLConnection) server.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Authorization", User.getTOKEN());
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("DELETE");

            InputStreamReader in = new InputStreamReader(connection.getInputStream());
            response = new DataFactory().parseInputStream(connection.getInputStream());
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

    String sendLogoutRequest(JsonObject params) {
        String response = "";
        try {
            URL server = new URL(ApiConstant.HOST + "/auth/logout");
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

            response = new DataFactory().parseInputStream(connection.getInputStream());

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
