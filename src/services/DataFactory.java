package services;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.stream.JsonParser;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataFactory {

    public List parseJSON(String s){
        JsonParser parser = Json.createParser(new StringReader(s));
        List list = new ArrayList();
        String key="",value="";
        while (parser.hasNext()){
            JsonParser.Event event = parser.next();

            switch(event) {
                case START_ARRAY:
                case END_ARRAY:
                case START_OBJECT:
                case END_OBJECT:
                case VALUE_FALSE:
                case VALUE_TRUE:
                    break;
                case KEY_NAME:
                    key = parser.getString();
                    break;
                case VALUE_NULL:
                    value="null";
                    list.add(key + ":" +value);
                    break;
                case VALUE_STRING:
                case VALUE_NUMBER:
                    value = parser.getString();
                    list.add(key + ":" +value);
                    break;
            }
        }
        return list;
    }

    public String parseInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder json = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                json.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return json.toString();
    }

    private List convertJsonObjecttoList(JsonObject objects) {

        DataFactory dataFactory = new DataFactory();
        return dataFactory.parseJSON(String.valueOf(objects));
    }


}
