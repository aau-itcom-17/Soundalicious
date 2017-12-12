import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.*;

public class FrontPageScene extends Main {

  Label labelFront;
  Button quickPlayButton, customGameButton, loginPageButton, signUpButton;
  Button  logOutButton, deleteButton, uploadButton;
  VBox layoutFrontpage ;


  public FrontPageScene() {


    //Label front page
    labelFront = new Label(Constants.gameName);
    labelFront.getStyleClass().add("label-headline");

    if(user.isLoggedIn()){
      labelFront.setText("Hi " + user.getUserName().toUpperCase() + "!");
    }

    //Quick play button -> Goes to Quickplay page
    quickPlayButton = new Button(Constants.quickPlayName);
    quickPlayButton.getStyleClass().add("button-quickplay");
    quickPlayButton.setOnAction(e -> new QuickPlayScene());


    //Custom game button -> Goes to login page  page
    customGameButton = new Button(Constants.customGameText);
    customGameButton.getStyleClass().add("button-menu");


    if(user.isLoggedIn()){
      customGameButton.setOnAction(e -> new CustomGameScene());
    }else{
      customGameButton.setOnAction(e -> new LogInScene());
    }

    if (user.isLoggedIn()) {
      //Logout button
      logOutButton = new Button(Constants.logOutText);
      logOutButton.getStyleClass().add("button-menu");
      logOutButton.setOnAction(e -> {
        user.setLoggedIn(false);
        new FrontPageScene();
      });

    }


    //delete your user button
    if(user.isLoggedIn()) {
      deleteButton = new Button(Constants.deleteUserText);
      deleteButton.getStyleClass().add("button-menu");
      deleteButton.setOnAction(e -> {
        removeLineFromFile("text.txt", loggedUser + " " + loggedUsersPass);
        window.setScene(frontPageScene);
      });


      //Upload a sound button
      uploadButton = new Button(Constants.uploadSound);
      uploadButton.getStyleClass().add("button-menu");
      uploadButton.setOnAction(e -> new SaveFiles());

    }

    //Login page button -> Goes to login page
    loginPageButton = new Button(Constants.logInText);
    loginPageButton.getStyleClass().add("button-menu");
    loginPageButton.setOnAction(e -> new LogInScene());


    //sign up button -> Goes to sign up page
    signUpButton = new Button(Constants.signUpText);
    signUpButton.getStyleClass().add("button-menu");
    signUpButton.setOnAction(e -> new SignUpScene());


    System.out.println("USER IS LOGGED IN/OUT :" + user.isLoggedIn());
    //Layout Front Page
    layoutFrontpage = new VBox(20);
    layoutFrontpage.setPadding(new Insets(150, 0, 150, 0));

    layoutFrontpage.setAlignment(Pos.CENTER);
    if (user.isLoggedIn()){
      System.out.println("hey");
      layoutFrontpage.getChildren().addAll(labelFront, quickPlayButton, customGameButton, uploadButton, deleteButton, logOutButton);
    }else {
      layoutFrontpage.getChildren().addAll(labelFront, quickPlayButton, customGameButton, loginPageButton, signUpButton);
    }
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
