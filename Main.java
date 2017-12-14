import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the main class....
 *
 * Hello
 */
public class Main extends Application {


    public static Stage window;
    public static Scene frontPageScene, frontPageSceneLoggedIn, quickPlayScene, customGameScene, logInPageScene, signUpScene, playGameScene, scoreBoardPageScene, finalScoreBoardPageScene, saveFilesScene, deleteUserScene;
    public static String enteredUsername, enteredPass;
    public static String loggedUser, loggedUsersPass;
    public static boolean loggedIn = false;
    public static int numOfQuestions = 10;
    public static int numOfTeams = 2;
    public static List<Question> questions, rQuestions, themeQuestions;
    public static List<RadioButton> answers;
    public static List<Team> teams;
    public static int n;
    public static User user = new User();
    public static Admin admin = new Admin();
    public static int tCount = 1, qCount = 1;


    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        StartUpSavingFolders.startUpSavingFolders();
      window = primaryStage;
      new FrontPageScene();
      window.setTitle("Soundalicous");
      window.show();

      questions = new ArrayList<>();
      rQuestions = new ArrayList<>();
      themeQuestions = new ArrayList<>();
      answers = new ArrayList<>();
      teams = new ArrayList<>();
      n = 0;

     Questions.readQuestionsFromFile(questions, rQuestions);
  }
}
