import javafx.application.*;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class QuickPlayScene extends Main {

  Label labelQuick, labelChoiceBox, labelChoiceBox2;
  ChoiceBox<String> choiceBox, choiceBox2;
  Button startGameButton, buttonHowToPlay, frontPageButton1;
  VBox quickPlayLayout;

  public QuickPlayScene(){

    //Label Quick play
    labelQuick = new Label(Constants.gameName);
    labelQuick.getStyleClass().add("label-headline");

    //Amount of teams in a choice box
    labelChoiceBox = new Label(Constants.numOfTeamsTitle);
    choiceBox = new ChoiceBox<>();
    choiceBox.getItems().addAll(Constants.teamChoice0, Constants.teamChoice1, Constants.teamChoice2, Constants.teamChoice3, Constants.teamChoice4, Constants.teamChoice5);
    //Set default value
    choiceBox.setValue(Constants.teamChoice0);
    choiceBox.setOnAction(e -> {
      switch (choiceBox.getValue()){
        case "1 Team":
          numOfTeams = Constants.teamChoice1Num;
          break;
        case "2 Teams":
          numOfTeams = Constants.teamChoice2Num;
          break;
        case "3 Teams":
          numOfTeams = Constants.teamChoice3Num;
          break;
        case "4 Teams":
          numOfTeams = Constants.teamChoice4Num;
          break;
        case "5 Teams":
          numOfTeams = Constants.teamChoice5Num;
          break;
        default:
          numOfTeams = Constants.teamChoice1Num;
          break;
      }

    });

    //Amount of questions in a choice box
    labelChoiceBox2 = new Label(Constants.numOfQuestionsTitle);
    choiceBox2 = new ChoiceBox<>();
    choiceBox2.getItems().addAll(Constants.questionChoice1, Constants.questionChoice2, Constants.questionChoice3);        //Set default value
    //Set default value
    choiceBox2.setValue(Constants.questionChoice1);

    //Quick Play play button button
    startGameButton = new Button(Constants.startGameText);
    startGameButton.getStyleClass().add("button-continue");
    startGameButton.setOnAction(e -> {
      //sets numOfTeams according to choice box

      //sets numOfQuestions according
      //to choice box
      switch (choiceBox2.getValue()){
        case "10 questions":
          numOfQuestions = Constants.questionChoice1Num;
          break;
        case "15 questions":
          numOfQuestions = Constants.questionChoice2Num;
          break;
        case "25 questions":
          numOfQuestions = Constants.questionChoice3Num;
          break;
        default:
          numOfQuestions = Constants.questionChoice1Num;
          break;
      }
      new TeamNameScene();

    });

    //How to play button button
    buttonHowToPlay = new Button(Constants.howToPlayText);
    buttonHowToPlay.getStyleClass().add("button-menu");
    buttonHowToPlay.setOnAction(e -> new HowToPlayScene());


    //Button back to front on Quick play page
    frontPageButton1 = new Button(Constants.goToMainText);
    frontPageButton1.getStyleClass().add("button-menu");
    frontPageButton1.setOnAction(e -> window.setScene(frontPageScene));

    //Layout quickplay
    quickPlayLayout = new VBox(20);
    quickPlayLayout.setAlignment(Pos.CENTER);
    quickPlayLayout.getChildren().addAll(labelQuick, choiceBox, choiceBox2, startGameButton, buttonHowToPlay);
    quickPlayScene = new Scene(quickPlayLayout, 400, 700);

    quickPlayScene.getStylesheets().add("Theme.css");
    window.setScene(quickPlayScene);

  }
}
