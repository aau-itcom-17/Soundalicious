import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class FinalScoreboardPageScene extends Main
{
    VBox layoutFinalScoreboard;
    Label gameName, gold, silver, bronze, t1, t2, t3;
    Button finGame;

    public FinalScoreboardPageScene(){

        gameName = new Label("Leaderboard");
        gameName.getStyleClass().add("label-board");
        gold = new Label();
        gold.getStyleClass().add("label-scores");
        silver = new Label();
        silver.getStyleClass().add("label-scores");
        bronze = new Label();
        bronze.getStyleClass().add("label-scores");
        t1 = new Label();
        t1.getStyleClass().add("label-team");
        t2 = new Label();
        t2.getStyleClass().add("label-team");
        t3 = new Label();
        t3.getStyleClass().add("label-team");


        if (teams.size() == 1) {
            gold.setText("Gold: " + teams.get(Team.getWinner()).getTeamName() + " with " + Integer.toString(teams.get(Team.getWinner()).getPointScore()) + " points");
            gold.setStyle("-fx-text-fill: #ffd700");
        }
        if (teams.size() == 2) {

            gold.setText("Gold: " + teams.get(Team.getWinner()).getTeamName() + " with " + Integer.toString(teams.get(Team.getWinner()).getPointScore()) + " points");
            gold.setStyle("-fx-text-fill: #ffd700");

            if (Team.getSameScore() != 0) //If two teams have same amount of points
            {
                silver.setText("Gold: " + teams.get(Team.getSameScore()).getTeamName() + " with " + Integer.toString(teams.get(Team.getSameScore()).getPointScore()) + " points");
                silver.setStyle("-fx-text-fill: #ffd700");
            }
            else
            {
                silver.setText("Silver: " + teams.get(Team.get2ndPlace()).getTeamName() + " with " + Integer.toString(teams.get(Team.get2ndPlace()).getPointScore()) + " points");
                silver.setStyle("-fx-text-fill: #c0c0c0");
            }
        }
        if (teams.size() >= 3) {

            gold.setText("Gold: " + teams.get(Team.getWinner()).getTeamName() + " with " + Integer.toString(teams.get(Team.getWinner()).getPointScore()) + " points");
            gold.setStyle("-fx-text-fill: #ffd700");

            if (teams.get(Team.getSameScore()).getPointScore() == teams.get(Team.getWinner()).getPointScore()) //If two teams have same amount of points
            {
                silver.setText("Gold: " + teams.get(Team.getSameScore()).getTeamName() + " with " + Integer.toString(teams.get(Team.getSameScore()).getPointScore()) + " points");
                silver.setStyle("-fx-text-fill: #ffd700");

                bronze.setText("Silver: " + teams.get(Team.get2ndPlace()).getTeamName() + " with " + Integer.toString(teams.get(Team.get2ndPlace()).getPointScore()) + " points");
                bronze.setStyle("-fx-text-fill: #c0c0c0");
            }
            else if (teams.get(Team.getSameScore()).getPointScore() == teams.get(Team.get2ndPlace()).getPointScore())
            {
                silver.setText("Silver: " + teams.get(Team.get2ndPlace()).getTeamName() + " with " + Integer.toString(teams.get(Team.get2ndPlace()).getPointScore()) + " points");
                silver.setStyle("-fx-text-fill: #c0c0c0");

                bronze.setText("Silver: " + teams.get(Team.getSameScore()).getTeamName() + " with " + Integer.toString(teams.get(Team.getSameScore()).getPointScore()) + " points");
                bronze.setStyle("-fx-text-fill: #c0c0c0");
            }
            else
            {
                silver.setText("Silver: " + teams.get(Team.get2ndPlace()).getTeamName() + " with " + Integer.toString(teams.get(Team.get2ndPlace()).getPointScore()) + " points");
                silver.setStyle("-fx-text-fill: #c0c0c0");

                bronze.setText("Bronze: " + teams.get(Team.get3rdPlace()).getTeamName() + " with " + Integer.toString(teams.get(Team.get3rdPlace()).getPointScore()) + " points");
                bronze.setStyle("-fx-text-fill: #cd7f32");
            }
        }


        finGame = new Button("Exit game");
        finGame.getStyleClass().add("button-menu");

        finGame.setOnAction(f -> {
            teams.clear();
            n = 0;
            rQuestions.clear();
            answers.clear();
            if (LogInScene.loggedIn == true){
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

        layoutFinalScoreboard = new VBox(20);
        layoutFinalScoreboard.setAlignment(Pos.CENTER);
        layoutFinalScoreboard.getChildren().addAll(gameName, t1, gold, t2, silver, t3,  bronze, finGame);
        finalScoreBoardPageScene = new Scene(layoutFinalScoreboard, 400, 700);
        finalScoreBoardPageScene.getStylesheets().add("Theme.css");

        window.setScene(finalScoreBoardPageScene);
        }
    }

