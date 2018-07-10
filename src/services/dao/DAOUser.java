package services.dao;

import models.User;

import java.util.ArrayList;
import java.util.List;

public class DAOUser {

    public List getUsers() {

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

        HttpRequest http = new HttpRequest();
        List list = http.sendGetRequest("/users");

        List<User> list_users = new ArrayList<>();
        System.out.println(list);

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
                case "profil": {
                    profil = value;
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
                case "type": {
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
                case "profil": {
                    profil = value;
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
                case "type": {
                    type = value;
                    selected_user = new User(id, username, firstname, lastname, age, email, profil, level, exp, type);
                    break;
                }
                default:
                    break;
            }
        }
        return selected_user;
    }

}