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

        team1 = new Label("Team 1");

        t1 = new Label();
        t1.setText(Integer.toString(score1));

        team2 = new Label("Team 2");

        t2 = new Label();
        t2.setText(Integer.toString(score2));

        team3 = new Label("Team 3");

        t3 = new Label("0");
        t3.setText(Integer.toString(score3));

        team4 = new Label("Team 4");

        t4 = new Label();
        t4.setText(Integer.toString(score4));

        team5 = new Label("Team 5");

        t5 = new Label("0");
        t5.setText(Integer.toString(score5));

        nextQuest = new Button("Next Question");
        nextQuest.setOnAction(e -> window.setScene(playGameScene));

        finGame = new Button("Finish game");
        finGame.setOnAction(e -> window.setScene(frontPageScene));

        layoutScoreboard = new VBox(20);
        layoutScoreboard.setAlignment(Pos.CENTER);
        layoutScoreboard.getChildren().addAll(gameName, team1, t1, team2, t2, team3, t3, team4, t4, team5, t5, nextQuest, finGame);
        Scene scoreboardScene = new Scene(layoutScoreboard, 400, 600);
        scoreboardScene.getStylesheets().add("Theme.css");

        window.setScene(scoreboardScene);
    }
}
