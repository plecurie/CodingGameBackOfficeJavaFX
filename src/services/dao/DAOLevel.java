package services.dao;

import java.util.List;

public class DAOLevel {

    public List getLevels() {
        return null;
    }

    public Boolean createLevel(int id_game, String name, int difficulty, String functions, List grid_list ) {
        boolean created = false;
/*        int i = 0;


        JsonObject parameters = (JsonObject) Json.createObjectBuilder().add("id_game", id_game)
                .add("name", name).add("difficulty", difficulty)
                .add("functions", functions);

        for (Object cell_txt : grid_list) {
            ((JsonObjectBuilder) parameters).add("c"+i, String.valueOf(cell_txt));
            i++;
        }

        ((JsonObjectBuilder) parameters).build();


        HttpRequest http = new HttpRequest();
        List list = http.sendPostRequest(parameters,"/levels/create/");

        if (list != null) created = true;
*/
        return created;
    }
}
