import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.*;
import java.io.FileWriter;

import javax.swing.*;
import java.io.IOException;

public class SignUpScene extends FrontPageScene {

  Label labelSignUp, usernameSignUpLabel, passwordSignUpLabel, passwordRepeatSignUpLabel;
  TextField usernameSignUpInput, passwordSignUpInput;
  PasswordField passwordRepeatSignUpInput;
  Button createUserButton;

  public SignUpScene(){
    //Sign up Scene
        //Label login page
       labelSignUp = new Label(Constants.signUpText);
       labelSignUp.getStyleClass().add("label-headline");

        //Username label
        usernameSignUpLabel = new Label(Constants.newUserText);

        //Username input
        usernameSignUpInput = new TextField();
        usernameSignUpInput.setOnKeyPressed((event) -> {
          if(event.getCode() == KeyCode.ENTER) {
              passwordSignUpInput.requestFocus();
          }
      });

        //Password label
        passwordSignUpLabel = new Label("Your password:");

        //Password input
        passwordSignUpInput = new PasswordField();
         passwordSignUpInput.setOnKeyPressed((event) -> {
          if(event.getCode() == KeyCode.ENTER) {
              passwordRepeatSignUpInput.requestFocus();
          }
         });

        //Repeat password label
        passwordRepeatSignUpLabel = new Label("Repeat password:");

        //Repeat password input
        passwordRepeatSignUpInput = new PasswordField();
          passwordRepeatSignUpInput.setOnKeyPressed((event) -> {
              if(event.getCode() == KeyCode.ENTER) {
                  signUp();
              }
          });



        //Create new user Button
      createUserButton = new Button("Create a new user");
      createUserButton.getStyleClass().add("button-continue");
      createUserButton.getStyleClass().add("button-menu");
      //Action when the button is clicked on
        createUserButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                signUp();
            }
        });

        //Button back to front on login page
        Button frontPageButton4 = new Button("Go back to front page");
      frontPageButton4.getStyleClass().add("button-menu");
      frontPageButton4.setOnAction(e -> window.setScene(frontPageScene));

        VBox signUpLayout = new VBox(20);
        signUpLayout.setAlignment(Pos.CENTER);
        signUpLayout.getChildren().addAll(labelSignUp, usernameSignUpLabel, usernameSignUpInput, passwordSignUpLabel, passwordSignUpInput, passwordRepeatSignUpLabel, passwordRepeatSignUpInput, createUserButton, frontPageButton4);
        signUpScene = new Scene(signUpLayout, 400, 700);

      signUpScene.getStylesheets().add("Theme.css");
      window.setScene(signUpScene);
  }

  public void signUp (){
      enteredUsername = usernameSignUpInput.getText(); //save text from TextField to String
      enteredUsername = enteredUsername.toLowerCase(); //convert String to lowercase
      enteredPass = passwordSignUpInput.getText(); //save password to String
      try{
          if(enteredUsername.isEmpty() || enteredPass.isEmpty() || passwordRepeatSignUpInput.getText().isEmpty()){ //checks if any line is empty
              createUserButton.setStyle("-fx-background-color: red");
              createUserButton.setText("You left some fields empty");
              if(enteredUsername.isEmpty()) usernameSignUpInput.setStyle("-fx-background-color: #FEE4DF");
              if(enteredPass.isEmpty()) passwordSignUpInput.setStyle("-fx-background-color: #FEE4DF");
              if(passwordRepeatSignUpInput.getText().isEmpty()) passwordRepeatSignUpInput.setStyle("-fx-background-color: #FEE4DF");

          }
          else if(containsSpecChar(enteredUsername)){ //checks if username contains spec chars
              createUserButton.setStyle("-fx-background-color: red");
              createUserButton.setText("Name contains spec. chars");
              usernameSignUpInput.setText("");
              usernameSignUpInput.requestFocus();
              usernameSignUpInput.setStyle("-fx-background-color: #FEE4DF");

          }
          else if(containsSpecChar(enteredPass)){ //checks if username contains spec chars
              createUserButton.setStyle("-fx-background-color: red");
              createUserButton.setText("Pass contains spec. chars");
              passwordSignUpInput.setText("");
              passwordRepeatSignUpInput.setText("");
              passwordSignUpInput.requestFocus();
              passwordSignUpInput.setStyle("-fx-background-color: #FEE4DF");
              passwordRepeatSignUpInput.setStyle("-fx-background-color: #FEE4DF");
          }
          else if(passwordSignUpInput.getText().equals((passwordRepeatSignUpInput.getText()))){ //checking if two TextFields with passwords match
              if(!userExists(enteredUsername)){ //checking if username does not exist
                  System.out.println("New user created" + enteredUsername + " " + enteredPass);
                  writeToFile(enteredUsername, enteredPass);
                  new LogInScene();
              }
              else{
                  createUserButton.setStyle("-fx-background-color: red");
                  createUserButton.setText("Username already exists");
                  usernameSignUpInput.setText("");
                  passwordSignUpInput.setText("");
                  passwordRepeatSignUpInput.setText("");
                  usernameSignUpInput.setStyle("-fx-background-color: #FEE4DF");
                  passwordSignUpInput.setStyle("-fx-background-color: #FEE4DF");
                  passwordRepeatSignUpInput.setStyle("-fx-background-color: #FEE4DF");
              }
          }
          else {
              createUserButton.setStyle("-fx-background-color: red");
              createUserButton.setText("Passwords do not match");
              passwordSignUpInput.setText("");
              passwordRepeatSignUpInput.setText("");
              passwordSignUpInput.setStyle("-fx-background-color: #FEE4DF");
              passwordRepeatSignUpInput.setStyle("-fx-background-color: #FEE4DF");
          }
      }
      catch (IOException e) {
          e.printStackTrace();
      }


  }

  public static boolean userExists(String enteredUsername) throws IOException {
          BufferedReader bufferedReader = new BufferedReader(new FileReader("text.txt"));
          String existingUser;

          while ((existingUser = bufferedReader.readLine()) != null)
          {
                existingUser = existingUser.substring(0, existingUser.indexOf(" "));
                if(existingUser.equals(enteredUsername)){
                  return true;
                }
          }
          return false;
      }

  public static void writeToFile(String username, String password) throws IOException {
        // append "true" saves the input to the text.txt file. Also when the application opens again.
        // We need to make a way for a user to delete itself.
        BufferedWriter out = new BufferedWriter(new FileWriter("text.txt", true));
        out.write(username + " " + password);
        out.newLine();
        out.close();
    }

  public static boolean containsSpecChar (String enteredString){
        Pattern p = Pattern.compile("[^a-z0-9]");
        Matcher m = p.matcher(enteredString);
        boolean b = m.find();

        if(b) return true;
        return false;
    }

}
