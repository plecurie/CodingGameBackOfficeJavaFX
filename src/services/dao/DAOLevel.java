package services.dao;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.List;

public class DAOLevel {

    public List getLevels() {
        return null;
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
                parameters = Json.createObjectBuilder().add("id_game", id_game)
                        .add("name", name).add("difficulty", difficulty).build();
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
        List list = http.sendPostRequest(parameters,"/levels/create/");

        if (list != null) created = true;

        return created;
    }
}
