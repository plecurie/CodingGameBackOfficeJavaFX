package dao.apirequest;

import controllers.AlertController;
import dao.services.ErrorHandler;
import dao.services.InputStreamToJson;
import settings.ApiConstant;

import javax.json.JsonObject;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AuthToken {

    private String token;

    public String getToken(JsonObject params, String route) {

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
            System.out.println(e.getMessage());
            ErrorHandler errorHandler = new ErrorHandler();
            String error = errorHandler.getErrorCode(e.getMessage());
            AlertController.showAlert(error);
        }
        return token;
    }

}
