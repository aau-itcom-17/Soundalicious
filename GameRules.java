import javafx.application.*;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.IOException;

public class GameRules extends Application {

    Stage window;
    Scene gameRules;

    public static void main (String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;

        Label labelHeadline = new Label("Soundalicious");
        // Gets the css class "label-headline" instead of the normal layout
        labelHeadline.getStyleClass().add("label-headline");

        Button buttonDrinking = new Button ("Drinking Rules");
        
        // Gets the normal layout from the css stylesheet
        buttonDrinking.getStylesheets().add("Theme.css");

        Label labelHowToPlay = new Label("Fuck, people can see me.");

        labelHowToPlay.setVisible(false);

        buttonDrinking.setOnAction(e -> {
            if(labelHowToPlay.isVisible()) {
                labelHowToPlay.setVisible(false);
            } else {
                labelHowToPlay.setVisible(true);
            }

        });

        Button frontpageButton4 = new Button("Go back to front page");

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(labelHeadline, buttonDrinking, labelHowToPlay, frontpageButton4);
        Scene gameRules = new Scene(layout, 500, 500);
        gameRules.getStylesheets().add("Theme.css");

        window.setScene(gameRules);
        window.setTitle("wupwup");
        window.show();

    }

}
