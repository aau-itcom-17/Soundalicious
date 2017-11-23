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

  public SignUpScene(){
    //Sign up Scene
        //Label login page
       labelSignUp = new Label(Constants.signUpText);

        //Username label
        usernameSignUpLabel = new Label(Constants.newUserText);

        //Username input
        usernameSignUpInput = new TextField();

        //Password label
        passwordSignUpLabel = new Label("Your password:");

        //Password input
        passwordSignUpInput = new PasswordField();

        //Repeat password label
        passwordRepeatSignUpLabel = new Label("Repeat password:");

        //Repeat password input
        passwordRepeatSignUpInput = new PasswordField();

        //signup error/message
        Label signUpError = new Label("Passwords do not match");
        signUpError.setVisible(false);

        //Create new user Button
        Button createUserButton = new Button("Create a new user");
        //Action when the button is clicked on
        createUserButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                signUpError.setVisible(false); //do not show message from the previous time
                enteredUsername = usernameSignUpInput.getText(); //save text from TextField to String
                enteredUsername = enteredUsername.toLowerCase(); //convert String to lowercase
                enteredPass = passwordSignUpInput.getText(); //save password to String
               try{
                    if(enteredUsername.isEmpty() || enteredUsername.isEmpty() || passwordRepeatSignUpInput.getText().isEmpty()){ //checks if any line is empty
                        signUpError.setText("You left some fields empty");
                        signUpError.setStyle("-fx-text-fill: red");
                        signUpError.setVisible(true);
                    }
                    else
                    if(containsSpecChar(enteredUsername)){ //checks if username contains spec chars
                        signUpError.setText("Username should not contain special characters");
                        signUpError.setStyle("-fx-text-fill: red");
                        signUpError.setVisible(true);
                    }
                    else if(containsSpecChar(enteredPass)){ //checks if username contains spec chars
                        signUpError.setText("Password should not contain special characters");
                        signUpError.setStyle("-fx-text-fill: red");
                        signUpError.setVisible(true);
                    }
                    else if(passwordSignUpInput.getText().equals((passwordRepeatSignUpInput.getText()))){ //checking if two TextFields with passwords match
                        if(!userExists(enteredUsername)){ //checking if username does not exist
                            signUpError.setStyle("-fx-text-fill: green");
                            signUpError.setText("New user created");  //changing error message
                            signUpError.setVisible(true); //showing error message
                            writeToFile(enteredUsername, enteredPass);
                        }
                        else{
                            signUpError.setText("Username already exists");
                            signUpError.setStyle("-fx-text-fill: red");
                            signUpError.setVisible(true);
                            usernameSignUpInput.setText("");
                            passwordSignUpInput.setText("");
                            passwordRepeatSignUpInput.setText("");
                        }
                    }
                    else {
                        signUpError.setText("Passwords do not match");
                        signUpError.setStyle("-fx-text-fill: red");
                        signUpError.setVisible(true);
                        passwordSignUpInput.setText("");
                        passwordRepeatSignUpInput.setText("");
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        //Button back to front on login page
        Button frontPageButton4 = new Button("Go back to front page");
        frontPageButton4.setOnAction(e -> window.setScene(frontPageScene));

        VBox signUpLayout = new VBox(20);
        signUpLayout.setAlignment(Pos.CENTER);
        signUpLayout.getChildren().addAll(labelSignUp, usernameSignUpLabel, usernameSignUpInput, passwordSignUpLabel, passwordSignUpInput, passwordRepeatSignUpLabel, passwordRepeatSignUpInput, signUpError, createUserButton, frontPageButton4);
        signUpScene = new Scene(signUpLayout, 400, 600);

      signUpScene.getStylesheets().add("Theme.css");
      window.setScene(signUpScene);
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
