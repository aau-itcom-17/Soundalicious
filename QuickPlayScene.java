import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Quick play scene appears right after user press the start game button in FrontPageScene.java
 * It includes two boxes for choosing number of teams and questions (with incrementals by the sides.
 * Also, there are 3 buttons: start the game, explain the rules or go back to main.
 */

public class QuickPlayScene extends FrontPageScene {

    Button buttonContainerTeams, buttonContainerQuestions;
    private Label labelScreenTitle;
    private Button buttonStartGame, buttonHowToPlay,
            buttonBackToMain, buttonDecTeams, buttonIncTeams, buttonDecQuestions, buttonIncQuestions;
    private VBox layoutQuickPlay;
    private HBox layoutIncrementTeams, layoutIncrementQuestions;

    public QuickPlayScene() {

        labelScreenTitle = new Label(Constants.nameQuickPlay);
        buttonDecTeams = new Button(Constants.textDecrease);
        buttonIncTeams = new Button(Constants.textIncrease);
        buttonDecQuestions = new Button(Constants.textDecrease);
        buttonIncQuestions = new Button(Constants.textIncrease);
        buttonStartGame = new Button(Constants.startGameText);
        buttonBackToMain = new Button(Constants.textBackToMain);
        buttonHowToPlay = new Button(Constants.howToPlayText);
        buttonContainerQuestions = new Button(Constants.questionNumNames[qCount]); //this button is not clickable, but was chosen as a button for better alignment with increments
        buttonContainerTeams = new Button(Constants.teamNumNames[tCount]); //this button is not clickable, but was chosen as a button for better alignment with increments
        layoutIncrementTeams = new HBox();
        layoutIncrementQuestions = new HBox();
        layoutQuickPlay = new VBox(Constants.vBoxSpacing);
        quickPlayScene = new Scene(layoutQuickPlay, Constants.screenWidth, Constants.screenHeight);

        layoutIncrementTeams.getChildren().addAll(buttonDecTeams, buttonContainerTeams, buttonIncTeams);
        layoutIncrementQuestions.getChildren().addAll(buttonDecQuestions, buttonContainerQuestions, buttonIncQuestions);

        //there are not enough questions in xml file for every theme, so now starting point for every custom game is limited questions
        if(selectedCustomGame){
            numOfQuestions = Constants.numberQuestionCustomGame;
            selectedCustomGame = false;
            layoutQuickPlay.getChildren().addAll(labelScreenTitle, layoutIncrementTeams, buttonStartGame, buttonHowToPlay, buttonBackToMain);
        } else {
            layoutQuickPlay.getChildren().addAll(labelScreenTitle, layoutIncrementTeams, layoutIncrementQuestions, buttonStartGame, buttonHowToPlay, buttonBackToMain);
        }

        labelScreenTitle.getStyleClass().add("label-headline");
        buttonDecTeams.getStyleClass().add("controlButtonMinus");
        buttonContainerTeams.getStyleClass().add("controlText");
        buttonIncTeams.getStyleClass().add("controlButtonPlus");
        buttonStartGame.getStyleClass().add("button-continue");
        buttonBackToMain.getStyleClass().add("button-menu");
        buttonDecQuestions.getStyleClass().add("controlButtonMinus");
        buttonContainerQuestions.getStyleClass().add("controlText");
        buttonHowToPlay.getStyleClass().add("button-menu");
        buttonIncQuestions.getStyleClass().add("controlButtonPlus");
        layoutIncrementTeams.setAlignment(Pos.CENTER);
        layoutIncrementQuestions.setAlignment(Pos.CENTER);
        layoutQuickPlay.setAlignment(Pos.CENTER);
        quickPlayScene.getStylesheets().add(Constants.StyleSheetPath);

        /*
         * Buttons for decreasing/increasing numbers of teams and questions work the same
         * If number reaches corner values, buttons changes color and value can not be changed with it
         * If corner value is not reached it changes the value itself and the text inside the middle textbox-button (buttonContainerTeams)
         */
        buttonDecTeams.setOnAction(e -> {
            if (tCount == 1) {
                tCount--;
                buttonContainerTeams.setText(Constants.teamNumNames[tCount]);
                numOfTeams = Constants.teamNums[tCount];
                buttonIncTeams.setStyle(null);
                buttonDecTeams.setStyle("-fx-background-color: #eaf2ff");
            } else if (tCount > 0) {
                tCount--;
                buttonContainerTeams.setText(Constants.teamNumNames[tCount]);
                numOfTeams = Constants.teamNums[tCount];
                buttonIncTeams.setStyle(null);
            }
        });
        buttonIncTeams.setOnAction(e -> {
                    if (tCount == 3) {
                        tCount++;
                        buttonContainerTeams.setText(Constants.teamNumNames[tCount]);
                        numOfTeams = Constants.teamNums[tCount];
                        buttonDecTeams.setStyle(null);
                        buttonIncTeams.setStyle("-fx-background-color: #eaf2ff");
                    } else if (tCount < 4) {
                        tCount++;
                        buttonContainerTeams.setText(Constants.teamNumNames[tCount]);
                        numOfTeams = Constants.teamNums[tCount];
                        buttonDecTeams.setStyle(null);
                    }
                }
        );
        buttonDecQuestions.setOnAction(e -> {
            if (qCount == 1) {
                qCount--;
                buttonContainerQuestions.setText(Constants.questionNumNames[qCount]);
                numOfQuestions = Constants.questionNums[qCount];
                buttonIncQuestions.setStyle(null);
                buttonDecQuestions.setStyle("-fx-background-color: #eaf2ff");
            } else if (qCount > 0) {
                qCount--;
                buttonContainerQuestions.setText(Constants.questionNumNames[qCount]);
                numOfQuestions = Constants.teamNums[qCount];
                buttonIncQuestions.setStyle(null);
            }
        });
        buttonIncQuestions.setOnAction(e -> {
                    if (qCount == 4) {
                        qCount++;
                        buttonContainerQuestions.setText(Constants.questionNumNames[qCount]);
                        numOfQuestions = Constants.questionNums[qCount];
                        buttonDecQuestions.setStyle(null);
                        buttonIncQuestions.setStyle("-fx-background-color: #eaf2ff");
                    } else if (qCount < 5) {
                        qCount++;
                        buttonContainerQuestions.setText(Constants.questionNumNames[qCount]);
                        numOfQuestions = Constants.questionNums[qCount];
                        buttonDecQuestions.setStyle(null);
                    }
                }
        );

        buttonStartGame.setOnAction(e -> new TeamNameScene());

        buttonHowToPlay.setOnAction(e -> new GameRulesScene());

        //before moving to front page, it clears values that have been changed by user
        buttonBackToMain.setOnAction(e -> {
            teams.clear();
            counterOfQuestions = 0;
            answers.clear();
            numOfTeams = 1;
            window.setScene(frontPageScene);
        });

        window.setScene(quickPlayScene);
    }
}
