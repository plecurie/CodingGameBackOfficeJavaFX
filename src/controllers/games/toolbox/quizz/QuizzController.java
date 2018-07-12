package controllers.games.toolbox.quizz;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.awt.*;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import services.dao.DAOLevel;

import java.io.Console;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class QuizzController implements Initializable{
    @FXML private TextField edt_response1;
    @FXML private TextField edt_response2;
    @FXML private TextField edt_response3;
    @FXML private TextField edt_response4;
    @FXML private ChoiceBox<Integer> cb_correct_answer;
    @FXML private Button btn_valid_create_question;
    @FXML private TextField edt_question;



    @FXML private void onSaveQuestion (ActionEvent actionEvent) {
        //System.out.println(edt_question.getText());

        ArrayList<String> responses = new ArrayList<>();
        responses.add(edt_response1.getText());
        responses.add(edt_response2.getText());
        responses.add(edt_response3.getText());
        responses.add(edt_response4.getText());


        String idGoodAnswer = String.valueOf(cb_correct_answer.getValue());
        System.out.println(idGoodAnswer);
        String correctAnswer = "";

        switch (idGoodAnswer){
            case("1"):
                correctAnswer = edt_response1.getText();
                break;
            case ("2"):
                correctAnswer = edt_response2.getText();
                break;
            case("3"):
                correctAnswer = edt_response3.getText();
                break;
            case ("4"):
                correctAnswer = edt_response4.getText();
                break;
            default:
                System.out.println("rien");
                break;
        }


        DAOLevel daoLevel = null;
        daoLevel.createLevelQuizz(edt_question.getText(), responses, correctAnswer);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Integer> choice = new ArrayList<>();
        choice.add(1);
        choice.add(2);
        choice.add(3);
        choice.add(4);
        cb_correct_answer.setItems(FXCollections.observableArrayList(choice));
        cb_correct_answer.setValue(choice.get(0));
    }
}
