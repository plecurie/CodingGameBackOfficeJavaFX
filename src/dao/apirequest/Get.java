package dao.apirequest;

import controllers.SettingsController;
import dao.services.ErrorHandler;
import dao.services.InputStreamToString;
import dao.services.JsonToString;
import settings.ApiConstant;

import java.io.InputStream;
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
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setRequestMethod("GET");

                JsonToString jsonToString = new JsonToString();

                response = jsonToString.parseJSON(new InputStreamToString().parseInputStream(connection.getInputStream()));

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return response;
        }

}
