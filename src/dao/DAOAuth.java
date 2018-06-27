package dao;

import services.HttpRequest;
import models.User;

import javax.json.Json;
import javax.json.JsonObject;

public class DAOAuth extends HttpRequest {
    public Boolean signIn(String username, String password) {
        boolean authenticated = false;

        JsonObject parameters = Json.createObjectBuilder().add("username", username)
                .add("password", password).build();

        String token = getToken(parameters,"/auth/signin");

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
