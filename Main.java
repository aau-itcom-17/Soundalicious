import javafx.application.*;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;


public class Main extends Application {


    Stage window;
    Scene frontpageScene, quickplayScene, customgameScene, loginpageScene, playGameScene, playGameScene2, playGameScene3;

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
        choiceBox2.getItems().addAll("chose a value", "10 Questions", "20 Questions", "30 Questions");
        //Set default value
        choiceBox2.setValue("chose a value");


        //Quick Play play button button
        Button startGame = new Button("Start Game");

        //How to play button button
        Button buttonHowToPlay = new Button("How To Play");

        //Button back to front on Quick play page
        Button frontpageButton1 = new Button("Go back to front page");
        frontpageButton1.setOnAction(e -> window.setScene(frontpageScene));

        //Layout quickplay
        VBox quickplayLayout = new VBox(20);
        quickplayLayout.setAlignment(Pos.CENTER);
        quickplayLayout.getChildren().addAll(labelQuick, labelChoiceBox, choiceBox, labelChoiceBox2, choiceBox2, startGame, buttonHowToPlay, frontpageButton1);
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

        Button loginButton = new Button("Log In");
        Button signupButton = new Button("Sign Up");

        //Button back to front on login page
        Button frontpageButton3 = new Button("Go back to front page");
        frontpageButton3.setOnAction(e -> window.setScene(frontpageScene));

        //Layout custom game
        VBox loginpageLayout = new VBox(20);
        loginpageLayout.setAlignment(Pos.CENTER);
        loginpageLayout.getChildren().addAll(labelLogin, usernameLabel, usernameInput, passwordLabel, passwordInput, loginButton, signupButton, frontpageButton3);
        loginpageScene = new Scene(loginpageLayout, 400, 600);

        //ALL THIS IS PLAYING THE GAME:



        //Open scene for the game

