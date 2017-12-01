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

    private int i;
    RadioButton radioBut1, radioBut2, radioBut3, radioBut4;
    ToggleGroup question1;
    Label whoIsThis;
    Button playSound, nextQuestion, frontPageButton5;
    VBox playGameLayout;



    public PlayGameScene() throws IOException, SAXException, ParserConfigurationException {

        //Buttons
        radioBut1 = new RadioButton(rQuestions.get(n).getCorrectAnswer());
        radioBut2 = new RadioButton(rQuestions.get(n).getDummyAnswers1());
        radioBut3 = new RadioButton(rQuestions.get(n).getDummyAnswers2());
        radioBut4 = new RadioButton(rQuestions.get(n).getDummyAnswers3());
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

        //Next Question button
        nextQuestion = new Button("Next Question");
        nextQuestion.setOnAction(f -> {

            if (radioBut1.isSelected())
            {
                PointSystem.countPoints();
            }
                    n++;
                    Soundfiles.noSound();
                    answers.clear();

                    try {
                        new PlayGameScene();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SAXException e) {
                        e.printStackTrace();
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    }

            if (n == QuickPlayScene.numOfQuestions)
            {
                window.setScene(frontPageScene);
                PointSystem.showPoints();
                PointSystem.resetCountPoints();

                n = 0;
                rQuestions.clear();
                answers.clear();
                try {
                    Questions.getRandomQuestions(questions, rQuestions);
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            /*
            //MORE TEAMS

            if (Constants.click < QuickPlayScene.numOfTeams) {
                //Multiple click on answers
                System.out.println("I'm here");
                Constants.click++;

                window.setScene(playGameScene);
                if (radioBut1.isSelected())
                {
                    PointSystem.countPoints();
                }

                if (Constants.click == QuickPlayScene.numOfTeams) {
                    //window.setScene(frontPageScene);
                    Soundfiles.noSound();
                    Constants.click = 0;
                }
            }
            */

            radioBut1.setSelected(false);
            radioBut2.setSelected(false);
            radioBut3.setSelected(false);
            radioBut4.setSelected(false);
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
