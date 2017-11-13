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
import java.io.IOException;


public class Main extends Application {


    Stage window;
    Scene frontpageScene, quickplayScene, customgameScene, loginpageScene, playGameScene;
    String enteredUsername, enteredPass;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;


        //ALL THIS IS FRONT PAGE:

        //Label front page
        Label labelFront = new Label("Soundalicious");

        //Quick play button -> Goes to Quickplay page
        Button quickplayButton = new Button("Quickplay");
        quickplayButton.setOnAction(e -> window.setScene(quickplayScene));

        //Custom game button -> Goes to custom game page
        Button customgameButton = new Button("Custom Game");
        customgameButton.setOnAction(e -> window.setScene(customgameScene));

        //Login page button -> Goes to login page
        Button loginpageButton = new Button("Log In/Sign Up");
        loginpageButton.setOnAction(e -> window.setScene(loginpageScene));

        //Layout Front Page
        VBox layoutFrontpage = new VBox(20);
        layoutFrontpage.setAlignment(Pos.CENTER);
        layoutFrontpage.getChildren().addAll(labelFront, quickplayButton, customgameButton, loginpageButton);
        frontpageScene = new Scene(layoutFrontpage, 400, 600);


        //ALL THIS IS QUICK PLAY PAGE:

        //Label Quick play
        Label labelQuick = new Label("Soundalicious");

        //Amount of teams in a choice box
        Label labelChoiceBox = new Label("Choose amount of teams:");
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("1 Team", "2 Teams", "3 Teams", "4 Teams", "5 Teams");
        //Set default value
        choiceBox.setValue("1 Team");

        //Amount of questions in a choice box
        Label labelChoiceBox2 = new Label("Choose amount of questions:");
        ChoiceBox<String> choiceBox2 = new ChoiceBox<>();
        choiceBox2.getItems().addAll("10 Questions", "20 Questions", "30 Questions");
        //Set default value
        choiceBox2.setValue("10 Questions");

        //Quick Play play button button
        Button quickplayPlay = new Button("Start Game");

        //Open scene for the game
        quickplayPlay.setOnAction(e -> window.setScene(playGameScene));

        //How to play button button
        Button buttonHowToPlay = new Button("How To Play");

        //Button back to front on Quick play page
        Button frontpageButton1 = new Button("Go back to front page");
        frontpageButton1.setOnAction(e -> window.setScene(frontpageScene));

        //Layout quickplay
        VBox quickplayLayout = new VBox(20);
        quickplayLayout.setAlignment(Pos.CENTER);
        quickplayLayout.getChildren().addAll(labelQuick, labelChoiceBox, choiceBox, labelChoiceBox2, choiceBox2, quickplayPlay, buttonHowToPlay, frontpageButton1);
        quickplayScene = new Scene(quickplayLayout, 500, 500);


        //ALL THIS IS CUSTOM GAME PAGE:

        //Label Custom page
        Label labelCustom = new Label("Soundalicious");

        //Label choose cards
        Label labelChooseQuestions = new Label("Choose which questions you want to play with");

        //Checkboxes for choosing cards
        CheckBox box1 = new CheckBox("Musicians");
        CheckBox box2 = new CheckBox("Actors");
        CheckBox box3 = new CheckBox("Athletes");

        //Custom game next button
        Button buttonCustomGameNext = new Button("Next Page");
        buttonCustomGameNext.setOnAction(e -> window.setScene(quickplayScene));

        //Button back to front on custom game page
        Button frontpageButton2 = new Button("Go back to front page");
        frontpageButton2.setOnAction(e -> window.setScene(frontpageScene));

        //Layout custom game
        VBox customgameLayout = new VBox(20);
        customgameLayout.setAlignment(Pos.CENTER);
        customgameLayout.getChildren().addAll(labelCustom, labelChooseQuestions, box1, box2, box3, buttonCustomGameNext, frontpageButton2);
        customgameScene = new Scene(customgameLayout, 400, 600);


        //ALL THIS IS LOGIN PAGE:

        //Label login page
        Label labelLogin = new Label("Soundalicious");

        //Username label
        Label usernameLabel = new Label("Username:");

        //Username input
        TextField usernameInput = new TextField();

        //Password label
        Label passwordLabel = new Label("Password:");

        //Password input
        TextField passwordInput = new TextField();

        Label loginError = new Label("Password is not correct. Try again.");
        loginError.setVisible(false);
        TextField passwordInput = new TextField();

        Button loginButton = new Button("Log In");
        loginButton.setOnAction(new EventHandler<ActionEvent>(){
          @Override
          public void handle(ActionEvent event) {
            enteredUsername = usernameInput.getText();
            enteredPass = passwordInput.getText();
            if (LoginTest.login(enteredUsername, enteredPass)){
              loginError = 
            }
            else {
              passwordInput.setText("");
              loginError.setVisible(true);
            }
          }
        });        Button signupButton = new Button("Sign Up");

        //Button back to front on login page
        Button frontpageButton3 = new Button("Go back to front page");
        frontpageButton3.setOnAction(e -> window.setScene(frontpageScene));

        //Layout custom game
        VBox loginpageLayout = new VBox(20);
        loginpageLayout.setAlignment(Pos.CENTER);
        loginpageLayout.getChildren().addAll(labelLogin, usernameLabel, usernameInput, passwordLabel,  passwordInput, loginError, loginButton, signupButton, frontpageButton3);
        loginpageScene = new Scene(loginpageLayout, 400, 600);


        //ALL THIS IS PLAYING THE GAME:

        //Label
        Label whoIsThis = new Label("Who is this?");
        //Buttons
        //Play Sound
        Button playSound = new Button ("Play Sound");
        playSound.setOnAction(new EventHandler<ActionEvent>()  {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Soundfiles.kanyeSound();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Button beyonceBut = new Button("Beyonce");
        Button kanyeBut = new Button("Kanye West");
        Button jayzBut = new Button("Jay-Z");
        Button eminemBut = new Button("Eminem");

        //Button back to front on custom game page
        Button frontpageButton4 = new Button("Go back to front page");
        frontpageButton4.setOnAction(e -> window.setScene(frontpageScene));

        //Layout for playing the game
        VBox playGameLayout = new VBox(20);
        playGameLayout.setAlignment(Pos.CENTER);
        playGameLayout.getChildren().addAll(whoIsThis, playSound, beyonceBut, kanyeBut, jayzBut, eminemBut, frontpageButton4);
        playGameScene = new Scene(playGameLayout, 400, 600);



        //THIS MAKES THE WINDOW OPEN:
        window.setScene(frontpageScene);
        window.setTitle("Soundalicous");
        window.show();


    }
}
