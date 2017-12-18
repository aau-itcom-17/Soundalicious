import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Login scene can be reached through the main menu when nobody is logged in.
 * Also, after a completed signup user is redirected here.
 * It includes two textfields for username and password as well as a button to confirm the login.
 */
public class LogInScene extends FrontPageScene {

    private Label labelScreenTitle;
    private Label labelUsername, labelPassword;
    private TextField textfieldUsername, textfieldPassword;
    VBox layoutLogin;
    Button buttonLoginAction, buttonBackToMain;

    public LogInScene(){

        layoutLogin = new VBox(Constants.vBoxSpacing);
        labelScreenTitle = new Label(Constants.nameLogin);
        labelUsername = new Label(Constants.textUsername);
        textfieldUsername = new TextField();
        labelPassword = new Label(Constants.textPassword);
        textfieldPassword = new PasswordField();
        buttonLoginAction = new Button(Constants.nameLogin);
        buttonBackToMain = new Button(Constants.textBackToMain);
        logInPageScene = new Scene(layoutLogin, Constants.screenWidth, Constants.screenHeight);

        layoutLogin.getChildren().addAll(labelScreenTitle, labelUsername, textfieldUsername, labelPassword, textfieldPassword, buttonLoginAction, buttonBackToMain);

        labelScreenTitle.getStyleClass().add("label-headline");
        buttonLoginAction.getStyleClass().add("button-continue");
        buttonBackToMain.getStyleClass().add("button-menu");
        logInPageScene.getStylesheets().add(Constants.StyleSheetPath);
        layoutLogin.setAlignment(Pos.CENTER);

        //if button Enter is pressed on username textfield focus moves on the next field
        textfieldUsername.setOnKeyPressed((event) -> {
            if(event.getCode() == KeyCode.ENTER) {
                textfieldPassword.requestFocus();
            }
        });

        //if error was displayed when clicked on field it resets
        textfieldUsername.setOnMouseClicked(e -> {
            textfieldUsername.setStyle(null);
            textfieldPassword.setStyle(null);
            buttonLoginAction.setStyle(null);
            buttonLoginAction.setText(Constants.nameLogin);
        });
        textfieldPassword.setOnMouseClicked(e -> {
            textfieldUsername.setStyle(null);
            textfieldPassword.setStyle(null);
            buttonLoginAction.setStyle(null);
            buttonLoginAction.setText(Constants.nameLogin);
        });

        textfieldPassword.setOnKeyPressed((event) -> {
            if(event.getCode() == KeyCode.ENTER) {
                    startingLogin();
            }});

        buttonLoginAction.setOnAction(e -> startingLogin());

        buttonBackToMain.setOnAction(e -> window.setScene(frontPageScene));

        window.setScene(logInPageScene);
    }

    /**
     * The method deals with the most of the login logic and displays common errors.
     */
     private void startingLogin(){
        enteredUsername = textfieldUsername.getText();
        enteredUsername = enteredUsername.toLowerCase();
        enteredPass = textfieldPassword.getText();
        try {
            if (login(enteredUsername, enteredPass) && !enteredUsername.equals(Constants.adminUsername)) {
                user.setLoggedIn(true);
                user.setUserName(enteredUsername);
                new FrontPageScene();
            } else if (enteredUsername.equals(Constants.adminUsername) && enteredPass.equals(Constants.adminPass)){
                admin.setLoggedIn(true);
                new FrontPageScene();
            } else if(!SignUpScene.userExists(enteredUsername)){
                textfieldPassword.setText("");
                textfieldUsername.setText("");
                buttonLoginAction.setStyle("-fx-background-color: red");
                textfieldUsername.setStyle("-fx-background-color: #FEE4DF");
                textfieldPassword.setStyle("-fx-background-color: #FEE4DF");
                buttonLoginAction.setText(Constants.warningUserNotExists);
                textfieldUsername.requestFocus();
            } else {
                textfieldPassword.setText("");
                buttonLoginAction.setStyle("-fx-background-color: red");
                textfieldUsername.setStyle(null);
                textfieldPassword.setStyle("-fx-background-color: #FEE4DF");
                buttonLoginAction.setText(Constants.warningWrongPassword);
                textfieldPassword.requestFocus();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method only checks if username and password exist in the database.
     * Returns false if does not exist or wrong password, returns true and changes user state to logged in when user and password matches.
     */
    private Boolean login(String enteredUsername, String enteredPass) throws IOException {
        FileReader fileReader = new FileReader(Constants.userDatabaseFilePath);
        byte[] bytes = Files.readAllBytes(Paths.get(Constants.userDatabaseFilePath));
        String s = new String(bytes);
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while ((bufferedReader.readLine()) != null) {
                if (enteredUsername.equals("") || enteredPass.equals("")) {
                    return false;
                } else if (s.contains(enteredUsername + " " + enteredPass)) {
                    loggedUser = enteredUsername;
                    loggedUsersPass = enteredPass;
                    return true;
                }
            }
        }
        return false;
    }


}
