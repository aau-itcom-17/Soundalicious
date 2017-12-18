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

/**
 * Scene for choosing custom game.
 * It includes radio buttons with different theme choices.
 * Goes to the PlayGameScene.class next
 */
public class CustomGameScene extends FrontPageScene {

    private String selection;
    private Label labelChooseQuestions, labelScreenTitle;
    private RadioButton radioButTheme1, radioButTheme2;
    private ToggleGroup groupContainerTheme;
    private Button buttonNextQuestionOrTeam, buttonBackToMain;
    private VBox customGameLayout;

    public CustomGameScene() {

        labelScreenTitle = new Label(Constants.nameCustomGame);
        labelChooseQuestions = new Label(Constants.chooseTopicTitle);
        buttonNextQuestionOrTeam = new Button(Constants.goToNextText);
        buttonBackToMain = new Button(Constants.textBackToMain);
        radioButTheme1 = new RadioButton(Constants.topicText1);
        radioButTheme2 = new RadioButton(Constants.topicText2);
        groupContainerTheme = new ToggleGroup();
        customGameLayout = new VBox(Constants.vBoxSpacing);
        customGameScene = new Scene(customGameLayout, Constants.screenWidth, Constants.screenHeight);

        radioButTheme1.setToggleGroup(groupContainerTheme);
        radioButTheme2.setToggleGroup(groupContainerTheme);

        customGameLayout.getChildren().addAll(labelScreenTitle, labelChooseQuestions, radioButTheme1, radioButTheme2, buttonNextQuestionOrTeam, buttonBackToMain);

        labelScreenTitle.getStyleClass().add("label-headline");
        buttonNextQuestionOrTeam.getStyleClass().add("button-menu");
        buttonBackToMain.getStyleClass().add("button-menu");
        radioButTheme1.getStyleClass().add("button-menuSelected");
        radioButTheme2.getStyleClass().add("button-menuSelected");
        radioButTheme1.setStyle("-fx-min-width: 250px; -fx-max-width: 250px;");
        radioButTheme2.setStyle("-fx-min-width: 250px; -fx-max-width: 250px;");
        customGameLayout.setAlignment(Pos.CENTER);
        customGameScene.getStylesheets().add(Constants.StyleSheetPath);

        buttonNextQuestionOrTeam.setOnAction(e -> {
            if (radioButTheme1.isSelected()) {
                selection = Constants.topicText1;
            } else if (radioButTheme2.isSelected()) {
                selection = Constants.topicText2;
            }
            try {
                Questions.getThemeQuestions(questions, rQuestions, themeQuestions, selection);
            } catch (ParserConfigurationException e1) {
                e1.printStackTrace();
            } catch (SAXException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            new QuickPlayScene();

        });

        groupContainerTheme.selectedToggleProperty().addListener((observable, oldVal, newVal) ->
        {
            radioButTheme1.setStyle("-fx-background-color: #AAD7FF;  -fx-text-fill: #004A8C; -fx-min-width: 250px; -fx-max-width: 250px;");
            radioButTheme2.setStyle("-fx-background-color: #AAD7FF;  -fx-text-fill: #004A8C; -fx-min-width: 250px; -fx-max-width: 250px;");
            buttonNextQuestionOrTeam.getStyleClass().add("button-menuSelected");
        });

        buttonBackToMain.setOnAction(e -> new FrontPageScene());

        window.setScene(customGameScene);
    }
}
