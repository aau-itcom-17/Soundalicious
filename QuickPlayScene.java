import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
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
  Button teamChoice, questionsChoice;
  Label teamNumLabel, questNumLabel;
  ChoiceBox<String> choiceBox, choiceBox2;
  Button startGameButton, buttonHowToPlay, frontPageButton1, minButtonT, plusButtonT, minButtonQ, plusButtonQ;
  VBox quickPlayLayout;

  public QuickPlayScene(){

    //Label Quick play
    labelQuick = new Label(Constants.quickPlayName);
    labelQuick.getStyleClass().add("label-headline");
    teamNumLabel = new Label("Choose number of teams");
    questNumLabel = new Label("Choose number of questions");


    HBox hbox1 = new HBox();
    minButtonT = new Button("-");
    minButtonT.getStyleClass().add("controlButtonMinus");
    minButtonT.setStyle("-fx-background-color: #eaf2ff");
    minButtonT.setOnAction(e -> {
              if(tCount == 1){
                tCount--;
                teamChoice.setText(Constants.teamNumNames[tCount]);
                numOfTeams = Constants.teamNums[tCount];
                plusButtonT.setStyle(null);
                minButtonT.setStyle("-fx-background-color: #eaf2ff");
              }
              else if (tCount > 0) {
                tCount--;
                teamChoice.setText(Constants.teamNumNames[tCount]);
                numOfTeams = Constants.teamNums[tCount];
                plusButtonT.setStyle(null);
              }
            });
    teamChoice = new Button(Constants.teamNumNames[tCount]);
    teamChoice.getStyleClass().add("controlText");
    plusButtonT = new Button("+");
    plusButtonT.getStyleClass().add("controlButtonPlus");
    plusButtonT.setOnAction(e -> {
              if(tCount == 3){
                tCount++;
                teamChoice.setText(Constants.teamNumNames[tCount]);
                numOfTeams = Constants.teamNums[tCount];
                minButtonT.setStyle(null);
                plusButtonT.setStyle("-fx-background-color: #eaf2ff");
              }
              else if(tCount < 4) {
                tCount++;
                teamChoice.setText(Constants.teamNumNames[tCount]);
                numOfTeams = Constants.teamNums[tCount];
                minButtonT.setStyle(null);
              }
    }
    );


    hbox1.setAlignment(Pos.CENTER);
    hbox1.getChildren().addAll(minButtonT, teamChoice, plusButtonT);

    HBox hbox2 = new HBox();
    minButtonQ = new Button("-");
    minButtonQ.getStyleClass().add("controlButtonMinus");
    minButtonQ.setOnAction(e -> {
      if(qCount == 1){
        qCount--;
        questionsChoice.setText(Constants.questionNumNames[qCount]);
        numOfQuestions= Constants.questionNums[qCount];
        plusButtonQ.setStyle(null);
        minButtonQ.setStyle("-fx-background-color: #eaf2ff");
      }
      else if (qCount > 0) {
        qCount--;
        questionsChoice.setText(Constants.questionNumNames[qCount]);
        numOfQuestions = Constants.teamNums[qCount];
        plusButtonQ.setStyle(null);
      }
    });
    questionsChoice = new Button(Constants.questionNumNames[qCount]);
    questionsChoice.getStyleClass().add("controlText");
    plusButtonQ = new Button("+");
    plusButtonQ.getStyleClass().add("controlButtonPlus");
    plusButtonQ.setOnAction(e -> {
              if(qCount == 4){
                qCount++;
                questionsChoice.setText(Constants.questionNumNames[qCount]);
                numOfQuestions = Constants.questionNums[qCount];
                minButtonQ.setStyle(null);
                plusButtonQ.setStyle("-fx-background-color: #eaf2ff");
              }
              else if (qCount < 5) {
                qCount++;
                questionsChoice.setText(Constants.questionNumNames[qCount]);
                numOfQuestions = Constants.questionNums[qCount];
                minButtonQ.setStyle(null);
              }
            }
    );
    hbox2.setAlignment(Pos.CENTER);
    hbox2.getChildren().addAll(minButtonQ, questionsChoice, plusButtonQ);


    //Quick Play play button button
    startGameButton = new Button(Constants.startGameText);
    startGameButton.getStyleClass().add("button-continue");
    startGameButton.setOnAction(e -> {
      //sets numOfQuestions according to choice box
      new TeamNameScene();

    });

    //How to play button button
    buttonHowToPlay = new Button(Constants.howToPlayText);
    buttonHowToPlay.getStyleClass().add("button-menu");
    buttonHowToPlay.setOnAction(e -> {
      new HowToPlayScene();
    });


    //Button back to front on custom game page
    frontPageButton1 = new Button(Constants.goToMainText);
    frontPageButton1.setOnAction(e -> {
        teams.clear();
        n = 0;
        answers.clear();
        numOfTeams = 1;
        window.setScene(frontPageScene);

    });
    frontPageButton1.getStyleClass().add("button-menu");

    //Layout quickplay
    quickPlayLayout = new VBox(20);
    quickPlayLayout.setAlignment(Pos.CENTER);
    quickPlayLayout.getChildren().addAll(labelQuick, teamNumLabel, hbox1, questNumLabel, hbox2, startGameButton, buttonHowToPlay, frontPageButton1);
    quickPlayScene = new Scene(quickPlayLayout, 400, 700);

    quickPlayScene.getStylesheets().add("Theme.css");
    window.setScene(quickPlayScene);

  }
}