            choiceBox2.setOnAction(e -> {
                Soundfiles.getChoice(choiceBox2);
                if (Soundfiles.getChoice(choiceBox2).equals("10 Questions"))
                {
                    startGame.setOnAction (f -> window.setScene(playGameScene));
                    //Label
                    Label whoIsThis = new Label("Who is this?");

                    //Buttons
                    //Play Sound
                    Button playSound = new Button("Play Sound");
                    playSound.setOnAction(f -> Soundfiles.kanyeSound());

                    //Buttons
                    RadioButton kanyeBut = new RadioButton("Kanye West");
                    RadioButton beyonceBut = new RadioButton("Beyonce");
                    RadioButton jayzBut = new RadioButton("Jay-Z");
                    RadioButton eminemBut = new RadioButton("Eminem");

                    ToggleGroup question1 = new ToggleGroup();

                    kanyeBut.setToggleGroup(question1);
                    beyonceBut.setToggleGroup(question1);
                    jayzBut.setToggleGroup(question1);
                    eminemBut.setToggleGroup(question1);

                    //Button back to front on custom game page
                    Button frontpageButton4 = new Button("Go back to front page");
                    frontpageButton4.setOnAction(f -> window.setScene(frontpageScene));

                    //Next Question button
                    Button nextQuestion = new Button("Next Question");
                    nextQuestion.setOnAction(f -> {
                        if (kanyeBut.isSelected())
                        {
                            Soundfiles.countPoints();
                        }
                        //if (beyonceBut.isSelected() || jayzBut.isSelected() || eminemBut.isSelected())
                        else
                        {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information Dialog");
                            alert.setHeaderText(null);
                            alert.setContentText("Wrong answer!");
                            alert.showAndWait();
                        }
                        window.setScene(playGameScene2);
                    });

                    //Layout for playing the game
                    VBox playGameLayout = new VBox(20);
                    playGameLayout.setAlignment(Pos.CENTER);
                    playGameLayout.getChildren().addAll(whoIsThis, playSound, beyonceBut, kanyeBut, jayzBut, eminemBut, nextQuestion, frontpageButton4);
                    playGameScene = new Scene(playGameLayout, 400, 600);


                    //Layout for question set nr. 2

                    //Label
                    Label whoIsThis2 = new Label("Who made this song?");
                    //Buttons
                    //Play Sound
                    Button playSound2 = new Button ("Play Sound");
                    playSound2.setOnAction(f -> Soundfiles.darudeSound());

                    //Buttons
                    RadioButton newName2 = new RadioButton("Darude");
                    RadioButton newName = new RadioButton("Eiffel 65");
                    RadioButton newName1 = new RadioButton("Gigi D'Agostino");
                    RadioButton newName3 = new RadioButton("Sash!");

                    ToggleGroup question2 = new ToggleGroup();

                    newName.setToggleGroup(question2);
                    newName1.setToggleGroup(question2);
                    newName2.setToggleGroup(question2);
                    newName3.setToggleGroup(question2);

                    //Button back to front on custom game page
                    Button frontpageButton5 = new Button("Go back to front page");
                    frontpageButton5.setOnAction(f -> window.setScene(frontpageScene));

                    //Next Question button
                    Button nextQuestion2 = new Button("Next Question");
                    nextQuestion2.setOnAction(f -> {
                        if (newName2.isSelected())
                        {
                            Soundfiles.countPoints();
                        }
                        //if (newName.isSelected() || newName1.isSelected() || newName3.isSelected())
                        else
                        {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information Dialog");
                            alert.setHeaderText(null);
                            alert.setContentText("Wrong answer!");
                            alert.showAndWait();
                        }
                        window.setScene(playGameScene3);
                    });

                    VBox playGameLayout2 = new VBox(20);
                    playGameLayout2.setAlignment(Pos.CENTER);
                    playGameLayout2.getChildren().addAll(whoIsThis2,playSound2,newName,newName1,newName2,newName3, nextQuestion2, frontpageButton5); //Write the questions in here like above.
                    playGameScene2 = new Scene(playGameLayout2, 400, 600);


                    //Layout for question set nr. 3

                    //Label
                    Label whoIsThis3 = new Label("Who made this song?");
                    //Buttons
                    //Play Sound
                    Button playSound3 = new Button ("Play Sound");
                    playSound3.setOnAction(f -> Soundfiles.bMarsSound());

                    //Buttons
                    RadioButton brunoBut = new RadioButton("Bruno Mars");
                    RadioButton queenBut = new RadioButton("Queen");
                    RadioButton michaelBut = new RadioButton("Michael Jackson");
                    RadioButton otherBut = new RadioButton("Other");

                    ToggleGroup question3 = new ToggleGroup();

                    brunoBut.setToggleGroup(question3);
                    queenBut.setToggleGroup(question3);
                    michaelBut.setToggleGroup(question3);
                    otherBut.setToggleGroup(question3);

                    //Button back to front on custom game page
                    Button frontpageButton6 = new Button("Go back to front page");
                    frontpageButton6.setOnAction(f -> window.setScene(frontpageScene));

                    //Next Question button
                    Button nextQuestion3 = new Button("Next Question");
                    nextQuestion3.setOnAction(f -> {
                        if (brunoBut.isSelected())
                        {
                            Soundfiles.countPoints();
                        }
                        //if (queenBut.isSelected() || michaelBut.isSelected() || otherBut.isSelected())
                        else
                        {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information Dialog");
                            alert.setHeaderText(null);
                            alert.setContentText("Wrong answer!");
                            alert.showAndWait();
                        }

                    });

                    //Layout for playing the game
                    VBox playGameLayout3 = new VBox(20);
                    playGameLayout3.setAlignment(Pos.CENTER);
                    playGameLayout3.getChildren().addAll(whoIsThis3, playSound3, queenBut, michaelBut, brunoBut, otherBut, nextQuestion3, frontpageButton6);
                    playGameScene3 = new Scene(playGameLayout3, 400, 600);
                }

                else if (Soundfiles.getChoice(choiceBox2).equals("20 Questions"))
                {
                    startGame.setOnAction(f -> window.setScene(playGameScene));
                    //Label
                    Label whoIsThis = new Label("Who is this test?");

                    //Buttons
                    //Play Sound
                    Button playSound = new Button("Play Sound");
                    playSound.setOnAction(f -> Soundfiles.kanyeSound());

                    //Correct Answer
                    Button kanyeBut = new Button("Kanye West");
                    //Count Points
                    kanyeBut.setOnAction(f -> Soundfiles.countPoints());
                    Button beyonceBut = new Button("Beyonce");
                    Button jayzBut = new Button("Jay-Z");
                    Button eminemBut = new Button("Eminem");

                    //Button back to front on custom game page
                    Button frontpageButton4 = new Button("Go back to front page");
                    frontpageButton4.setOnAction(f -> window.setScene(frontpageScene));


                    //Layout for playing the game
                    VBox playGameLayout = new VBox(20);
                    playGameLayout.setAlignment(Pos.CENTER);
                    playGameLayout.getChildren().addAll(whoIsThis, playSound, beyonceBut, kanyeBut, jayzBut, eminemBut, frontpageButton4);
                    playGameScene = new Scene(playGameLayout, 400, 600);
                }

                else if (Soundfiles.getChoice(choiceBox2).equals("30 Questions"))
                {
                    startGame.setOnAction(f -> window.setScene(playGameScene));
                    //Label
                    Label whoIsThis = new Label("Who is this test2?");

                    //Buttons
                    //Play Sound
                    Button playSound = new Button("Play Sound");
                    playSound.setOnAction(f -> Soundfiles.kanyeSound());

                    //Correct Answer
                    Button kanyeBut = new Button("Kanye West");
                    //Count Points
                    kanyeBut.setOnAction(f -> Soundfiles.countPoints());
                    Button beyonceBut = new Button("Beyonce");
                    Button jayzBut = new Button("Jay-Z");
                    Button eminemBut = new Button("Eminem");

                    //Button back to front on custom game page
                    Button frontpageButton4 = new Button("Go back to front page");
                    frontpageButton4.setOnAction(f -> window.setScene(frontpageScene));


                    //Layout for playing the game
                    VBox playGameLayout = new VBox(20);
                    playGameLayout.setAlignment(Pos.CENTER);
                    playGameLayout.getChildren().addAll(whoIsThis, playSound, beyonceBut, kanyeBut, jayzBut, eminemBut, frontpageButton4);
                    playGameScene = new Scene(playGameLayout, 400, 600);
                }
                else
                {
                    startGame.setOnAction(f -> window.setScene(quickplayScene));

                }
            });










        //THIS MAKES THE WINDOW OPEN:
        window.setScene(frontpageScene);
        window.setTitle("Soundalicous");
        window.show();


    }


}





