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
    RadioButton radioBut1, radioBut2, radioBut3;
    ToggleGroup theme;
    Button buttonCustomGameNext, frontPageButton2;
    VBox customGameLayout;

    public CustomGameScene(){

      //ALL THIS IS CUSTOM GAME PAGE:

        //Label Custom page
        labelCustom = new Label(Constants.gameName);
        labelCustom.getStyleClass().add("label-headline");

        //Label choose cards
        labelChooseQuestions = new Label(Constants.chooseTopicTitle);

        //Radiobuttons for choosing cards
        radioBut1 = new RadioButton(Constants.topicText1);
        radioBut2 = new RadioButton(Constants.topicText2);
        radioBut3 = new RadioButton(Constants.topicText3);
        theme = new ToggleGroup();

        radioBut1.setToggleGroup(theme);
        radioBut2.setToggleGroup(theme);
        radioBut3.setToggleGroup(theme);

        radioBut1.getStyleClass().add("button-menuSelected");
        radioBut2.getStyleClass().add("button-menuSelected");
        radioBut3.getStyleClass().add("button-menuSelected");

        //Custom game next button
        buttonCustomGameNext = new Button(Constants.goToNextText);
        buttonCustomGameNext.getStyleClass().add("button-menu");
        buttonCustomGameNext.setOnAction(e -> new QuickPlayScene());

        theme.selectedToggleProperty().addListener((observable, oldVal, newVal) ->
        {
            radioBut1.setStyle("-fx-background-color: #AAD7FF;  -fx-text-fill: #004A8C;");
            radioBut2.setStyle("-fx-background-color: #AAD7FF;  -fx-text-fill: #004A8C;");
            radioBut3.setStyle("-fx-background-color: #AAD7FF;  -fx-text-fill: #004A8C;");
            buttonCustomGameNext.getStyleClass().add("button-menuSelected");
        });


        //Button back to front on custom game page
        frontPageButton2 = new Button(Constants.goToMainText);
        frontPageButton2.getStyleClass().add("button-menu");
        frontPageButton2.setOnAction(e -> new FrontPageScene());

        //Layout custom game
        customGameLayout = new VBox(20);
        customGameLayout.setAlignment(Pos.CENTER);
        customGameLayout.getChildren().addAll(labelCustom, labelChooseQuestions, radioBut1, radioBut2, radioBut3, buttonCustomGameNext, frontPageButton2);
        customGameScene = new Scene(customGameLayout, 400, 600);

        customGameScene.getStylesheets().add("Theme.css");
        window.setScene(customGameScene);
    }
}
