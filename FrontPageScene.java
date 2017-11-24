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
import java.io.*;

public class FrontPageScene extends Main {

  Label labelFront;
  Button quickPlayButton, customGameButton, logInPageButton, signUpButton;
  Button quickPlayButton1, customGameButton1, logOutButton, deleteButton;
  VBox layoutFrontpage, layoutFrontpage1;


  public FrontPageScene() {

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

    //delete your user button
    deleteButton = new Button(Constants.deleteUserText);
    deleteButton.setOnAction(e -> {
      removeLineFromFile("text.txt", loggedUser + " " + loggedUsersPass);
      window.setScene(frontPageScene);
    });

    //Layout Front Page when logged in
    layoutFrontpage1 = new VBox(20);
    layoutFrontpage1.setAlignment(Pos.CENTER);
    layoutFrontpage1.getChildren().addAll(labelFront, quickPlayButton1, customGameButton1, logOutButton, deleteButton);
    frontPageSceneLoggedIn = new Scene(layoutFrontpage1, 400, 600);
    frontPageSceneLoggedIn.getStylesheets().add("Theme.css");

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

    frontPageScene.getStylesheets().add("Theme.css");
    window.setScene(frontPageScene);
  }

  private void removeLineFromFile(String file, String lineToRemove) {
    System.out.println(lineToRemove);
    try {
      File inFile = new File(file);
      //Construct the new file that will later be renamed to the original filename.
      // ".tmp makes sure we don't delete the entire .txt file"
      File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
      BufferedReader br = new BufferedReader(new FileReader(file));
      PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
      String line;
      //Read from the original file and write to the new
      //unless content matches data to be removed.
      while ((line = br.readLine()) != null) {
        if (!line.trim().equals(lineToRemove)) {
          pw.println(line);
          pw.flush();
        }
      }

      pw.close();
      br.close();

      if (!inFile.delete()) {
        System.out.println("Could not delete file");
        return;
      }
      //Rename the new file to the filename the original file had.
      if (!tempFile.renameTo(inFile))
        System.out.println("Could not rename file");

    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}