import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Scoreboard is displayed after all teams have answered the same question.
 * At the top stage shows the correct answer.
 * Below it, there are text label for every team's name and overall score as well as line with chosen answer.
 * The line after every team with chosen answer is either green or red accordingly if it was right or wrong answer.
 * At the bottom - button to continue the game to the next question.
 */

public class ScoreboardPageScene extends PlayGameScene {

    private VBox layoutScoreboard;
    private Label labelScreenTitle, labelTeamNameAndScore1, labelTeamNameAndScore2, labelTeamNameAndScore3, labelTeamNameAndScore4, labelTeamNameAndScore5;
    private Label labelTeamAnswerText1, labelTeamAnswerText2, labelTeamAnswerText3, labelTeamAnswerText4, labelTeamAnswerText5, labelCorrectAnswerText;
    private Button buttonNextQuestion;

    public ScoreboardPageScene() throws IOException, SAXException, ParserConfigurationException {

        labelScreenTitle = new Label(Constants.nameScoreboard);
        labelTeamNameAndScore1 = new Label();
        labelTeamNameAndScore2 = new Label();
        labelTeamNameAndScore3 = new Label();
        labelTeamNameAndScore4 = new Label();
        labelTeamNameAndScore5 = new Label();
        labelTeamAnswerText1 = new Label();
        labelTeamAnswerText2 = new Label();
        labelTeamAnswerText3 = new Label();
        labelTeamAnswerText4 = new Label();
        labelTeamAnswerText5 = new Label();
        labelCorrectAnswerText = new Label(Constants.textCorrectAnswer + ": " + PlayGameScene.correctAnswerText);
        buttonNextQuestion = new Button((counterOfQuestions == QuickPlayScene.numOfQuestions) ? Constants.nameLeaderboard : (Constants.textNext + " " + Constants.nameQuestion));
        layoutScoreboard = new VBox(Constants.vBoxSpacing);

        labelScreenTitle.getStyleClass().add("label-board");
        labelTeamNameAndScore1.getStyleClass().add("label-scores");
        labelTeamNameAndScore2.getStyleClass().add("label-scores");
        labelTeamNameAndScore3.getStyleClass().add("label-scores");
        labelTeamNameAndScore4.getStyleClass().add("label-scores");
        labelTeamNameAndScore5.getStyleClass().add("label-scores");
        buttonNextQuestion.getStyleClass().add("button-continue");
        layoutScoreboard.setAlignment(Pos.CENTER);

        if (teams.size() >= 1) {
            labelTeamNameAndScore1.setText(teams.get(0).getTeamName() + ": " + Integer.toString(teams.get(0).getPointScore()) + " points");
            if (PlayGameScene.correctAnswerText.equals(PlayGameScene.answersChosenByPlayersTexts[0])) {
                labelTeamAnswerText1.setStyle("-fx-text-fill: green");
            } else {
                labelTeamAnswerText1.setStyle("-fx-text-fill: red");
            }
            labelTeamAnswerText1.setText(Constants.textSelectedAnswer + ": " + PlayGameScene.answersChosenByPlayersTexts[0] + "\n ");
        }

        if (teams.size() >= 2) {
            labelTeamNameAndScore2.setText(teams.get(1).getTeamName() + ": " + Integer.toString(teams.get(1).getPointScore()) + " points");
            if (PlayGameScene.correctAnswerText.equals(PlayGameScene.answersChosenByPlayersTexts[1])) {
                labelTeamAnswerText2.setStyle("-fx-text-fill: green");
            } else {
                labelTeamAnswerText2.setStyle("-fx-text-fill: red");
            }
            labelTeamAnswerText2.setText(Constants.textSelectedAnswer + ": " + PlayGameScene.answersChosenByPlayersTexts[1] + "\n ");
        }

        if (teams.size() >= 3) {
            labelTeamNameAndScore3.setText(teams.get(2).getTeamName() + ": " + Integer.toString(teams.get(2).getPointScore()) + " points");
            if (PlayGameScene.correctAnswerText.equals(PlayGameScene.answersChosenByPlayersTexts[2])) {
                labelTeamAnswerText3.setStyle("-fx-text-fill: green");
            } else {
                labelTeamAnswerText3.setStyle("-fx-text-fill: red");
            }
            labelTeamAnswerText3.setText(Constants.textSelectedAnswer + ": " + PlayGameScene.answersChosenByPlayersTexts[2] + "\n ");
        }

        if (teams.size() >= 4) {
            labelTeamNameAndScore4.setText(teams.get(3).getTeamName() + ": " + Integer.toString(teams.get(3).getPointScore()) + " points");
            if (PlayGameScene.correctAnswerText.equals(PlayGameScene.answersChosenByPlayersTexts[3])) {
                labelTeamAnswerText4.setStyle("-fx-text-fill: green");
            } else {
                labelTeamAnswerText4.setStyle("-fx-text-fill: red");
            }
            labelTeamAnswerText4.setText(Constants.textSelectedAnswer + ": " + PlayGameScene.answersChosenByPlayersTexts[3] + "\n ");

        }

        if (teams.size() == 5) {
            labelTeamNameAndScore5.setText(teams.get(4).getTeamName() + ": " + Integer.toString(teams.get(4).getPointScore()) + " points");
            if (PlayGameScene.correctAnswerText.equals(PlayGameScene.answersChosenByPlayersTexts[4])) {
                labelTeamAnswerText5.setStyle("-fx-text-fill: green");
            } else {
                labelTeamAnswerText5.setStyle("-fx-text-fill: red");
            }
            labelTeamAnswerText5.setText(Constants.textSelectedAnswer + " " + PlayGameScene.answersChosenByPlayersTexts[4] + "\n ");
        }

        buttonNextQuestion.setOnAction(e -> {
            if (counterOfQuestions == QuickPlayScene.numOfQuestions) {
                try {
                    new FinalScoreboardPageScene();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (SAXException e1) {
                    e1.printStackTrace();
                } catch (ParserConfigurationException e1) {
                    e1.printStackTrace();
                }
            } else {
                counterOfQuestions++;
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

        if (teams.size() == 1) {
            layoutScoreboard.getChildren().addAll(labelScreenTitle, labelCorrectAnswerText, labelTeamNameAndScore1, labelTeamAnswerText1, buttonNextQuestion);
        } else if (teams.size() == 2) {
            layoutScoreboard.getChildren().addAll(labelScreenTitle, labelCorrectAnswerText, labelTeamNameAndScore1, labelTeamAnswerText1, labelTeamNameAndScore2, labelTeamAnswerText2, buttonNextQuestion);
        } else if (teams.size() == 3) {
            layoutScoreboard.getChildren().addAll(labelScreenTitle, labelCorrectAnswerText, labelTeamNameAndScore1, labelTeamAnswerText1, labelTeamNameAndScore2, labelTeamAnswerText2, labelTeamNameAndScore3, labelTeamAnswerText3, buttonNextQuestion);
        } else if (teams.size() == 4) {
            layoutScoreboard.getChildren().addAll(labelScreenTitle, labelCorrectAnswerText, labelTeamNameAndScore1, labelTeamAnswerText1, labelTeamNameAndScore2, labelTeamAnswerText2, labelTeamNameAndScore3, labelTeamAnswerText3, labelTeamNameAndScore4, labelTeamAnswerText4, buttonNextQuestion);
        } else if (teams.size() == 5) {
            layoutScoreboard.getChildren().addAll(labelScreenTitle, labelCorrectAnswerText, labelTeamNameAndScore1, labelTeamAnswerText1, labelTeamNameAndScore2, labelTeamAnswerText2, labelTeamNameAndScore3, labelTeamAnswerText3, labelTeamNameAndScore4, labelTeamAnswerText4, labelTeamNameAndScore5, labelTeamAnswerText5, buttonNextQuestion);
        }

        scoreBoardPageScene = new Scene(layoutScoreboard, Constants.screenWidth, Constants.screenHeight);
        scoreBoardPageScene.getStylesheets().add(Constants.StyleSheetPath);
        window.setScene(scoreBoardPageScene);
    }
}
