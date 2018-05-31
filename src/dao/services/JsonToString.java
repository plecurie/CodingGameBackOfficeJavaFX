package dao.services;

import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonToString {

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


}
