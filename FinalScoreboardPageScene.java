import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Final Scoreboard Scene is displayed when all teams answered all questions.
 * It displays 3 lines of text with 3 team that scored the most points.
 * This class uses methods declared in Team class for finding the 3 winner teams.
 * A button at the bottom returns user to the main page.
 */
public class FinalScoreboardPageScene extends ScoreboardPageScene {
    private VBox layoutFinalScoreboard;
    private Label labelScreenTitle, gold, silver, bronze, t1, t2, t3;
    private Button buttonFinishGame;

    FinalScoreboardPageScene() throws IOException, SAXException, ParserConfigurationException {

        labelScreenTitle = new Label(Constants.nameLeaderboard);
        gold = new Label();
        silver = new Label();
        bronze = new Label();
        t1 = new Label();
        t2 = new Label();
        t3 = new Label();
        buttonFinishGame = new Button(Constants.nameExitGame);

        labelScreenTitle.getStyleClass().add("label-board");
        gold.getStyleClass().add("label-scores");
        silver.getStyleClass().add("label-scores");
        bronze.getStyleClass().add("label-scores");
        t1.getStyleClass().add("label-team");
        t2.getStyleClass().add("label-team");
        t3.getStyleClass().add("label-team");
        buttonFinishGame.getStyleClass().add("button-menu");

        if (teams.size() == 1) {
            gold.setText(Constants.nameGold + ": " + teams.get(Team.getWinner()).getTeamName() + " with " + Integer.toString(teams.get(Team.getWinner()).getPointScore()) + " " + Constants.namePoints);
            gold.setStyle("-fx-text-fill: #ffd700");
        }

        if (teams.size() == 2) {
            gold.setText(Constants.nameGold + ": " + teams.get(Team.getWinner()).getTeamName() + " with " + Integer.toString(teams.get(Team.getWinner()).getPointScore()) + " " + Constants.namePoints);
            gold.setStyle("-fx-text-fill: #ffd700");
            if (Team.getSameScore() != 0) //If two teams have same amount of points
            {
                silver.setText(Constants.nameGold + ": " + teams.get(Team.getSameScore()).getTeamName() + " with " + Integer.toString(teams.get(Team.getSameScore()).getPointScore()) + " " + Constants.namePoints);
                silver.setStyle("-fx-text-fill: #ffd700");
            } else {
                silver.setText(Constants.nameSilver + ": " + teams.get(Team.get2ndPlace()).getTeamName() + " with " + Integer.toString(teams.get(Team.get2ndPlace()).getPointScore()) + " " + Constants.namePoints);
                silver.setStyle("-fx-text-fill: #c0c0c0");
            }
        }

        if (teams.size() >= 3) {
            gold.setText(Constants.nameGold + ": " + teams.get(Team.getWinner()).getTeamName() + " with " + Integer.toString(teams.get(Team.getWinner()).getPointScore()) + " " + Constants.namePoints);
            gold.setStyle("-fx-text-fill: #ffd700");

            if (Team.getSameScoreForThree() > -1) {
                silver.setText(Constants.nameGold + ": " + teams.get(Team.getSameScore()).getTeamName() + " with " + Integer.toString(teams.get(Team.getSameScore()).getPointScore()) + " " + Constants.namePoints);
                silver.setStyle("-fx-text-fill: #ffd700");

                bronze.setText(Constants.nameGold + ": " + teams.get(Team.getSameScoreForThree()).getTeamName() + " with " + Integer.toString(teams.get(Team.getSameScoreForThree()).getPointScore()) + " " + Constants.namePoints);
                bronze.setStyle("-fx-text-fill: #ffd700");
            } else if (teams.get(Team.getSameScore()).getPointScore() == teams.get(Team.getWinner()).getPointScore()) //If two teams have same amount of points
            {
                silver.setText(Constants.nameGold + ": " + teams.get(Team.getSameScore()).getTeamName() + " with " + Integer.toString(teams.get(Team.getSameScore()).getPointScore()) + " " + Constants.namePoints);
                silver.setStyle("-fx-text-fill: #ffd700");

                bronze.setText(Constants.nameSilver + ": "  + teams.get(Team.get2ndPlace()).getTeamName() + " with " + Integer.toString(teams.get(Team.get2ndPlace()).getPointScore()) + " " + Constants.namePoints);
                bronze.setStyle("-fx-text-fill: #c0c0c0");
            } else if (teams.get(Team.getSameScore()).getPointScore() == teams.get(Team.get2ndPlace()).getPointScore()) {
                silver.setText(Constants.nameSilver + ": "  + teams.get(Team.get2ndPlace()).getTeamName() + " with " + Integer.toString(teams.get(Team.get2ndPlace()).getPointScore()) + " " + Constants.namePoints);
                silver.setStyle("-fx-text-fill: #c0c0c0");

                bronze.setText(Constants.nameSilver + ": "  + teams.get(Team.getSameScore()).getTeamName() + " with " + Integer.toString(teams.get(Team.getSameScore()).getPointScore()) + " " + Constants.namePoints);
                bronze.setStyle("-fx-text-fill: #c0c0c0");
            } else {
                silver.setText(Constants.nameSilver + ": "  + teams.get(Team.get2ndPlace()).getTeamName() + " with " + Integer.toString(teams.get(Team.get2ndPlace()).getPointScore()) + " " + Constants.namePoints);
                silver.setStyle("-fx-text-fill: #c0c0c0");

                bronze.setText(Constants.nameBronze + ": "  + teams.get(Team.get3rdPlace()).getTeamName() + " with " + Integer.toString(teams.get(Team.get3rdPlace()).getPointScore()) + " " + Constants.namePoints);
                bronze.setStyle("-fx-text-fill: #cd7f32");
            }
        }

        try {
            user.writeOnHistoryFile( Constants.nameTeam + " " + teams.get(Team.getWinner()).getTeamName() + " " + Constants.textTeamXWonAndGot +
                    " " + Integer.toString(teams.get(Team.getWinner()).getPointScore()) + "  " +  Constants.textXAnswerRightOutOfX + "  " + numOfQuestions + " " + Constants.textQuestions + ".");
        } catch (IOException e) {
            e.printStackTrace();
        }


        buttonFinishGame.setOnAction(f -> {

            if(!user.isLoggedIn) {
                user.setUserName("guest user");
            }
            try {
                user.writeOnHistoryFile("Team: " + teams.get(Team.getWinner()).getTeamName() + " won the game and got " + Integer.toString(teams.get(Team.getWinner()).getPointScore()) + " right answers out of " + numOfQuestions + " questions.");
            } catch (IOException e) {
                e.printStackTrace();
            }

            teams.clear();
            counterOfQuestions = 0;
            rQuestions.clear();
            answers.clear();
            numOfTeams = 1;
            numOfQuestions = 10;
            tCount = 0;
            qCount = 1;
            window.setScene(frontPageScene);

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

        layoutFinalScoreboard = new VBox(Constants.vBoxSpacing);
        layoutFinalScoreboard.setAlignment(Pos.CENTER);
        layoutFinalScoreboard.getChildren().addAll(labelScreenTitle, t1, gold, t2, silver, t3, bronze, buttonFinishGame);
        finalScoreBoardPageScene = new Scene(layoutFinalScoreboard, Constants.screenWidth, Constants.screenHeight);
        finalScoreBoardPageScene.getStylesheets().add(Constants.StyleSheetPath);

        window.setScene(finalScoreBoardPageScene);
    }
}

