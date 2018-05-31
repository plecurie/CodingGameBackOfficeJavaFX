package dao.models;

import dao.apirequest.AuthToken;
import models.User;

import javax.json.Json;
import javax.json.JsonObject;

public class DAOAuth {
    public Boolean signIn(String username, String password) {
        boolean authenticated = false;

        System.out.println("\nusername : " + username + "\npassword : " + password);

        JsonObject parameters = Json.createObjectBuilder().add("username", username)
                .add("password", password).build();

        AuthToken authToken = new AuthToken();

        String token = authToken.getToken(parameters,"/auth/signin");

        if (token != null) {
            User.setTOKEN(token);
            authenticated = true;
        }

        return authenticated;
    }
}
