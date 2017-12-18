import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * The main class that starts the program.
 * <p>
 * Soundalicious is a sound based quiz platform and game with questions related pop culture, movies, songs or just general memes.
 * This program was developed for AAU CPH Project 1.
 *
 * @author Anton Krogh Petersen
 * @author Bastian Hojbjerre
 * @author Gustav Jorgensen
 * @author Lasse Skjott Hansen
 * @author Marcus Olesen
 * @author Karolis Daugerdas
 * @author Nisanth Elangkumaran
 * <p>
 * The main class of the application.
 * Initiate the Stage (window) creation and goes to the frontPageScene.class
 * Declares public variables used in the globally.
 */

public class Main extends Application {

    public static Stage window;
    public static Scene frontPageScene, quickPlayScene, customGameScene, logInPageScene, signUpScene, playGameScene, scoreBoardPageScene, finalScoreBoardPageScene, saveFilesScene, deleteUserScene, gameRulesScene, teamNameScene, historyScene;
    public static String enteredUsername, enteredPass;
    public static String loggedUser, loggedUsersPass;
    public static int numOfQuestions = 10;
    public static int numOfTeams = 2;
    public static List<Question> questions, rQuestions, themeQuestions;
    public static List<RadioButton> answers;
    public static List<Team> teams;
    public static int counterOfQuestions;
    public static User user = new User();
    public static Admin admin = new Admin();
    public static int tCount = 1, qCount = 1; //used in quickplay scene for settings
    public static Boolean selectedCustomGame = false;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StartUpSavingFolders.startUpSavingFolders();
        window = primaryStage;
        new FrontPageScene();
        window.setTitle(Constants.nameGame);
        window.show();

        questions = new ArrayList<>();
        rQuestions = new ArrayList<>();
        themeQuestions = new ArrayList<>();
        answers = new ArrayList<>();
        teams = new ArrayList<>();
        counterOfQuestions = 0;

        Questions.readQuestionsFromFile(questions, rQuestions);
    }
}
