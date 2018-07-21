package controllers.games.toolbox.explorer;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import services.dao.DAOLevel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ExplorerController implements Initializable {

    @FXML private TextField edt_question;
    @FXML private TextField edt_response1;
    @FXML private TextField edt_response2;
    @FXML private TextField edt_response3;
    @FXML private ChoiceBox<Integer> cb_correct_answer;
    @FXML private Button btn_validate_question;

    private DAOLevel daoLevel = new DAOLevel();


    @FXML private void onSaveQuestion (ActionEvent actionEvent) {
        ArrayList<String> answers = new ArrayList<>();
        answers.add(edt_response1.getText());
        answers.add(edt_response2.getText());
        answers.add(edt_response3.getText());

        String idCorrectAnswer = String.valueOf(cb_correct_answer.getValue());
        String correctAnswer = "";

        switch(idCorrectAnswer){
            case("1"):
                correctAnswer = edt_response1.getText();
                break;
            case("2"):
                correctAnswer = edt_response2.getText();
                break;
            case("3"):
                correctAnswer = edt_response3.getText();
                break;
            default:
                System.out.println("rien");
                break;
        }

        daoLevel.createLevelAdventure(edt_question.getText(), answers, correctAnswer);
    }


    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Integer> choice = new ArrayList<>();
        choice.add(1);
        choice.add(2);
        choice.add(3);
        cb_correct_answer.setItems(FXCollections.observableArrayList(choice));
        cb_correct_answer.setValue(choice.get(0));
    }
}
