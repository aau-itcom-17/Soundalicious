import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.input.KeyCode;


import java.io.IOException;

public class LogInScene extends FrontPageScene {

    public static Label labelLogin, usernameLabel, passwordLabel, logInError, logInMessage, adminMessage;
    public static TextField usernameInput, passwordInput;
    VBox logInPageLayout;
    Button logInButton, frontPageButton3;

    public LogInScene(){

        //Label login page
        labelLogin = new Label(Constants.gameName);
        labelLogin.getStyleClass().add("label-headline");

        //Username label
        usernameLabel = new Label(Constants.usernameText);

        //Username input
        usernameInput = new TextField();
        usernameInput.setOnKeyPressed((event) -> {
            if(event.getCode() == KeyCode.ENTER) {
                passwordInput.requestFocus();
            }
        });


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
        logInButton.getStyleClass().add("button-continue");

        // The addEventHandler handles more than one event, which makes it so we don't have to click the login button twice.
        passwordInput.setOnKeyPressed((event) -> {
            if(event.getCode() == KeyCode.ENTER) {
                {
                    startingLogin();
                }
                }});




        logInButton.addEventHandler(ActionEvent.ACTION, event -> {
            startingLogin();
        });

        //Button back to front on login page
        frontPageButton3 = new Button(Constants.goToMainText);
        frontPageButton3.getStyleClass().add("button-menu");
        frontPageButton3.setOnAction(e -> window.setScene(frontPageScene));

        adminMessage = new Label (Constants.adminLoggedIn);
        adminMessage.setVisible(false);

        //Layout custom game
        logInPageLayout = new VBox(20);
        logInPageLayout.setAlignment(Pos.CENTER);
        logInPageLayout.getChildren().addAll(labelLogin, usernameLabel, usernameInput, passwordLabel,  passwordInput, logInError, logInMessage, logInButton, frontPageButton3, adminMessage);
        logInPageScene = new Scene(logInPageLayout, 400, 700);

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

    public void startingLogin(){
        enteredUsername = usernameInput.getText();
        enteredUsername = enteredUsername.toLowerCase();
        enteredPass = passwordInput.getText();
        try {
            if (login(enteredUsername, enteredPass) && !enteredUsername.equals("admin")) {
                user.setLoggedIn(true);
                user.setUserName(enteredUsername);
                logInMessage.setVisible(true);
                logInError.setVisible(false);
                new FrontPageScene();
            } else if (enteredUsername.equals("admin") && enteredPass.equals("password")){
                    admin.setLoggedIn(true);
                    new FrontPageScene();
            } else {
                passwordInput.setText("");
                logInMessage.setVisible(false);
                logInError.setVisible(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
