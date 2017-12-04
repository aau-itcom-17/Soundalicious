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
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.io.IOException;

public class CustomGameScene extends FrontPageScene {

    Label labelCustom, labelChooseQuestions;
    CheckBox box1, box2, box3;
    Button buttonCustomGameNext, frontPageButton2;
    VBox customGameLayout;

    public CustomGameScene(){

      //ALL THIS IS CUSTOM GAME PAGE:

        //Label Custom page
        labelCustom = new Label(Constants.gameName);
        labelCustom.getStyleClass().add("label-headline");

        //Label choose cards
        labelChooseQuestions = new Label(Constants.chooseTopicTitle);

        //Checkboxes for choosing cards
        box1 = new CheckBox(Constants.topicText1);
        box2 = new CheckBox(Constants.topicText2);
        box3 = new CheckBox(Constants.topicText3);

        //Custom game next button
        buttonCustomGameNext = new Button(Constants.goToNextText);
        buttonCustomGameNext.setOnAction(e -> new QuickPlayScene());

        //Button back to front on custom game page
        frontPageButton2 = new Button(Constants.goToMainText);
        frontPageButton2.setOnAction(e -> window.setScene(frontPageScene));

        //Layout custom game
        customGameLayout = new VBox(20);
        customGameLayout.setAlignment(Pos.CENTER);
        customGameLayout.getChildren().addAll(labelCustom, labelChooseQuestions, box1, box2, box3, buttonCustomGameNext, frontPageButton2);
        customGameScene = new Scene(customGameLayout, 400, 600);

        customGameScene.getStylesheets().add("Theme.css");
        window.setScene(customGameScene);
    }
}
