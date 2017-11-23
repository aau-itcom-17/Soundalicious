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


public class Main extends Application {


    public static Stage window;
    public static Scene frontPageScene, frontPageSceneLoggedIn, quickPlayScene, customGameScene, logInPageScene, signUpScene, playGameScene;
    public static String enteredUsername, enteredPass;
    public static boolean loggedIn = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
      window = primaryStage;
      new FrontPageScene();
      window.setTitle("Soundalicous");
      window.show();


  }

  //THIS MAKES THE WINDOW OPEN:


  }
