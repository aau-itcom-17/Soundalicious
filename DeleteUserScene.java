import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Scene that can be accessed by admin used for deleting other user with their password.
 * Includes textfield and button for confirming delete action.
 */
public class DeleteUserScene extends FrontPageScene {
    private Button buttonDeleteUser, buttonBackToMain;
    private TextField textfieldUsernameForDeleting;
    private Label labelScreenName;
    private VBox layoutDeleteUser;

    public DeleteUserScene() throws IOException {
        labelScreenName = new Label(Constants.textEnterUserForDeleting);
        textfieldUsernameForDeleting = new TextField();
        buttonDeleteUser = new Button(Constants.textDeleteUser);
        buttonBackToMain = new Button(Constants.textBackToMain);
        layoutDeleteUser = new VBox(Constants.vBoxSpacing);
        deleteUserScene = new Scene(layoutDeleteUser, Constants.screenWidth, Constants.screenHeight);

        buttonDeleteUser.getStyleClass().add("button-continue");
        buttonBackToMain.getStyleClass().add("button-menu");
        layoutDeleteUser.setAlignment(Pos.CENTER);
        deleteUserScene.getStylesheets().add(Constants.StyleSheetPath);

        layoutDeleteUser.getChildren().addAll(labelScreenName, textfieldUsernameForDeleting, buttonDeleteUser, buttonBackToMain);

        textfieldUsernameForDeleting.setOnMouseClicked(e -> {
            buttonDeleteUser.setStyle(null);
            buttonDeleteUser.setText(Constants.textDeleteUser);
        });

        textfieldUsernameForDeleting.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    if (findAndDeleteUser(textfieldUsernameForDeleting.getText())) {
                        buttonDeleteUser.setText(Constants.warningUserDeleted);
                        buttonDeleteUser.setStyle("-fx-background-color: green");
                        textfieldUsernameForDeleting.setText("");
                    } else {
                        buttonDeleteUser.setText(Constants.warningUserNotExists);
                        buttonDeleteUser.setStyle("-fx-background-color: red");
                        textfieldUsernameForDeleting.setText("");
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        buttonDeleteUser.setOnAction(e -> {
            try {
                if (findAndDeleteUser(textfieldUsernameForDeleting.getText())) {
                    buttonDeleteUser.setText(Constants.warningUserDeleted);
                    buttonDeleteUser.setStyle("-fx-background-color: green");
                    textfieldUsernameForDeleting.setText("");
                } else {
                    buttonDeleteUser.setText(Constants.warningUserNotExists);
                    buttonDeleteUser.setStyle("-fx-background-color: red");
                    textfieldUsernameForDeleting.setText("");
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        buttonBackToMain.setOnAction(e -> window.setScene(frontPageScene));

        window.setScene(deleteUserScene);
    }

    /**
     * Method used for finding username in the database and deleting it with the password.
     * Method returns boolean true if search and delete action were done, returns false - if not.
     */
    public static boolean findAndDeleteUser(String username) throws IOException {
        String line = "";
        String user;
        int spacePos;
        FileReader fileReader = new FileReader(Constants.userDatabaseFilePath);
        byte[] bytes = Files.readAllBytes(Paths.get(Constants.userDatabaseFilePath));
        String s = new String(bytes);
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while ((line = bufferedReader.readLine()) != null) {
                spacePos = line.indexOf(" ");
                user = line.substring(0, spacePos);
                if (user.equals(username) && !user.equals(Constants.adminUsername)) {
                    removeLineFromFile(Constants.userDatabaseFilePath, line);
                    return true;
                }
            }
        }
        return false;
    }
}



