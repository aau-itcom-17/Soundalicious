import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * It is the first screen that user sees, it is called from the Main.java.
 * It has different menu options when nobody is logged in or admin/user is logged in.
 * When nobody is logged in it shows Quick play, Custom games (when pressed just goes to login), Log in and Sign up options as buttons.
 * Admin sees Quick play, Custom game, Upload sound, Delete users and Log out options as buttons.
 * Regular user sees Quick play, Custom game, Upload sound, Delete your user and Log out option as buttons.
 * The program goes next to the item that is chosen in menu, if somebody is logged in title displays the name of the user.
 */

public class GameRulesScene extends QuickPlayScene {

    private Label labelScreenTitle, labelRulesText;
    private Button buttonShowRules, buttonBackToMain;
    VBox layout;

    public GameRulesScene() {
        labelScreenTitle = new Label(Constants.nameGame);
        buttonShowRules = new Button(Constants.textHowToPlay);
        labelRulesText = new Label(Constants.textRules);
        buttonBackToMain = new Button(Constants.goToQuickPlayText);
        layout = new VBox(Constants.vBoxSpacing);
        gameRulesScene = new Scene(layout, Constants.screenWidth, Constants.screenHeight);

        buttonShowRules.getStyleClass().add("button-menu");
        buttonBackToMain.getStyleClass().add("button-continue");
        layout.setAlignment(Pos.CENTER);
        labelRulesText.setStyle("-fx-text-alignment: center");
        gameRulesScene.getStylesheets().add(Constants.StyleSheetPath);

        labelRulesText.setVisible(false);
        layout.getChildren().addAll(labelScreenTitle, buttonShowRules, labelRulesText, buttonBackToMain);

        buttonShowRules.setOnAction(e -> {
            if (labelRulesText.isVisible()) {
                labelRulesText.setVisible(false);
            } else {
                labelRulesText.setVisible(true);
            }
        });

        /*
         * Because this game rules screen was reached from quick play screen
         * Before going back to the quick play scene values next to increments set to ones that was chosen
         * Otherwise, user would have to change values again
         */
        buttonBackToMain.setOnAction(e -> {
            numOfTeams = Constants.teamNums[tCount];
            buttonContainerTeams.setText(Constants.teamNumNames[tCount]);

            numOfQuestions = Constants.questionNums[qCount];
            buttonContainerQuestions.setText(Constants.questionNumNames[qCount]);

            window.setScene(quickPlayScene);
        });

        window.setScene(gameRulesScene);
    }
}
