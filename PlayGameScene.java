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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.*;
import java.io.FileWriter;
import javax.swing.*;
import java.io.IOException;

public class PlayGameScene extends FrontPageScene {

  RadioButton radioBut1, radioBut2, radioBut3, radioBut4;
  ToggleGroup question1;
  Label whoIsThis;
  Button playSound, nextQuestion, frontPageButton5;
  VBox playGameLayout;

  public PlayGameScene(){
    //Buttons
        radioBut1 = new RadioButton(Constants.answer1);
        radioBut2 = new RadioButton(Constants.answer2);
        radioBut3 = new RadioButton(Constants.answer3);
        radioBut4 = new RadioButton(Constants.answer4);
        question1 = new ToggleGroup();

        radioBut1.setToggleGroup(question1);
        radioBut2.setToggleGroup(question1);
        radioBut3.setToggleGroup(question1);
        radioBut4.setToggleGroup(question1);
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
            if (radioBut1.isSelected())
            {
                //Soundfiles.countPoints();
                Soundfiles.noSound();
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
          //  Soundfiles.resetCountPoints();
        });

        //Button back to front on custom game page
        frontPageButton5 = new Button(Constants.goToMainText);
        frontPageButton5.setOnAction(e -> window.setScene(frontPageScene));

        //Layout for playing the game
        playGameLayout = new VBox(20);
        playGameLayout.setAlignment(Pos.CENTER);
        playGameLayout.getChildren().addAll(whoIsThis, playSound, radioBut1, radioBut2, radioBut3, radioBut4, nextQuestion, frontPageButton5);
        playGameScene = new Scene(playGameLayout, 400, 600);

      playGameScene.getStylesheets().add("Theme.css");
      window.setScene(playGameScene);

    }

}
