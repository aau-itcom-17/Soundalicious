/**
 * Class that saves all the text and numbers used through out the program.
 */
public class Constants {

    //files
    public static String userDatabaseFilePath = "userDatabase.txt";
    public static String StyleSheetPath = "Theme.css";
    public static String userHistoryPath = "UserHistoryFiles";

    //dimensions, sizes, numbers
    public static int screenWidth = 400;
    public static int screenHeight = 700;
    public static int vBoxSpacing = 20;
    public static int numberQuestionCustomGame = 10;

    //message
    public static String messageGreetingWord = "Hi";

    //names
    public static String nameGame = "Soundalicious";
    public static String nameCustomGame = "Custom game";
    public static String nameQuickPlay = "Quickplay";
    public static String nameLogin = "Log in";
    public static String nameSignup = "Sign up";
    public static String nameLogout = "Log out";
    public static String nameDeleteYouUser = "Delete your user";
    public static String nameDeleteOtherUsers = "Delete other users";
    public static String nameUploadQuestion = "Upload a question";
    public static String nameTeam = "Team";
    public static String nameQuestion = "Question";
    public static String nameScoreboard = "Scoreboard";
    public static String nameLeaderboard = "Leaderboard";
    public static String nameExitGame = "Exit game";
    public static String namePoints = "Points";
    public static String nameGold = "Gold";
    public static String nameSilver = "Silver";
    public static String nameBronze = "Bronze";
    public static String nameHistory = "Game history";

    //admin
    public static String nameAdmin = "Admin";
    public static String adminUsername = "admin";
    public static String adminPass = "password";

    //texts
    public static String textIncrease = "+";
    public static String textDecrease = "-";
    public static String textPlaySound = "Play sound";
    public static String textAnsweringQuestion = "answering question";
    public static String textNext = "Next";
    public static String textSeeAnswers = "See answers";
    public static String textCorrectAnswer = "Correct answer";
    public static String textSelectedAnswer = "Selected answer";
    public static String textUsername = "Username:";
    public static String textPassword = "Password:";
    public static String textNewUsername = "New " + textUsername;
    public static String textRepeatPassword = "Repeat " + textPassword;
    public static String textCreateNewUser = "Create new user";
    public static String textBackToMain = "Go back to the front page";
    public static String textEnterUserForDeleting = "Enter username which you want to delete";
    public static String textDeleteUser = "Delete user";
    public static String textSaveTeam = "Save team";
    public static String textNameYourTeam = "Name your team";
    public static String textUserXDeleted = "was deleted";
    public static String textTeamXWonAndGot = "won the game and got";
    public static String textXAnswerRightOutOfX = "right answers out of";
    public static String textQuestions = "questions";
    public static String textLoggedOut = "Logged out";
    public static String textUserDeletedSelf = "User deleted himself/herself";
    public static String textNewUserCreated = "New user created";
    public static String textLoggedIn = "Logged in";
    public static String textSeeHistory = "See history";
    public static String textCancel = "Cancel";

    //button texts
    public static String goToNextText = "Go to next page";

    //warnings
    public static String warningConfirmAction = "Are you sure?";
    public static String warningConfirmActionExit = "Are you sure want to exit?";
    public static String warningEmptyFields = "You left some fields empty";
    public static String warningEmptyField = "Field left empty";
    public static String warningNameContainsSpecChars = "Name contains spec. chars";
    public static String warningPassContainsSpecChars = "Pass contains spec. chars";
    public static String warningUserExists = "Username already exists";
    public static String warningDoNotMatch = "Passwords do not match";
    public static String warningUserNotExists = "User does not exist";
    public static String warningWrongPassword = "Wrong password";
    public static String warningUserDeleted = "User deleted";
    //custom game
    public static int click = 1;
    public static int[] teamNums = new int[]{1, 2, 3, 4, 5};
    public static String[] teamNumNames = new String[]{"1 Team", "2 Teams", "3 Teams", "4 Teams", "5 Teams"};

    public static int[] questionNums = new int[]{5, 10, 15, 20, 25, 30};
    public static String[] questionNumNames = new String[]{"5 questions", "10 questions", "15 questions", "20 questions", "25 questions", "30 questions"};

    public static String[] themeNames = new String[]{"Theme1", "Theme2", "Theme3", "Theme4", "Theme5"};

    public static String startGameText = "Start game";
    public static String howToPlayText = "How to play";

    //quickplay
    public static String chooseTopicTitle = "Select a theme and you will get " + numberQuestionCustomGame + " questions about it";
    public static String topicText1 = "Celebrities";
    public static String topicText2 = "Movies";

    //how to play
    public static String textHowToPlay = "How to play?";
    public static String textRules = "Rules are really simple, \nso you probably will get them as soon as you start.\n\n " +
            "1. Just pick a number of teams \n(it can be both individuals or groups or just you).\n" +
            "2. Select a number of questions you want to answer. \nEvery team has to answer every question.\n" +
            "3. Start the game!\n" +
            "4. First team plays the sound, \nhas to pick their answer and submit.\n" +
            "5. Other teams get the same question.\n" +
            "6. When every team has answered, you'll see the correct \n answer, points and chosen answer by every team.\n" +
            "7. When all questions answered, \nyou'll see the final leaderboard.\n" +
            "8. Good luck!\n\n" +
            "If you want to get more option, just go back to main menu, \nlog in and select Custom game option.\n" +
            "If you don't have an account yet, \nin the main menu press Sign up.\n";

    public static String goToQuickPlayText = "Go back to Quick Play";
}
