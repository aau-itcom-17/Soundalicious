import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Scene can be reached from the main menu, displays history of user
 * When admin is logged can seen the whole history
 */
public class HistoryScene extends FrontPageScene {

    Label historyLabel;
    VBox layoutHistory;
    Button buttonBackToMain;
    TextArea text;
    String allLines;

    HistoryScene() {
        historyLabel = new Label(Constants.nameHistory);
        text = new TextArea();
        allLines = "";
        buttonBackToMain = new Button(Constants.textBackToMain);
        layoutHistory = new VBox(Constants.vBoxSpacing);
        historyScene = new Scene(layoutHistory, Constants.screenWidth, Constants.screenHeight);

        text.setPrefRowCount(25);
        historyLabel.getStyleClass().add("label-headline");
        buttonBackToMain.getStyleClass().add("button-menu");
        historyScene.getStylesheets().add(Constants.StyleSheetPath);
        layoutHistory.setAlignment(Pos.CENTER);

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(Constants.userHistoryPath + "/ " + Constants.textFileCreated  + " " + user.getUserName() + ".txt")))) {
            String line;
            while ((line = reader.readLine()) != null)
                allLines = allLines + line + "\n";
        } catch (IOException e) {
            e.printStackTrace();
        }

        text.setWrapText(true);
        text.setEditable(false);
        text.appendText(allLines);
        layoutHistory.getChildren().addAll(historyLabel, text, buttonBackToMain);

        buttonBackToMain.setOnAction(e -> new FrontPageScene());

        window.setScene(historyScene);
    }
}
