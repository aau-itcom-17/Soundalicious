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
import org.xml.sax.SAXException;

import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.*;
import java.io.FileWriter;
import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class PlayGameScene extends FrontPageScene {

  RadioButton radioBut1, radioBut2, radioBut3, radioBut4;
  ToggleGroup question1;
  Label whoIsThis;
  Button playSound, nextQuestion, frontPageButton5;
  VBox playGameLayout;

  public PlayGameScene() throws IOException, SAXException, ParserConfigurationException {
    //Buttons
        radioBut1 = new RadioButton(rQuestions.get(0).getCorrectAnswer());
        radioBut2 = new RadioButton(rQuestions.get(0).getDummyAnswers1());
        radioBut3 = new RadioButton(rQuestions.get(0).getDummyAnswers2());
        radioBut4 = new RadioButton(rQuestions.get(0).getDummyAnswers3());
        question1 = new ToggleGroup();

        radioBut1.setToggleGroup(question1);
        radioBut2.setToggleGroup(question1);
        radioBut3.setToggleGroup(question1);
        radioBut4.setToggleGroup(question1);
    
        answers.add(radioBut1);
        answers.add(radioBut2);
        answers.add(radioBut3);
        answers.add(radioBut4);

        Collections.shuffle(answers);
    
        //ALL THIS IS PLAYING THE GAME:
        //Button frontPageButton4 = new Button("Go back to front page");
        //frontPageButton4.setOnAction(f -> window.setScene(frontpageScene));

        //Label
        whoIsThis = new Label("Who is this?");
        //Buttons
        //Play Sound
        playSound = new Button ("Play Sound");
        playSound.setOnAction(new EventHandler<ActionEvent>()  {
            @Override
            public void handle(ActionEvent event) {
                  Soundfiles.kanyeSound();
            }
        });
        //Button beyonceBut = new Button("Beyonce");
        //Button kanyeBut = new Button("Kanye West");
        //Button jayzBut = new Button("Jay-Z");
        //Button eminemBut = new Button("Eminem");

        //Next Question button
        nextQuestion = new Button("Next Question");
        nextQuestion.setOnAction(f -> {
          
            radioBut1.setSelected(false);
            radioBut2.setSelected(false);
            radioBut3.setSelected(false);
            radioBut4.setSelected(false);
          
          if (Constants.click < QuickPlayScene.numOfTeams) { //Multiple click on answers
                Constants.click++;
                /*if (Constants.click == QuickPlayScene.numOfTeams) {
                    window.setScene(playGameScene);
                } else {*/
                if (radioBut1.isSelected()) {
                    System.out.println("Good job");
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong answer!");
                    alert.showAndWait();
                }
                if (Constants.click == QuickPlayScene.numOfTeams) {
                    window.setScene(frontPageScene);
                    Soundfiles.noSound();
                    Constants.click = 0;
                }
            }
          
/*          Soundfiles.noSound();
            if (radioBut1.isSelected())
            {
                //Soundfiles.countPoints();
                
            }
            //if (beyonceBut.isSelected() || jayzBut.isSelected() || eminemBut.isSelected())
            else
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Wrong answer!");
                alert.showAndWait();
            }
            window.setScene(frontPageScene);
          //  Soundfiles.resetCountPoints(); */
        });

        //Button back to front on custom game page
        frontPageButton5 = new Button(Constants.goToMainText);
        frontPageButton5.setOnAction(e -> window.setScene(frontPageScene));

        //Layout for playing the game
        playGameLayout = new VBox(20);
        playGameLayout.setAlignment(Pos.CENTER);
        playGameLayout.getChildren().addAll(whoIsThis, playSound, answers.get(0), answers.get(1), answers.get(2), answers.get(3), nextQuestion, frontPageButton5);
        playGameScene = new Scene(playGameLayout, 400, 600);

      playGameScene.getStylesheets().add("Theme.css");
      window.setScene(playGameScene);

    }

}
