package dao.apirequest;

import controllers.AlertController;
import dao.services.ErrorHandler;
import dao.services.InputStreamToJson;
import dao.services.JsonToString;
import models.User;
import settings.ApiConstant;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Get {

        public List sendGetRequest(String route)  {

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
                AlertController.showAlert(error);
            }

            return response;
        }

}
