package services.dao;

import controllers.DisplayerController;
import controllers.games.toolbox.sombrero.Sombrero;
import controllers.games.toolbox.sombrero.SombreroFactory;
import models.Game;
import models.Level;
import models.LevelExplorer;
import models.LevelQuizz;

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
                System.out.println(parameters);
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
            System.out.println("key" + key);
            System.out.println("value" + value);

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

    public Boolean createLevelQuizz(String question, ArrayList<String> responses, String correctAnswer, String difficulty){
        HttpRequest httpRequest = new HttpRequest();
        JsonObject parameters = Json.createObjectBuilder()
                .add("question", question)
                .add("id_game", 4)
                .add("name", question)
                .add("answer1", responses.get(0))
                .add("answer2", responses.get(1))
                .add("answer3", responses.get(2))
                .add("answer4", responses.get(3))
                .add("correct_answer", correctAnswer)
                .add("difficulty", difficulty)
                .build();

        httpRequest.sendPostRequest(parameters, "/levels/create/");
        DisplayerController displayerController = new DisplayerController();
        displayerController.displayInformation("Succed Creation Level");
        return true;
    }

    public List<LevelExplorer> getLevelsExplorer() {
        int id = 0;
        int idLevels = 0;
        String question = "";
        String answer1 = "";
        String answer2 = "";
        String answer3 = "";
        String correctAnswer = "";
        int difficulty = 1;

        HttpRequest http = new HttpRequest();
        List list = http.sendGetRequest("/questions");

        List<LevelExplorer> listLevelExplorer = new ArrayList<>();

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
                case "correctAnswer": {
                    correctAnswer = value;
                    listLevelExplorer.add(new LevelExplorer(id, idLevels, question, answer1, answer2, answer3, correctAnswer));
                    break;
                }
                default: {
                    break;
                }
            }
        }
        return listLevelExplorer;
    }

    public int createLevelExplorer(String question, ArrayList<String> answers, String correctAnswer){
        HttpRequest httpRequest = new HttpRequest();
        JsonObject parameters = Json.createObjectBuilder()
                .add("id_game", 2)
                .add("name", question)
                .add("question", question)
                .add("answer1", answers.get(0))
                .add("answer2", answers.get(1))
                .add("answer3", answers.get(2))
                .add("correctAnswer", correctAnswer)
                .add("difficulty", 1)
                .build();
        httpRequest.sendPostRequest(parameters, "/levels/create");
        DisplayerController displayerController = new DisplayerController();
        displayerController.displayInformation("Succed Creation Level");

        return 0;
    }

    public void updateLevels(String text) {}

    public Boolean deleteLevel(int idLevel){
        HttpRequest httpRequest = new HttpRequest();
        JsonObject parameters = Json.createObjectBuilder().build();

        System.out.println("/levels/" + Game.GAME_ID + "/" + idLevel);
        httpRequest.sendDeleteRequest("/levels/" + Game.GAME_ID + "/" + idLevel);
        DisplayerController displayerController = new DisplayerController();
        displayerController.displayInformation("Deleted !");
        return true;
    }
}
