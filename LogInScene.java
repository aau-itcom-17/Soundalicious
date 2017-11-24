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
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import java.io.IOException;

public class LogInScene extends FrontPageScene {

    Label labelLogin, usernameLabel, passwordLabel, logInError, logInMessage, adminMessage;
    TextField usernameInput, passwordInput;
    VBox logInPageLayout;
    Button logInButton, frontPageButton3;

    public LogInScene(){

        //Label login page
        labelLogin = new Label(Constants.gameName);

        //Username label
        usernameLabel = new Label(Constants.usernameText);

        //Username input
        usernameInput = new TextField();

        //Password label
        passwordLabel = new Label(Constants.passwordText);

        //Password input
        passwordInput = new PasswordField();

        // Error and message labels
        logInError = new Label(Constants.wrongPasswordText);
        logInError.setStyle("-fx-text-fill: red");
        logInError.setVisible(false);
        logInMessage = new Label(Constants.succesfulLogInText);
        logInMessage.setStyle("-fx-text-fill: green");
        logInMessage.setVisible(false);

        logInButton = new Button(Constants.logInText);
        // The addEventHandler handles more than one event, which makes it so we don't have to click the login button twice.
        logInButton.addEventHandler(ActionEvent.ACTION, event -> {
            enteredUsername = usernameInput.getText();
            enteredUsername = enteredUsername.toLowerCase();
            enteredPass = passwordInput.getText();
            try {
                if (login(enteredUsername, enteredPass)){
                    loggedIn = true;
                    logInMessage.setVisible(true);
                    logInError.setVisible(false);
                    logInButton.setOnAction(e -> window.setScene(frontPageSceneLoggedIn));

                    if (login(Constants.adminUsername, Constants.adminPass)){
                        // admin properties so that the admin can delete files and questions if he wants. but only stuff thats uploaded by users.
                        // the login should be assigned to a User with name and stuff. That user should be allowed to upload sound files and make questions.
                        // the User should be allowed to customize own questions and to delete his how shizzle.

                        adminMessage = new Label (Constants.adminLoggedIn);
                        adminMessage.setVisible(true);
                    }
                }
                else {
                    passwordInput.setText("");
                    logInMessage.setVisible(false);
                    logInError.setVisible(true);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //Button back to front on login page
        frontPageButton3 = new Button(Constants.goToMainText);
        frontPageButton3.setOnAction(e -> window.setScene(frontPageScene));

        //Layout custom game
        logInPageLayout = new VBox(20);
        logInPageLayout.setAlignment(Pos.CENTER);
        logInPageLayout.getChildren().addAll(labelLogin, usernameLabel, usernameInput, passwordLabel,  passwordInput, logInError, logInMessage, logInButton, frontPageButton3);
        logInPageScene = new Scene(logInPageLayout, 400, 600);

        logInPageScene.getStylesheets().add("Theme.css");
        window.setScene(logInPageScene);
    }

    public static boolean containsSpecChar (String enteredString){
          Pattern p = Pattern.compile("[^a-z0-9]");
          Matcher m = p.matcher(enteredString);
          boolean b = m.find();
          if(b) return true;
          return false;
      }

    public static boolean login(String enteredUsername, String enteredPass) throws IOException {
        FileReader fileReader = new FileReader("text.txt");
        byte[] bytes = Files.readAllBytes(Paths.get("text.txt"));
        String s = new String(bytes);
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (enteredUsername.equals("") || enteredPass.equals("")) {
                    return false;
                } else if (s.contains(enteredUsername + " " + enteredPass)) {
                    loggedUser = enteredUsername;
                    loggedUsersPass = enteredPass;
                    return true;
                }
                // process the line.
            }
        }
        return false;
    }


}
