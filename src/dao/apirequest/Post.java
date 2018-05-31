package dao.apirequest;

import dao.services.InputStreamToString;
import dao.services.JsonToString;
import settings.ApiConstant;

import javax.json.JsonObject;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Post {

    public List sendPostRequest(JsonObject params, String route) {
        List response = new ArrayList();

        try {
            URL server = new URL(ApiConstant.HOST + route);
            HttpURLConnection connection = (HttpURLConnection) server.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");

            OutputStream os = connection.getOutputStream();
            os.write(params.toString().getBytes("UTF-8"));
            os.close();


            InputStreamReader in = new InputStreamReader(connection.getInputStream());
            response.add(new InputStreamToString().parseInputStream(connection.getInputStream()));
            response = new JsonToString().parseJSON(response.toString());

            in.close();
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


}
