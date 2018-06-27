package dao;


import services.HttpRequest;
import models.Game;

import java.util.ArrayList;
import java.util.List;

public class DAOGame extends HttpRequest {

    public List<Game> getGames() {

        int id = 0;
        String name = "";
        String description;

        List list = sendGetRequest("/games");

        List<Game> list_games = new ArrayList<>();

        for (Object aList : list) {
            String[] list_objet = aList.toString().split(":");
            String key = list_objet[0];
            String value = list_objet[1];

            switch (key) {
                case "id": {
                    id = Integer.valueOf(value);
                    break;
                }
                case "name": {
                    name = value;
                    break;
                }
                case "description": {
                    description = value;
                    list_games.add(new Game(id, name, description));
                    break;
                }
                default: {
                    break;
                }
            }
        }
        return list_games;
    }

    public Game getSelectedGame(int id) {

        String name = "";
        String description = "";

        List list = sendGetRequest("/games/" + id);
        Game selected_game = new Game();

        for (Object aList : list) {
            String[] list_objet = aList.toString().split(":");
            String key = list_objet[0];
            String value = list_objet[1];

            switch (key) {
                case "name": {
                    name = value;
                    break;
                }
                case "description": {
                    description = value;
                    selected_game = new Game(id, name, description);
                    break;
                }
                default:
                    break;
            }
        }
        return selected_game;
    }

}
