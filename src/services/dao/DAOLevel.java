package services.dao;

import controllers.games.toolbox.sombrero.Sombrero;
import controllers.games.toolbox.sombrero.SombreroFactory;
import models.Game;
import models.Level;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class DAOLevel {

    public List<Level> getLevels(int id_game) {
        int id = 0;
        String name = "";
        int difficulty;

        HttpRequest http = new HttpRequest();
        List list = http.sendGetRequest("/levels/" + id_game);

        List<Level> list_levels = new ArrayList<>();

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
                case "difficulty": {
                    difficulty = Integer.valueOf(value);
                    list_levels.add(new Level(id, name, difficulty));
                    break;
                }
                default: {
                    break;
                }
            }
        }
        return list_levels;
    }

    public Boolean createLevel(int id_game, String name, int difficulty ) {
        boolean created = false;
        JsonObject parameters = null;

        switch (id_game) {
            case 1 : {
                parameters = Json.createObjectBuilder().add("id_game", id_game)
                        .add("name", name).add("difficulty", difficulty).build();
                break;
            }
            case 2 : {
                parameters = Json.createObjectBuilder().add("id_game", id_game)
                        .add("name", name).add("difficulty", difficulty).build();
                break;
            }
            case 3 : {
                SombreroFactory sombreroFactory = new SombreroFactory();
                parameters = sombreroFactory.convertSombreroToJsonObject(Sombrero.getSelectedSombrero());
                break;
            }
            case 4 : {
                parameters = Json.createObjectBuilder().add("id_game", id_game)
                        .add("name", name).add("difficulty", difficulty).build();
                break;
            }
            default:
                break;
        }

        HttpRequest http = new HttpRequest();
        String response = http.sendPostRequest(parameters,"/levels/create/");

        if (!response.isEmpty())
            created = true;

        return created;
    }

    public Level getSelectedLevel(int id_level) {
        String name = "";
        int difficulty;

        HttpRequest http = new HttpRequest();
        List list = http.sendGetRequest("/levels/" + Game.GAME_ID + "/" + id_level);

        SombreroFactory sombreroFactory = new SombreroFactory();
        sombreroFactory.browseAndBuildSombrero(list, false);

        Level selected_level = new Level();

        for (Object aList : list) {
            String[] list_objet = aList.toString().split(":");
            String key = list_objet[0];
            String value = list_objet[1];

            switch (key) {
                case "name" : {
                    name = value;
                    break;
                }
                case "difficulty" : {
                    difficulty = Integer.valueOf(value);
                    selected_level = new Level(id_level, name, difficulty);
                    Level.setSelectedLevel(selected_level);
                    break;
                }
                default:
                    break;
            }
        }
        return selected_level;
    }

    public void updateLevels(String text) {

    }
}
