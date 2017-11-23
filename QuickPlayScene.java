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

import javax.swing.*;
import java.io.IOException;

public class QuickPlayScene extends Main {

  Label labelQuick, labelChoiceBox, labelChoiceBox2;
  ChoiceBox<String> choiceBox, choiceBox2;
  Button startGameButton, buttonHowToPlay, frontPageButton1;
  VBox quickPlayLayout;

  public QuickPlayScene(){

    //Label Quick play
    labelQuick = new Label(Constants.gameName);

    //Amount of teams in a choice box
    labelChoiceBox = new Label(Constants.numOfTeamsTitle);
    choiceBox = new ChoiceBox<>();
    choiceBox.getItems().addAll(Constants.teamChoice1, Constants.teamChoice2, Constants.teamChoice3, Constants.teamChoice4, Constants.teamChoice5);
    //Set default value
    choiceBox.setValue(Constants.teamChoice1);

    //Amount of questions in a choice box
    labelChoiceBox2 = new Label(Constants.numOfQuestionsTitle);
    choiceBox2 = new ChoiceBox<>();
    choiceBox2.getItems().addAll(Constants.questionChoice1, Constants.questionChoice2, Constants.questionChoice3);        //Set default value
    //Set default value
    choiceBox2.setValue(Constants.questionChoice1);

    //Quick Play play button button
    startGameButton = new Button(Constants.startGameText);
    startGameButton.setOnAction(e -> new PlayGameScene());

    //How to play button button
    buttonHowToPlay = new Button(Constants.howToPlayText);
    buttonHowToPlay.setOnAction(e -> new HowToPlayScene());


    //Button back to front on Quick play page
    frontPageButton1 = new Button(Constants.goToMainText);
    frontPageButton1.setOnAction(e -> new FrontPageScene());

    //Layout quickplay
    quickPlayLayout = new VBox(20);
    quickPlayLayout.setAlignment(Pos.CENTER);
    quickPlayLayout.getChildren().addAll(labelQuick, labelChoiceBox, choiceBox, labelChoiceBox2, choiceBox2, startGameButton, buttonHowToPlay, frontPageButton1);
    quickPlayScene = new Scene(quickPlayLayout, 500, 500);

    window.setScene(quickPlayScene);

  }
}
