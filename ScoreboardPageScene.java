import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.print.attribute.standard.Finishings;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class ScoreboardPageScene extends Main{

    VBox layoutScoreboard;
    Label gameName, team1, team2, team3, team4, team5, t1, t2, t3, t4, t5;
    Label answer1, answer2, answer3, answer4, answer5, correctA;
    Button nextQuest, finGame;
    String finalOrQuest = null;

    public ScoreboardPageScene(){

        gameName = new Label("Scoreboard");
        gameName.getStyleClass().add("label-board");
        correctA = new Label("Correct answer: " + PlayGameScene.checkCorrect);
        team1 = new Label();
        team1.getStyleClass().add("label-scores");
        team2 = new Label();
        team2.getStyleClass().add("label-scores");
        team3 = new Label();
        team3.getStyleClass().add("label-scores");
        team4 = new Label();
        team4.getStyleClass().add("label-scores");
        team5 = new Label();
        team5.getStyleClass().add("label-scores");
        t1 = new Label();
        t1.getStyleClass().add("label-team");
        t2 = new Label();
        t2.getStyleClass().add("label-team");
        t3 = new Label();
        t3.getStyleClass().add("label-team");
        t4 = new Label();
        t4.getStyleClass().add("label-team");
        t5 = new Label();
        t5.getStyleClass().add("label-team");
        answer1 = new Label();
        answer2 = new Label();
        answer3 = new Label();
        answer4 = new Label();
        answer5 = new Label();


         if (teams.size() >= 1) {
            team1.setText(teams.get(0).getTeamName() + ": " + Integer.toString(teams.get(0).getPointScore()) + " points");
           // t1.setText(Integer.toString(teams.get(0).getPointScore()));
            if(PlayGameScene.checkCorrect.equals(PlayGameScene.playersChoices[0])){
                answer1.setStyle("-fx-text-fill: green");
            } else  {
                answer1.setStyle("-fx-text-fill: red");
            }
            answer1.setText("Selected answer: " + PlayGameScene.playersChoices[0]);
        }
        if (teams.size() >= 2) {
            team2.setText(teams.get(1).getTeamName() + ": " + Integer.toString(teams.get(1).getPointScore()) + " points");
            //t2.setText(Integer.toString(teams.get(1).getPointScore()));
            if(PlayGameScene.checkCorrect.equals(PlayGameScene.playersChoices[1])){
                answer2.setStyle("-fx-text-fill: green");
            } else  {
                answer2.setStyle("-fx-text-fill: red");
            }
            answer2.setText("Selected answer: " + PlayGameScene.playersChoices[1]);
        }
        if (teams.size() >= 3) {
            team3.setText(teams.get(2).getTeamName() + ": " + Integer.toString(teams.get(2).getPointScore()) + " points");
           // t3.setText(Integer.toString(teams.get(2).getPointScore()));
            if(PlayGameScene.checkCorrect.equals(PlayGameScene.playersChoices[2])){
                answer3.setStyle("-fx-text-fill: green");
            } else  {
                answer3.setStyle("-fx-text-fill: red");
            }
            answer3.setText("Selected answer: " + PlayGameScene.playersChoices[2]);
        }
        if (teams.size() >= 4) {
            team4.setText(teams.get(3).getTeamName() + ": " + Integer.toString(teams.get(3).getPointScore()) + " points");
            //t4.setText(Integer.toString(teams.get(3).getPointScore()));
            if(PlayGameScene.checkCorrect.equals(PlayGameScene.playersChoices[3])){
                answer4.setStyle("-fx-text-fill: green");
            } else  {
                answer4.setStyle("-fx-text-fill: red");
            }
            answer4.setText("Selected answer: " + PlayGameScene.playersChoices[3]);
        }

        if (teams.size() == 5) {
            team5.setText(teams.get(4).getTeamName() + ": " + Integer.toString(teams.get(4).getPointScore()) + " points");
            //t5.setText(Integer.toString(teams.get(4).getPointScore()));
            if(PlayGameScene.checkCorrect.equals(PlayGameScene.playersChoices[4])){
                answer5.setStyle("-fx-text-fill: green");
            } else  {
                answer5.setStyle("-fx-text-fill: red");
            }
            answer5.setText("Selected answer: " + PlayGameScene.playersChoices[4]);
        }


        if (n == QuickPlayScene.numOfQuestions){
            finalOrQuest = "Leaderboard";
        } else{
            finalOrQuest = "Next Question";
        }
        nextQuest = new Button(finalOrQuest);
        nextQuest.getStyleClass().add("button-continue");
        nextQuest.setOnAction(e -> {
            if (n == QuickPlayScene.numOfQuestions) {
                new FinalScoreboardPageScene();
            } else {
                try {
                    new PlayGameScene();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (SAXException e1) {
                    e1.printStackTrace();
                } catch (ParserConfigurationException e1) {
                    e1.printStackTrace();
                }
                Constants.click++;
            }

        });

        finGame = new Button("Finish game");
        finGame.getStyleClass().add("button-menu");
        finGame.setOnAction(f -> {
            teams.clear();
            n = 0;
            rQuestions.clear();
            answers.clear();
            if (LogInScene.loggedIn == true) {
                window.setScene(frontPageSceneLoggedIn);
            } else {
                window.setScene(frontPageScene);
            }
            Constants.click = 1;
            try {
                Questions.getRandomQuestions(questions, rQuestions);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        layoutScoreboard = new VBox(20);
        layoutScoreboard.setAlignment(Pos.CENTER);
        layoutScoreboard.getChildren().addAll(gameName, correctA, t1, team1, answer1, t2, team2, answer2, t3,  team3, answer3,  t4, team4, answer4,  t5, team5, answer5, nextQuest, finGame);
        scoreBoardPageScene = new Scene(layoutScoreboard, 400, 700);
        scoreBoardPageScene.getStylesheets().add("Theme.css");

        window.setScene(scoreBoardPageScene);
        

    }
}
