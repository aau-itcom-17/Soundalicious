import com.sun.tools.internal.jxc.ap.Const;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Signup scene can be reached from the main menu when user is not logged in.
 * It includes 3 text fields - user name and 2 password text fields to repeat password twice.
 * At the bottom - buttons for confirming or going back to the main.
 * If signup was successful app proceeds to the Login scene, if not - user can try again.
 */
public class SignUpScene extends FrontPageScene {

    private Label labelScreenTitle, labelUsername, labelPassword, passwordRepeatSignUpLabel;
    private TextField textfieldUsername, textfieldPassword;
    private PasswordField passwordRepeatSignUpInput;
    private Button buttonCreateNewUser, buttonBackToMain;
    private VBox signUpLayout;

    public SignUpScene() {
        labelScreenTitle = new Label(Constants.nameSignup);
        textfieldUsername = new TextField();
        labelUsername = new Label(Constants.textNewUsername);
        labelPassword = new Label(Constants.textPassword + ":");
        passwordRepeatSignUpLabel = new Label(Constants.textRepeatPassword + ": ");
        buttonCreateNewUser = new Button(Constants.textCreateNewUser);
        buttonBackToMain = new Button(Constants.textBackToMain);
        textfieldPassword = new PasswordField();
        passwordRepeatSignUpInput = new PasswordField();
        signUpLayout = new VBox(Constants.vBoxSpacing);
        signUpScene = new Scene(signUpLayout, Constants.screenWidth, Constants.screenHeight);

        labelScreenTitle.getStyleClass().add("label-headline");
        buttonCreateNewUser.getStyleClass().add("button-continue");
        buttonCreateNewUser.getStyleClass().add("button-menu");
        buttonBackToMain.getStyleClass().add("button-menu");
        signUpLayout.setAlignment(Pos.CENTER);
        signUpScene.getStylesheets().add(Constants.StyleSheetPath);

        signUpLayout.getChildren().addAll(labelScreenTitle, labelUsername, textfieldUsername, labelPassword, textfieldPassword, passwordRepeatSignUpLabel, passwordRepeatSignUpInput, buttonCreateNewUser, buttonBackToMain);

        textfieldUsername.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                textfieldPassword.requestFocus();
            }
        });

        textfieldPassword.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                passwordRepeatSignUpInput.requestFocus();
            }
        });

        passwordRepeatSignUpInput.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                signUp();
            }
        });

        buttonCreateNewUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                signUp();
            }
        });

        buttonBackToMain.setOnAction(e -> window.setScene(frontPageScene));
        window.setScene(signUpScene);
    }

    /**
     * Deals with the logic of signup (warnings of existing user, not matching password etc.)
     */
    public void signUp() {
        enteredUsername = textfieldUsername.getText(); //save text from TextField to String
        enteredUsername = enteredUsername.toLowerCase(); //convert String to lowercase
        enteredPass = textfieldPassword.getText(); //save password to String
        try {
            if (enteredUsername.isEmpty() || enteredPass.isEmpty() || passwordRepeatSignUpInput.getText().isEmpty()) { //checks if any line is empty
                buttonCreateNewUser.setStyle("-fx-background-color: red");
                buttonCreateNewUser.setText(Constants.warningEmptyFields);
                if (enteredUsername.isEmpty()) textfieldUsername.setStyle("-fx-background-color: #FEE4DF");
                if (enteredPass.isEmpty()) textfieldPassword.setStyle("-fx-background-color: #FEE4DF");
                if (passwordRepeatSignUpInput.getText().isEmpty())
                    passwordRepeatSignUpInput.setStyle("-fx-background-color: #FEE4DF");

            } else if (containsSpecChar(enteredUsername)) { //checks if username contains spec chars
                buttonCreateNewUser.setStyle("-fx-background-color: red");
                buttonCreateNewUser.setText(Constants.warningNameContainsSpecChars);
                textfieldUsername.setText("");
                textfieldUsername.requestFocus();
                textfieldUsername.setStyle("-fx-background-color: #FEE4DF");

            } else if (containsSpecChar(enteredPass)) { //checks if username contains spec chars
                buttonCreateNewUser.setStyle("-fx-background-color: red");
                buttonCreateNewUser.setText(Constants.warningPassContainsSpecChars);
                textfieldPassword.setText("");
                passwordRepeatSignUpInput.setText("");
                textfieldPassword.requestFocus();
                textfieldPassword.setStyle("-fx-background-color: #FEE4DF");
                passwordRepeatSignUpInput.setStyle("-fx-background-color: #FEE4DF");
            } else if (textfieldPassword.getText().equals((passwordRepeatSignUpInput.getText()))) { //checking if two TextFields with passwords match
                if (!userExists(enteredUsername)) { //checking if username does not exist
                    user.writeOnHistoryFile(Constants.textNewUserCreated);
                    writeToFile(enteredUsername, enteredPass);
                    new LogInScene();
                } else {
                    buttonCreateNewUser.setStyle("-fx-background-color: red");
                    buttonCreateNewUser.setText(Constants.warningUserExists);
                    textfieldUsername.setText("");
                    textfieldPassword.setText("");
                    passwordRepeatSignUpInput.setText("");
                    textfieldUsername.setStyle("-fx-background-color: #FEE4DF");
                    textfieldPassword.setStyle("-fx-background-color: #FEE4DF");
                    passwordRepeatSignUpInput.setStyle("-fx-background-color: #FEE4DF");
                }
            } else {
                buttonCreateNewUser.setStyle("-fx-background-color: red");
                buttonCreateNewUser.setText(Constants.warningDoNotMatch);
                textfieldPassword.setText("");
                passwordRepeatSignUpInput.setText("");
                textfieldPassword.setStyle("-fx-background-color: #FEE4DF");
                passwordRepeatSignUpInput.setStyle("-fx-background-color: #FEE4DF");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * checks in the file if user already exists, purpose - to not sign up user with the same login
     * return true if exists, false - if not
     */
    public static boolean userExists(String enteredUsername) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.userDatabaseFilePath));
        String existingUser;

        while ((existingUser = bufferedReader.readLine()) != null) {
            existingUser = existingUser.substring(0, existingUser.indexOf(" "));
            if (existingUser.equals(enteredUsername)) {
                return true;
            }
        }
        return false;
    }

    /**
     * write single line of text to file (username + " " + password)
     */
    public static void writeToFile(String username, String password) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(Constants.userDatabaseFilePath, true));
        out.write(username + " " + password);
        out.newLine();
        out.close();
    }

    /**
     * checks if string contains spec chars, used in signUp() for logic
     * returns true if contains, false - if not
     */
    public static boolean containsSpecChar(String enteredString) {
        Pattern p = Pattern.compile("[^a-z0-9]");
        Matcher m = p.matcher(enteredString);
        boolean b = m.find();

        if (b) return true;
        return false;
    }

}
