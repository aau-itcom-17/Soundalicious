import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;

import java.io.*;

public class FrontPageScene extends Main {

  Label labelFront;
  Button quickPlayButton, customGameButton, loginPageButton, signUpButton;
  Button quickPlayButton1, customGameButton1, logOutButton, deleteButton, uploadButton;
  VBox layoutFrontpage, layoutFrontpage1;


  public FrontPageScene() {

    //Label front page
    labelFront = new Label(Constants.gameName);
    labelFront.getStyleClass().add("label-headline");

    //Quick play button -> Goes to Quickplay page
    quickPlayButton = new Button(Constants.quickPlayName);
    quickPlayButton.getStyleClass().add("button-quickplay");
    quickPlayButton.setOnAction(e -> new QuickPlayScene());

    //Quick play button when logged in -> Goes to Quickplay page
    quickPlayButton1 = new Button(Constants.quickPlayName);
    quickPlayButton1.getStyleClass().add("button-quickplay");
    quickPlayButton1.setOnAction(e -> new QuickPlayScene());

    //Custom game button -> Goes to login page  page
    customGameButton = new Button(Constants.customGameText);
    customGameButton.getStyleClass().add("button-menu");
    customGameButton.setOnAction(e -> new LogInScene());

    //Custom game button1 goes to custom game
    customGameButton1 = new Button(Constants.customGameText);
    customGameButton1.getStyleClass().add("button-menu");
    customGameButton1.setOnAction(e -> new CustomGameScene());

    //Logout button
    logOutButton = new Button(Constants.logOutText);
    logOutButton.getStyleClass().add("button-menu");
    logOutButton.setOnAction(e -> {
      loggedIn = false;
      window.setScene(frontPageScene);
    });

    //delete your user button
    deleteButton = new Button(Constants.deleteUserText);
    deleteButton.getStyleClass().add("button-menu");
    deleteButton.setOnAction(e -> {
      removeLineFromFile("text.txt", loggedUser + " " + loggedUsersPass);
      window.setScene(frontPageScene);
    });
    
    //Upload a sound button
    uploadButton = new Button(Constants.uploadSound);
    uploadButton.setOnAction(e -> new SaveFiles());

    //Layout Front Page when logged in
    layoutFrontpage1 = new VBox(20);
    layoutFrontpage1.setAlignment(Pos.CENTER);
    layoutFrontpage1.getChildren().addAll(labelFront, quickPlayButton1, customGameButton1, logOutButton, deleteButton, uploadButton);
    frontPageSceneLoggedIn = new Scene(layoutFrontpage1, 400, 700);
    frontPageSceneLoggedIn.getStylesheets().add("Theme.css");

    //Login page button -> Goes to login page
    loginPageButton = new Button(Constants.logInText);
    loginPageButton.getStyleClass().add("button-menu");
    loginPageButton.setOnAction(e -> new LogInScene());

    //sign up button -> Goes to sign up page
    signUpButton = new Button(Constants.signUpText);
    signUpButton.getStyleClass().add("button-menu");
    signUpButton.setOnAction(e -> new SignUpScene());

    //Layout Front Page
    layoutFrontpage = new VBox(20);
    layoutFrontpage.setAlignment(Pos.CENTER);
    layoutFrontpage.getChildren().addAll(labelFront, quickPlayButton, customGameButton, loginPageButton, signUpButton);
    frontPageScene = new Scene(layoutFrontpage, 400, 700);
    frontPageScene.getStylesheets().add("Theme.css");
    window.setScene(frontPageScene);
  }

  public static void  removeLineFromFile(String file, String lineToRemove) {
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
