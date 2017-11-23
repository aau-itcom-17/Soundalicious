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

public class FrontPageScene extends Main {

  Label labelFront;
  Button quickPlayButton, customGameButton, logInPageButton, signUpButton;
  Button quickPlayButton1, customGameButton1, logOutButton;
  VBox layoutFrontpage, layoutFrontpage1;


  public FrontPageScene(){

    //Label front page
    labelFront = new Label(Constants.gameName);

    //Quick play button -> Goes to Quickplay page
    quickPlayButton = new Button(Constants.quickPlayName);
    quickPlayButton.setOnAction(e -> new QuickPlayScene());

    //Quick play button when logged in -> Goes to Quickplay page
    quickPlayButton1 = new Button(Constants.quickPlayName);
    quickPlayButton1.setOnAction(e -> new QuickPlayScene());

    //Custom game button -> Goes to login page  page
    customGameButton = new Button(Constants.customGameText);
    customGameButton.setOnAction(e -> new LogInScene());

    //Custom game button1 goes to custom game
    customGameButton1 = new Button(Constants.customGameText);
    customGameButton1.setOnAction(e -> new CustomGameScene());

    //Logout button
    logOutButton = new Button(Constants.logOutText);
    logOutButton.setOnAction(e -> window.setScene(frontPageScene));

    //Layout Front Page when logged in
    layoutFrontpage1 = new VBox(20);
    layoutFrontpage1.setAlignment(Pos.CENTER);
    layoutFrontpage1.getChildren().addAll(labelFront, quickPlayButton1, customGameButton1, logOutButton);
    frontPageSceneLoggedIn = new Scene(layoutFrontpage1, 400, 600);

    //Login page button -> Goes to login page
    logInPageButton = new Button(Constants.logInText);
    logInPageButton.setOnAction(e -> new LogInScene());

    //sign up button -> Goes to sign up page
    signUpButton = new Button(Constants.signUpText);
    signUpButton.setOnAction(e -> new SignUpScene());

    //Layout Front Page
    layoutFrontpage = new VBox(20);
    layoutFrontpage.setAlignment(Pos.CENTER);
    layoutFrontpage.getChildren().addAll(labelFront, quickPlayButton, customGameButton, logInPageButton, signUpButton);
    frontPageScene = new Scene(layoutFrontpage, 400, 600);

    window.setScene(frontPageScene);
  }
}
