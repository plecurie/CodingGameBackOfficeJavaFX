package services.dao;

import models.Game;
import models.LevelQuizz;

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

    public List<LevelQuizz> getLevelsQuizz() {
        int id = 0;
        int idLevels = 0;
        String question = "";
        String answer1 = "";
        String answer2 = "";
        String answer3 = "";
        String answer4 = "";
        String correctAnswer = "";
        int difficulty = 1;

        HttpRequest http = new HttpRequest();
        List list = http.sendGetRequest("/level_quizz");

        List<LevelQuizz> listLevelQuizz = new ArrayList<>();

        for (Object aList : list) {
            String[] list_objet = aList.toString().split(":");
            String key = list_objet[0];
            String value = list_objet[1];



            switch (key) {
                case "id": {
                    id = Integer.valueOf(value);
                    break;
                }
                case "id_levels": {
                    idLevels = Integer.parseInt(value);
                    break;
                }
                case "question": {
                    question = value;
                    break;
                }
                case "answer1": {
                    answer1 = value;
                    break;
                }
                case "answer2": {
                    answer2 = value;
                    break;
                }
                case "answer3": {
                    answer3 = value;
                    break;
                }
                case "answer4": {
                    answer4 = value;
                    break;
                }
                case "correct_answer": {
                    correctAnswer = value;
                    break;
                }
                case "difficulty": {
                    difficulty = Integer.valueOf(value);
                    listLevelQuizz.add(new LevelQuizz(id, idLevels, question, answer1, answer2, answer3, answer4, correctAnswer, difficulty));
                    break;
                }
                default: {
                    break;
                }
            }
        }
        return listLevelQuizz;
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


    public Boolean deleteLevel(int idLevel){
        HttpRequest httpRequest = new HttpRequest();
        JsonObject parameters = Json.createObjectBuilder().build();

        httpRequest.sendDeleteRequest(parameters, "/levels/" + Game.GAME_ID + "/" + idLevel);
        return true;
        //return httpRequest.sendPostRequest(parameters, "/level_quizz");
    }
}
