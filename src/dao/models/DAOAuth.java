package dao.models;

import dao.apirequest.AuthToken;
import models.User;

import javax.json.Json;
import javax.json.JsonObject;

public class DAOAuth {
    public Boolean signIn(String username, String password) {
        boolean authenticated = false;

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

    public Boolean signUp(String username, String password, String firstname, String lastname, String email, int age, String profil, int level, float exp, String type ) {
        Boolean created = false;

        /*
        *
        * */

        return created;
    }

    public Boolean logOut() {
        Boolean disconnected = false;

        /*
        *
        * */

        return disconnected;
    }
}
