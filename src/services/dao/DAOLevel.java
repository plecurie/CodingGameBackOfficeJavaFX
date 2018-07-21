package services.dao;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.ArrayList;
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


    public Boolean createLevelQuizz(String question, ArrayList<String> responses, String correctAnswer, String isEvaluateLevel, String difficulty){
        HttpRequest httpRequest = new HttpRequest();
        JsonObject parameters = Json.createObjectBuilder().add("question", question)
                .add("id_game", 4)
                .add("name", " ")
                .add("answer1", responses.get(0))
                .add("answer2", responses.get(1))
                .add("answer3", responses.get(2))
                .add("answer4", responses.get(3))
                .add("correct_answer", correctAnswer)
                .add("evaluate_lvl_player", isEvaluateLevel)
                .add("difficulty", difficulty)
                .build();

        httpRequest.sendPostRequest(parameters, "/levels/create/");
        return true;
        //return httpRequest.sendPostRequest(parameters, "/level_quizz");
    }
}
