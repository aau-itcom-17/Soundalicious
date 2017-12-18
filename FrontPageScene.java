import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

/**
 * It is the first screen that user sees, it is called from the Main.java.
 * It has different menu options when nobody is logged in or admin/user is logged in.
 * When nobody is logged in it shows Quick play, Custom games (when pressed just goes to login), Log in and Sign up options as buttons.
 * Admin sees Quick play, Custom game, Upload sound, Delete users and Log out options as buttons.
 * Regular user sees Quick play, Custom game, Upload sound, Delete your user and Log out option as buttons.
 * The program goes next to the item that is chosen in menu, if somebody is logged in title displays the name of the user.
 */
public class FrontPageScene extends Main {

    private Label labelScreenTitle;
    private Button buttonQuickPlay, buttonCustomGame, buttonLogin, buttonSignup, buttonCancel;
    private Button buttonUserHistory, buttonUserLogOut, buttonUserDeleteYourAcc, buttonUserUploadSound, buttonAdminDeleteOtherUsers;
    private VBox layoutFrontpage;

    public FrontPageScene() {

        labelScreenTitle = new Label(Constants.nameGame);
        buttonQuickPlay = new Button(Constants.nameQuickPlay);
        buttonCustomGame = new Button(Constants.nameCustomGame);
        buttonLogin = new Button(Constants.nameLogin);
        buttonSignup = new Button(Constants.nameSignup);
        buttonUserLogOut = new Button(Constants.nameLogout);
        buttonUserDeleteYourAcc = new Button(Constants.nameDeleteYouUser);
        buttonUserUploadSound = new Button(Constants.nameUploadQuestion);
        buttonAdminDeleteOtherUsers = new Button(Constants.nameDeleteOtherUsers);
        layoutFrontpage = new VBox(Constants.vBoxSpacing);
        buttonUserHistory = new Button(Constants.textSeeHistory);
        buttonCancel = new Button(Constants.textCancel);
        frontPageScene = new Scene(layoutFrontpage, Constants.screenWidth, Constants.screenHeight);


        labelScreenTitle.getStyleClass().add("label-headline");
        buttonQuickPlay.getStyleClass().add("button-quickplay");
        buttonCustomGame.getStyleClass().add("button-menu");
        buttonLogin.getStyleClass().add("button-menu");
        buttonSignup.getStyleClass().add("button-menu");
        buttonUserLogOut.getStyleClass().add("button-menu");
        buttonUserDeleteYourAcc.getStyleClass().add("button-menu");
        buttonUserUploadSound.getStyleClass().add("button-menu");
        buttonAdminDeleteOtherUsers.getStyleClass().add("button-menu");
        buttonUserHistory.getStyleClass().add("button-menu");
        layoutFrontpage.setAlignment(Pos.CENTER);
        frontPageScene.getStylesheets().add(Constants.StyleSheetPath);

        //if statement for displaying different layout for user, admin and not logged in user
        if (user.isLoggedIn) {
            labelScreenTitle.setText(Constants.messageGreetingWord + " " + user.getUserName().toUpperCase() + "!");
            layoutFrontpage.getChildren().addAll(labelScreenTitle, buttonQuickPlay, buttonCustomGame, buttonUserUploadSound, buttonUserHistory, buttonUserDeleteYourAcc, buttonUserLogOut);
        } else if (admin.isLoggedIn) {
            labelScreenTitle.setText(Constants.messageGreetingWord + " " + Constants.nameAdmin);
            layoutFrontpage.getChildren().addAll(labelScreenTitle, buttonQuickPlay, buttonCustomGame, buttonUserUploadSound, buttonUserHistory, buttonAdminDeleteOtherUsers, buttonUserLogOut);
        } else {
            layoutFrontpage.getChildren().addAll(labelScreenTitle, buttonQuickPlay, buttonCustomGame, buttonLogin, buttonSignup);
        }

        buttonQuickPlay.setOnAction(e -> {
            try {
                Questions.getRandomQuestions(questions, rQuestions);
            } catch (ParserConfigurationException e1) {
                e1.printStackTrace();
            } catch (SAXException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            new QuickPlayScene();
        });

        /*
         * custom game scene should be available only to logged in users
         * so if user is not logged in it goes to log in scene for user to log in
         * this is the only one main menu button which is for logged in users, but can be seen by everybody
         */
        buttonCustomGame.setOnAction(e -> {
            if (user.isLoggedIn || admin.isLoggedIn) {
                selectedCustomGame = true;
                new CustomGameScene();
            } else {
                new LogInScene();
            }
        });

        buttonUserHistory.setOnAction(e -> new HistoryScene());

        buttonLogin.setOnAction(e -> new LogInScene());

        buttonSignup.setOnAction(e -> new SignUpScene());

        buttonUserLogOut.setOnAction(e -> {
            try {
                user.writeOnHistoryFile(Constants.textLoggedOut);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            user.setLoggedIn(false);
            admin.setLoggedIn(false);
            new FrontPageScene();
        });

        /*
         * before user can delete his/her profile button asks again to confirm the action
         */
        buttonUserDeleteYourAcc.setOnAction(e -> {
            buttonUserDeleteYourAcc.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            buttonUserDeleteYourAcc.setText(Constants.warningConfirmAction);
            frontPageScene = new Scene(layoutFrontpage, Constants.screenWidth, Constants.screenHeight);
            frontPageScene.getStylesheets().add(Constants.StyleSheetPath);
            window.setScene(frontPageScene);
            buttonUserDeleteYourAcc.setOnAction(f -> {
                try {
                    user.writeOnHistoryFile(Constants.textUserDeletedSelf);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                removeLineFromFile(Constants.userDatabaseFilePath, loggedUser + " " + loggedUsersPass);
                File fileForDeleting = new File( Constants.userHistoryPath + "/" + user.getUserName() + ".txt");
                fileForDeleting.delete();
                user.setLoggedIn(false);
                new FrontPageScene();
            });
        });

        buttonUserUploadSound.setOnAction(e -> new UploadQuestionScene());

        buttonAdminDeleteOtherUsers.setOnAction(event -> {
            try {
                new DeleteUserScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        window.setScene(frontPageScene);
    }

    /**
     * Method used for removing user from a file
     * Takes input of two Strings (file name and the line to remove from the file (in this case "username + " " + password").
     * Used in several places in the code
     */
    public static void removeLineFromFile(String file, String lineToRemove) {
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
