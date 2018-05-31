package dao.apirequest;

import dao.services.InputStreamToJson;
import settings.ApiConstant;

import javax.json.JsonObject;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Put {

    public String sendPutRequest(JsonObject params, String route) {
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
            e.printStackTrace();
        }
        return response;
    }

}
