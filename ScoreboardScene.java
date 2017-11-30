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


public class ScoreboardScene extends Application{
    public int score1 = 0;
    public int score2 = 0;
    public int score3 = 0;
    public int score4 = 0;
    public int score5 = 0;

    Scene Scoreboardscene;
    VBox scoreboard;
    Label gameName, team1, team2, team3, team4, team5, t1, t2, t3, t4, t5;
    Button nextQuest;

    public static void main (String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){

        gameName = new Label(Constants.gameName);
        gameName.getStyleClass().add("label-headline");

        team1 = new Label("Team 1");

        t1 = new Label();
        t1.setText(Integer.toString(score1));

        team2 = new Label("Team 2");

        t2 = new Label();
        t2.setText(Integer.toString(score2));

        team3 = new Label("Team 3");

        t3 = new Label();
        t3.setText(Integer.toString(score3));

        team4 = new Label("Team 4");

        t4 = new Label();
        t4.setText(Integer.toString(score4));

        team5 = new Label("Team 5");

        t5 = new Label();
        t5.setText(Integer.toString(score5));

        nextQuest = new Button("Next Question");

        scoreboard = new VBox(20);
        scoreboard.setPrefSize(400, 600);
        scoreboard.setAlignment(Pos.CENTER);
        scoreboard.getChildren().addAll(gameName, team1, t1, team2, t2, team3, t3, team4, t4, team5, t5, nextQuest);
        scoreboard.getStylesheets().add("Theme.css");

        Scoreboardscene = new Scene(scoreboard);
        primaryStage.setScene(Scoreboardscene);
        primaryStage.show();

    }
}