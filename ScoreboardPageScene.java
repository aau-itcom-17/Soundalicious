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

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class ScoreboardPageScene extends Main{

    VBox layoutScoreboard;
    Label gameName, team1, team2, team3, team4, team5, t1, t2, t3, t4, t5;
    Button nextQuest, finGame;

    public ScoreboardPageScene(){

        gameName = new Label(Constants.gameName);
        gameName.getStyleClass().add("label-headline");
        team1 = new Label();
        team2 = new Label();
        team3 = new Label();
        team4 = new Label();
        team5 = new Label();
        t1 = new Label();
        t2 = new Label();
        t3 = new Label();
        t4 = new Label();
        t5 = new Label();

        if (teams.size() >= 1) {
            team1.setText(teams.get(0).getTeamName());
            t1.setText(Integer.toString(teams.get(0).getPointScore()));
        }
        if (teams.size() >= 2) {
            team2.setText(teams.get(1).getTeamName());
            t2.setText(Integer.toString(teams.get(1).getPointScore()));
        }
        if (teams.size() >= 3) {
            team3.setText(teams.get(2).getTeamName());
            t3.setText(Integer.toString(teams.get(2).getPointScore()));
        }
        if (teams.size() >= 4) {
            team4.setText(teams.get(3).getTeamName());
            t4.setText(Integer.toString(teams.get(3).getPointScore()));
        }

        if (teams.size() == 5) {
            team5.setText(teams.get(4).getTeamName());
            t5.setText(Integer.toString(teams.get(4).getPointScore()));
        }


        nextQuest = new Button("Next Question");
        nextQuest.setOnAction(e -> {
            if (Constants.click == QuickPlayScene.numOfQuestions) {
                window.setScene(frontPageScene);
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
        finGame.setOnAction(e -> window.setScene(frontPageScene));

        layoutScoreboard = new VBox(20);
        layoutScoreboard.setAlignment(Pos.CENTER);
        layoutScoreboard.getChildren().addAll(gameName, team1, t1, team2, t2, team3, t3, team4, t4, team5, t5, nextQuest, finGame);
        scoreBoardPageScene = new Scene(layoutScoreboard, 400, 600);
        scoreBoardPageScene.getStylesheets().add("Theme.css");

        window.setScene(scoreBoardPageScene);
    }
}
