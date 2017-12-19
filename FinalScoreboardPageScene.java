import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Comparator;

/**
 * Final Scoreboard Scene is displayed when all teams answered all questions.
 * It displays 3 lines of text with 3 team that scored the most points.
 * This class uses methods declared in Team class for finding the 3 winner teams.
 * A button at the bottom returns user to the main page.
 */
public class FinalScoreboardPageScene extends ScoreboardPageScene {
    private VBox layoutFinalScoreboard;
    private Label labelScreenTitle, labelPlaceName1, labelPlaceName2, labelPlaceName3, labelLeader1, labelLeader2, labelLeader3;
    private Button buttonFinishGame;

    FinalScoreboardPageScene() throws IOException, SAXException, ParserConfigurationException {

        labelScreenTitle = new Label(Constants.nameLeaderboard);
        labelPlaceName1 = new Label();
        labelPlaceName2 = new Label();
        labelPlaceName3 = new Label();
        labelLeader1 = new Label();
        labelLeader2 = new Label();
        labelLeader3 = new Label();
        buttonFinishGame = new Button(Constants.nameExitGame);

        labelScreenTitle.getStyleClass().add("label-board");
        labelPlaceName1.getStyleClass().add("label-scores");
        labelPlaceName2.getStyleClass().add("label-scores");
        labelPlaceName3.getStyleClass().add("label-scores");
        labelLeader1.getStyleClass().add("label-scores");
        labelLeader2.getStyleClass().add("label-scores");
        labelLeader3.getStyleClass().add("label-scores");
        buttonFinishGame.getStyleClass().add("button-continue");

        teams.sort(Comparator.comparingInt(Team::getPointScore).reversed());

        if (teams.size() == 1) {
            labelPlaceName1.setText(Constants.nameGold + ": ");
            labelPlaceName1.setStyle("-fx-background-color: gold; -fx-padding: 10px 30px 10px 30px; -fx-text-fill: #FFFFFF;");
            labelLeader1.setText(teams.get(0).getTeamName() + " with " + Integer.toString(teams.get(0).getPointScore()) + " " + Constants.namePoints);
        }
        if (teams.size() == 2) {
            if (teams.get(0).getPointScore() == teams.get(1).getPointScore()) {
                labelPlaceName1.setText(Constants.nameGold + ": ");
                labelPlaceName1.setStyle("-fx-background-color: gold; -fx-padding: 10px 30px 10px 30px; -fx-text-fill: #FFFFFF;");
                labelPlaceName2.setText(Constants.nameGold + ": ");
                labelPlaceName2.setStyle("-fx-background-color: gold; -fx-padding: 10px 30px 10px 30px; -fx-text-fill: #FFFFFF;");
                labelLeader1.setText(teams.get(0).getTeamName() + " with " + Integer.toString(teams.get(0).getPointScore()) + " " + Constants.namePoints);
                labelLeader2.setText(teams.get(1).getTeamName() + " with " + Integer.toString(teams.get(1).getPointScore()) + " " + Constants.namePoints);
            } else {
                labelPlaceName1.setText(Constants.nameGold + ": ");
                labelPlaceName1.setStyle("-fx-background-color: gold; -fx-padding: 10px 30px 10px 30px; -fx-text-fill: #FFFFFF;");
                labelPlaceName2.setText(Constants.nameSilver + ": ");
                labelPlaceName2.setStyle("-fx-background-color: darkgray; -fx-padding: 10px 30px 10px 30px; -fx-text-fill: #FFFFFF;");
                labelLeader1.setText(teams.get(0).getTeamName() + " with " + Integer.toString(teams.get(0).getPointScore()) + " " + Constants.namePoints);
                labelLeader2.setText(teams.get(1).getTeamName() + " with " + Integer.toString(teams.get(1).getPointScore()) + " " + Constants.namePoints);
            }
        }
        if (teams.size() >= 3) {
            if (teams.get(0).getPointScore() == teams.get(1).getPointScore() && teams.get(0).getPointScore() == teams.get(2).getPointScore()) {
                labelPlaceName1.setText(Constants.nameGold + ": ");
                labelPlaceName1.setStyle("-fx-background-color: gold; -fx-padding: 10px 30px 10px 30px; -fx-text-fill: #FFFFFF;");
                labelPlaceName2.setText(Constants.nameGold + ": ");
                labelPlaceName2.setStyle("-fx-background-color: gold; -fx-padding: 10px 30px 10px 30px; -fx-text-fill: #FFFFFF;");
                labelPlaceName3.setText(Constants.nameGold + ": ");
                labelPlaceName3.setStyle("-fx-background-color: gold; -fx-padding: 10px 30px 10px 30px; -fx-text-fill: #FFFFFF;");
                labelLeader1.setText(teams.get(0).getTeamName() + " with " + Integer.toString(teams.get(0).getPointScore()) + " " + Constants.namePoints);
                labelLeader2.setText(teams.get(1).getTeamName() + " with " + Integer.toString(teams.get(1).getPointScore()) + " " + Constants.namePoints);
                labelLeader3.setText(teams.get(2).getTeamName() + " with " + Integer.toString(teams.get(2).getPointScore()) + " " + Constants.namePoints);
            } else if (teams.get(0).getPointScore() == teams.get(1).getPointScore()) {
                labelPlaceName1.setText(Constants.nameGold + ": ");
                labelPlaceName1.setStyle("-fx-background-color: gold; -fx-padding: 10px 30px 10px 30px; -fx-text-fill: #FFFFFF;");
                labelPlaceName2.setText(Constants.nameGold + ": ");
                labelPlaceName2.setStyle("-fx-background-color: gold; -fx-padding: 10px 30px 10px 30px; -fx-text-fill: #FFFFFF;");
                labelPlaceName3.setText(Constants.nameSilver + ": ");
                labelPlaceName3.setStyle("-fx-background-color: darkgray; -fx-padding: 10px 30px 10px 30px; -fx-text-fill: #FFFFFF;");
                labelLeader1.setText(teams.get(0).getTeamName() + " with " + Integer.toString(teams.get(0).getPointScore()) + " " + Constants.namePoints);
                labelLeader2.setText(teams.get(1).getTeamName() + " with " + Integer.toString(teams.get(1).getPointScore()) + " " + Constants.namePoints);
                labelLeader3.setText(teams.get(2).getTeamName() + " with " + Integer.toString(teams.get(2).getPointScore()) + " " + Constants.namePoints);
            } else if (teams.get(1).getPointScore() == teams.get(2).getPointScore()) {
                labelPlaceName1.setText(Constants.nameGold + ": ");
                labelPlaceName1.setStyle("-fx-background-color: gold; -fx-padding: 10px 30px 10px 30px; -fx-text-fill: #FFFFFF;");
                labelPlaceName2.setText(Constants.nameSilver + ": ");
                labelPlaceName2.setStyle("-fx-background-color: darkgray; -fx-padding: 10px 30px 10px 30px; -fx-text-fill: #FFFFFF;");
                labelPlaceName3.setText(Constants.nameSilver + ": ");
                labelPlaceName3.setStyle("-fx-background-color: darkgray; -fx-padding: 10px 30px 10px 30px; -fx-text-fill: #FFFFFF;");
                labelLeader1.setText(teams.get(0).getTeamName() + " with " + Integer.toString(teams.get(0).getPointScore()) + " " + Constants.namePoints);
                labelLeader2.setText(teams.get(1).getTeamName() + " with " + Integer.toString(teams.get(1).getPointScore()) + " " + Constants.namePoints);
                labelLeader3.setText(teams.get(2).getTeamName() + " with " + Integer.toString(teams.get(2).getPointScore()) + " " + Constants.namePoints);
            } else {
                labelPlaceName1.setText(Constants.nameGold + ": ");
                labelPlaceName1.setStyle("-fx-background-color: gold; -fx-padding: 10px 30px 10px 30px; -fx-text-fill: #FFFFFF;");
                labelPlaceName2.setText(Constants.nameSilver + ": ");
                labelPlaceName2.setStyle("-fx-background-color: darkgray; -fx-padding: 10px 30px 10px 30px; -fx-text-fill: #FFFFFF;");
                labelPlaceName3.setText(Constants.nameBronze + ": ");
                labelPlaceName3.setStyle("-fx-background-color: sandybrown; -fx-padding: 10px 30px 10px 30px; -fx-text-fill: #FFFFFF;");
                labelLeader1.setText(teams.get(0).getTeamName() + " with " + Integer.toString(teams.get(0).getPointScore()) + " " + Constants.namePoints);
                labelLeader2.setText(teams.get(1).getTeamName() + " with " + Integer.toString(teams.get(1).getPointScore()) + " " + Constants.namePoints);
                labelLeader3.setText(teams.get(2).getTeamName() + " with " + Integer.toString(teams.get(2).getPointScore()) + " " + Constants.namePoints);
            }
        }

        try {
            user.writeOnHistoryFile( Constants.nameTeam + " " + teams.get(0).getTeamName() + " " + Constants.textTeamXWonAndGot +
                    " " + Integer.toString(teams.get(0).getPointScore()) + "  " +  Constants.textXAnswerRightOutOfX + "  " + numOfQuestions + " " + Constants.textQuestions + ".");
        } catch (IOException e) {
            e.printStackTrace();
        }


        buttonFinishGame.setOnAction(f -> {

            if(!user.isLoggedIn) {
                user.setUserName("guest user");
            }
            try {
                user.writeOnHistoryFile("Team: " + teams.get(0).getTeamName() + " won the game and got " + Integer.toString(teams.get(0).getPointScore()) + " right answers out of " + numOfQuestions + " questions.");
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
        layoutFinalScoreboard.getChildren().addAll(labelScreenTitle, labelPlaceName1, labelLeader1, labelPlaceName2, labelLeader2, labelPlaceName3, labelLeader3, buttonFinishGame);
        finalScoreBoardPageScene = new Scene(layoutFinalScoreboard, Constants.screenWidth, Constants.screenHeight);
        finalScoreBoardPageScene.getStylesheets().add(Constants.StyleSheetPath);

        window.setScene(finalScoreBoardPageScene);
    }
}

