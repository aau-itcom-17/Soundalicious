import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.MouseAdapter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DeleteUserScene extends FrontPageScene{
    Label labelFront;
    Button deleteButton, frontPageButton;
    TextField usernameInput;
    Label title;
    VBox layoutDeleteUserScene;
    String username;



    public DeleteUserScene()  throws IOException  {
        title = new Label("Enter username which you want to delete");
        usernameInput = new TextField();
        usernameInput.setOnMouseClicked(e -> {
            deleteButton.setStyle(null);
            deleteButton.setText("Delete user");
        });


        deleteButton = new Button("Delete user");
        deleteButton.getStyleClass().add("button-continue");
        usernameInput.setOnKeyPressed((event) -> {
            if(event.getCode() == KeyCode.ENTER) {
                username = usernameInput.getText();
                try {
                    if(findAndDeleteUser(username)) {
                        try {
                            user.writeOnHistoryFile("\""+ username + "\" was deleted");
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        deleteButton.setText("User deleted");
                        deleteButton.setStyle("-fx-background-color: green");
                        usernameInput.setText("");
                    }
                    else {
                        deleteButton.setText("User was not found");
                        deleteButton.setStyle("-fx-background-color: red");
                        usernameInput.setText("");
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }});
        deleteButton.setOnAction(e -> {
            username = usernameInput.getText();
            try {
                if(findAndDeleteUser(username)) {
                    deleteButton.setText("User deleted");
                    deleteButton.setStyle("-fx-background-color: green");
                    usernameInput.setText("");
                }
                else {
                    deleteButton.setText("User was not found");
                    deleteButton.setStyle("-fx-background-color: red");
                    usernameInput.setText("");
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        frontPageButton = new Button(Constants.goToMainText);
        frontPageButton.getStyleClass().add("button-menu");
        frontPageButton.setOnAction(e -> window.setScene(frontPageScene));

        layoutDeleteUserScene = new VBox(20);
        layoutDeleteUserScene.setAlignment(Pos.CENTER);
        layoutDeleteUserScene.getChildren().addAll(title, usernameInput, deleteButton, frontPageButton);



        deleteUserScene = new Scene(layoutDeleteUserScene, 400, 700);
        deleteUserScene.getStylesheets().add("Theme.css");
        window.setScene(deleteUserScene);
    }

    public static boolean findAndDeleteUser(String username) throws IOException {
        String line = "";
        String user;
        int spacePos;
        FileReader fileReader = new FileReader("text.txt");
        byte[] bytes = Files.readAllBytes(Paths.get("text.txt"));
        String s = new String(bytes);
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while ((line = bufferedReader.readLine()) != null) {
                spacePos = line.indexOf(" ");
                user = line.substring(0, spacePos);
                if(user.equals(username) && !user.equals("admin")) {
                        removeLineFromFile("text.txt", line);
                        return true;
                    }
                }
                // process the line.
            }
        return false;
        }
    }



