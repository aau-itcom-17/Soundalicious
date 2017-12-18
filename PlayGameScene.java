import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Collections;

/**
 * Play game scene appears on the screen after Saving teams' name.
 * It has a label in the top with the team's name and number of the current question.
 * Below it - label with the question, button for playing sound, radio buttons for answer, button for next question/team and back to main menu button.
 * After the question has been answered, next screen is either the same question for other team or if all teams have answered - ScoreboardPageScene.java
 */
public class PlayGameScene extends QuickPlayScene {

    private int counterOfCurrentTeam = 0;
    private RadioButton radioAnswer1, radioAnswer2, radioAnswer3, radioAnswer4;
    private ToggleGroup groupAnswerOptions;
    private Label labelQuestionText, teamName;
    private Button buttonPlaySound, buttonNextTeamOrQuestion, buttonBackToMain;
    private VBox playGameLayout;
    public static String[] answersChosenByPlayersTexts = new String[5];
    public static String correctAnswerText;

    public PlayGameScene() throws IOException, SAXException, ParserConfigurationException {

        radioAnswer1 = new RadioButton(rQuestions.get(counterOfQuestions).getCorrectAnswer());
        radioAnswer2 = new RadioButton(rQuestions.get(counterOfQuestions).getwrongAnswers1());
        radioAnswer3 = new RadioButton(rQuestions.get(counterOfQuestions).getwrongAnswers2());
        radioAnswer4 = new RadioButton(rQuestions.get(counterOfQuestions).getwrongAnswers3());
        buttonBackToMain = new Button(Constants.textBackToMain);
        labelQuestionText = new Label(rQuestions.get(counterOfQuestions).getTextOfQuestion());
        buttonPlaySound = new Button(Constants.textPlaySound);
        groupAnswerOptions = new ToggleGroup();
        playGameLayout = new VBox(Constants.vBoxSpacing);
        teamName = new Label(teams.get(counterOfCurrentTeam).getTeamName() + " " + Constants.textAnsweringQuestion + " " + (counterOfQuestions + 1));
        correctAnswerText = rQuestions.get(counterOfQuestions).getCorrectAnswer();
        buttonNextTeamOrQuestion = new Button(Constants.textNext + " " + (teams.size() > 1 ? Constants.nameTeam : Constants.nameQuestion)); // if one team playing - button says next question, if more than one - next team

        radioAnswer1.setToggleGroup(groupAnswerOptions);
        radioAnswer2.setToggleGroup(groupAnswerOptions);
        radioAnswer3.setToggleGroup(groupAnswerOptions);
        radioAnswer4.setToggleGroup(groupAnswerOptions);
        buttonNextTeamOrQuestion.setVisible(false);

        answers.add(radioAnswer1);
        answers.add(radioAnswer2);
        answers.add(radioAnswer3);
        answers.add(radioAnswer4);

        //displaying radio buttons in random way, otherwise, correct is always the first one
        Collections.shuffle(answers);

        playGameLayout.getChildren().addAll(teamName, labelQuestionText, buttonPlaySound, answers.get(0), answers.get(1), answers.get(2), answers.get(3), buttonNextTeamOrQuestion, buttonBackToMain);
        playGameScene = new Scene(playGameLayout, Constants.screenWidth, Constants.screenHeight);

        labelQuestionText.getStyleClass().add("label-who");
        radioAnswer1.getStyleClass().add("radio-button-Selected");
        radioAnswer2.getStyleClass().add("radio-button-Selected");
        radioAnswer3.getStyleClass().add("radio-button-Selected");
        radioAnswer4.getStyleClass().add("radio-button-Selected");
        radioAnswer1.setStyle("-fx-background-color: #AAD7FF;  -fx-text-fill: #004A8C;");
        radioAnswer2.setStyle("-fx-background-color: #AAD7FF;  -fx-text-fill: #004A8C;");
        radioAnswer3.setStyle("-fx-background-color: #AAD7FF;  -fx-text-fill: #004A8C;");
        radioAnswer4.setStyle("-fx-background-color: #AAD7FF;  -fx-text-fill: #004A8C;");
        buttonPlaySound.getStyleClass().add("button-menuSelected");
        buttonBackToMain.getStyleClass().add("button-menu");
        buttonNextTeamOrQuestion.getStyleClass().add("button-menu");
        buttonNextTeamOrQuestion.setStyle("-fx-max-width: 250px");
        playGameLayout.setAlignment(Pos.CENTER);
        playGameScene.getStylesheets().add(Constants.StyleSheetPath);
        labelQuestionText.setWrapText(true);
        labelQuestionText.setStyle("-fx-text-alignment: center");

        //when play sound is clicked, the sound plays and buttons for answer changes style
        buttonPlaySound.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Soundfiles.readingSound();
                buttonPlaySound.setStyle("-fx-background-color: #AAD7FF;  -fx-text-fill: #004A8C;");
                radioAnswer1.setStyle(null);
                radioAnswer2.setStyle(null);
                radioAnswer3.setStyle(null);
                radioAnswer4.setStyle(null);
            }
        });

        //when any button of answers is pressed, all answer button change color back and "Next question/team" button becomes visible
        groupAnswerOptions.selectedToggleProperty().addListener((observable, oldVal, newVal) ->
        {
            buttonPlaySound.setStyle("-fx-background-color: #AAD7FF;  -fx-text-fill: #004A8C;");
            radioAnswer1.setStyle("-fx-background-color: #AAD7FF;  -fx-text-fill: #004A8C;");
            radioAnswer2.setStyle("-fx-background-color: #AAD7FF;  -fx-text-fill: #004A8C;");
            radioAnswer3.setStyle("-fx-background-color: #AAD7FF;  -fx-text-fill: #004A8C;");
            radioAnswer4.setStyle("-fx-background-color: #AAD7FF;  -fx-text-fill: #004A8C;");
            buttonNextTeamOrQuestion.getStyleClass().add("button-menuSelected");
            buttonNextTeamOrQuestion.setVisible(true);
        });

        /*
         * when next team/question button is clicked, answer is saved and the next team can answer
         * so the state of the button is refreshed and counters are incresead to get the answer from other team
         * if all teams answered, game goes to scoreboard to continue the game
         */
        buttonNextTeamOrQuestion.setOnAction(f -> {
            buttonPlaySound.getStyleClass().add("button-menuSelected");

            groupAnswerOptions.selectedToggleProperty().addListener((observable, oldVal, newVal) -> buttonNextTeamOrQuestion.setVisible(false));

            if (radioAnswer1.isSelected()) answersChosenByPlayersTexts[counterOfCurrentTeam] = radioAnswer1.getText();
            if (radioAnswer2.isSelected()) answersChosenByPlayersTexts[counterOfCurrentTeam] = radioAnswer2.getText();
            if (radioAnswer3.isSelected()) answersChosenByPlayersTexts[counterOfCurrentTeam] = radioAnswer3.getText();
            if (radioAnswer4.isSelected()) answersChosenByPlayersTexts[counterOfCurrentTeam] = radioAnswer4.getText();

            if (teams.size() > counterOfCurrentTeam + 1) {
                teamName.setText(teams.get(counterOfCurrentTeam + 1).getTeamName() + " " + Constants.textAnsweringQuestion + " " + (counterOfQuestions + 1));
            }

            if (teams.size() == counterOfCurrentTeam + 2) {
                buttonNextTeamOrQuestion.setText(Constants.textSeeAnswers);
            }

            if (radioAnswer1.isSelected()) {
                teams.get(counterOfCurrentTeam).setPointScore(teams.get(counterOfCurrentTeam).getPointScore() + 1);
            }

            if (counterOfCurrentTeam == teams.size() - 1) {
                try {
                    new ScoreboardPageScene();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }
            }

            radioAnswer1.setSelected(false);
            radioAnswer2.setSelected(false);
            radioAnswer3.setSelected(false);
            radioAnswer4.setSelected(false);
            buttonPlaySound.setStyle(null);
            radioAnswer1.setStyle("-fx-background-color: #AAD7FF;  -fx-text-fill: #004A8C;");
            radioAnswer2.setStyle("-fx-background-color: #AAD7FF;  -fx-text-fill: #004A8C;");
            radioAnswer3.setStyle("-fx-background-color: #AAD7FF;  -fx-text-fill: #004A8C;");
            radioAnswer4.setStyle("-fx-background-color: #AAD7FF;  -fx-text-fill: #004A8C;");

            counterOfCurrentTeam++;

            Soundfiles.noSound();
            answers.clear();

            groupAnswerOptions.selectedToggleProperty().addListener((observable, oldVal, newVal) -> buttonNextTeamOrQuestion.setVisible(true));
        });

        buttonBackToMain.setOnAction(e -> {
            buttonBackToMain.setText(Constants.warningConfirmActionExit);
            buttonBackToMain.setStyle("-fx-background-color: red; -fx-text-fill: #FFFFFF;");
            buttonBackToMain.setOnAction(PlayGameScene::handle);
        });

        window.setScene(playGameScene);
    }


    /**
     * Method resets the game for a new play with default values.
     */
    private static void handle(ActionEvent f) {
        teams.clear();
        counterOfQuestions = 0;
        Soundfiles.noSound();
        rQuestions.clear();
        answers.clear();
        numOfTeams = 2;
        numOfQuestions = 10;
        tCount = 1;
        qCount = 1;
        window.setScene(frontPageScene);

        Constants.click = 1;
    }
}
