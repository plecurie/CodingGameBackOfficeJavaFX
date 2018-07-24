package services.dao;

import models.History;
import models.User;
import settings.ApiConstant;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

import static settings.ApiConstant.DEFAULT_ADMIN_TYPE;
import static settings.ApiConstant.DEFAULT_PASSWORD;

public class DAOUser {

    public List<User> getUsers() {

        HttpRequest http = new HttpRequest();
        List list = http.sendGetRequest("/users");

        return buildUserList(list);
    }

    public List<History> getHistorySelectedUser(int id) {

        String game="";
        String level="";
        int score = 0;
        String date = "";

        HttpRequest http = new HttpRequest();
        List list = http.sendGetRequest("/historic/" + id);
        List<History> list_history = new ArrayList<>();

        for (Object aList : list) {
            String[] list_objet = aList.toString().split(":");
            String key = list_objet[0];
            String value = list_objet[1];

            switch (key) {
                case "id_game": {
                    game = value;
                    break;
                }
                case "id_level": {
                    level = value;
                    break;
                }
                case "score": {
                    score = Integer.valueOf(value);
                    break;
                }
                case "date": {
                    date = value;
                    list_history.add(new History(id, game, level, score, date));
                    break;
                }
                default:
                    break;
            }
        }
        return list_history;
    }

    public User getSelectedUser(int id) {

        String username = "";
        String firstname = "";
        String lastname = "";
        int age = 0;
        String email = "";
        String profil = "";
        int level = 0;
        float exp = 0;
        String type;


        HttpRequest http = new HttpRequest();
        List list = http.sendGetRequest("/users/" + id);
        User selected_user = new User();

        for (Object aList : list) {
            String[] list_objet = aList.toString().split(":");
            String key = list_objet[0];
            String value = list_objet[1];

            switch (key) {
                case "username": {
                    username = value;
                    break;
                }
                case "firstname": {
                    firstname = value;
                    break;
                }
                case "lastname": {
                    lastname = value;
                    break;
                }
                case "age": {
                    age = Integer.valueOf(value);
                    break;
                }
                case "email": {
                    email = value;
                    break;
                }
                case "id_type_profil": {
                    if (value != null) profil = value;
                    else value = "undefined";
                    break;
                }
                case "level": {
                    level = Integer.valueOf(value);
                    break;
                }
                case "exp": {
                    exp = Float.valueOf(value);
                    break;
                }
                case "userType": {
                    type = String.valueOf(value);
                    selected_user = new User(id, username, firstname, lastname, age, email, profil, level, exp, type);
                    break;
                }
                default:
                    break;
            }
        }
        return selected_user;
    }

    public List<User> getAdminUsers() {

        HttpRequest http = new HttpRequest();
        List list = http.sendGetRequest("/users/admin");

        return buildUserList(list);
    }

    public boolean createAdmin(String username, String firstname, String lastname, String email, int age) {
        boolean created = false;

        JsonObject parameters = Json
                .createObjectBuilder()
                .add("firstname", firstname)
                .add("lastname", lastname)
                .add("username", username)
                .add("password", DEFAULT_PASSWORD)
                .add("email", email)
                .add("age", age)
                .add("userType", DEFAULT_ADMIN_TYPE)
                .build();


        HttpRequest http = new HttpRequest();
        String response = http.sendPostRequest(parameters,"/users/create");

        if (response != null)
            created = true;

        return created ;
    }

    private List<User> buildUserList(List list) {

        int id = 0;
        String username = "";
        String firstname = "";
        String lastname = "";
        int age = 0;
        String email = "";
        String profil = "";
        int level = 0;
        float exp = 0;
        String type;

        HttpRequest httpRequest = new HttpRequest();

        List<User> list_users = new ArrayList<>();

        for (Object aList : list) {
            String[] list_objet = aList.toString().split(":");
            String key = list_objet[0];
            String value = list_objet[1];

            switch (key) {
                case "id": {
                    id = Integer.valueOf(value);
                    break;
                }
                case "username": {
                    username = value;
                    break;
                }
                case "firstname": {
                    firstname = value;
                    break;
                }
                case "lastname": {
                    lastname = value;
                    break;
                }
                case "age": {
                    age = Integer.valueOf(value);
                    break;
                }
                case "email": {
                    email = value;
                    break;
                }
                case "id_type_profil": {
                    if (value != null && Integer.valueOf(value) != 0){
                        List json = httpRequest.sendGetRequest("/users/type/" + value);
                        for (Object obj: json) {
                            String[] o = obj.toString().split(":");
                            if (o[0].equals("name")) profil = o[1];
                        }
                    }
                    else
                        profil = "undefined";
                    break;
                }
                case "level": {
                    level = Integer.valueOf(value);
                    break;
                }
                case "exp": {
                    exp = Float.valueOf(value);
                    break;
                }
                case "userType": {
                    type = value;
                    list_users.add(new User(id, username, firstname, lastname, age, email, profil, level, exp, type));
                    break;
                }
                default: {
                    break;
                }
            }
        }
        return list_users;
    }

}