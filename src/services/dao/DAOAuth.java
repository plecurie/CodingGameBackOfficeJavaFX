package services.dao;

import models.User;


import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

public class DAOAuth {
    public Boolean signIn(String username, String password) {
        boolean authenticated = false;

        JsonObject parameters = Json.createObjectBuilder().add("username", username)
                .add("password", password).build();

        HttpRequest http = new HttpRequest();
        String token = http.getToken(parameters);

        if (!token.isEmpty()) {
            User.setTOKEN(token);
            authenticated = true;
        }

        return authenticated;
    }

    public Boolean logout() {

        User.setTOKEN("");

        return true;
    }

}
