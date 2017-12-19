# Soundalicious 

![alt text](http://pichoster.net/images/2017/12/19/e10c840d9a7b14d727e47d32650cbe05.png)

It is a social game that capitalises on the apparent trend of pop-culture on the internet. We have done this by making a quiz, where the object is to hear a sound and then guess where this sound comes from. Whether it is a celebrity, from a movie or a song. There is an option to upload your own questions with sounds, which makes Souncalicious also a sound based questions quiz platform. 

It is based on Java FX with Apache Commons IO 2.6. Before running program you need to install it. It is included in the folder "Plugin for StartUpSavingFolder", also can be downloaded  from https://commons.apache.org/proper/commons-io/index.html .

How to add plugin with IntelliJ?
1. Extract the file "commons-io-2.6-bin.tar.gz" from the archive in "Plugin for StartUpSavingFolder"
2. Import the project to IntelliJ
2. Select: File > Project Structure
3. In the opened window select Libraries and press plus ("+") icon in the left section and select the extracted file
4. In the same window ("Project Structure") select Modules 
5. In the section on the right side choose Dependencies tab and put a tick next to "commons-io-2"
6. Ready to run the program

## Scene classes:

#### Main.class
The main function that creates window.
#### FrontPageScene.class 
Creates frontPageScene that can go to quickPlayScene, customGameScene, logInScene or signUpScene. Also, an option to delete your user and log out, then application goes to the first screen of frontPageScene.
#### QuickPlayScene.class
Creates quickPlayScene with options to choose teams and questions number. Can go to to the playGameScene or back to the frontPageScene.
#### CustomGameScene.class
Firstly redirects to logInScene, after login you can select topics. Before starting game goes to the quickPlayScene, gamePlayScene or can go back to the frontPageScene.
#### LogInScene.class
Logs you in if the data is correct or can go back to the frontPageScene.
#### SignUpScene.class
Can create a new user or go back to the frontPageScene.
#### PlayGameScene.class
Start of the game.
#### GameRules.class
Scene that goes from the quickPlayScene to show the rules. Goes back to the quickPlayScene.
#### DeleteUserScene.class 
Admin can delete any user by entering username in text field
#### ScoreboardPageScene.class
Scoreboard that appears after every answered question
#### FinalScoreboardPageScene.class
Scoreboard that appears after the final question
#### TeamNameScene.class
Scene that follows quickPlayScene, it contains textfield for team to enter their name
#### UploadQuestionScene.class
Scene for uploading sound and question to the game

### Other files:

#### Constants.class
All not dynamic strings should be here and used through out other classes.
#### Theme.css
Stylesheet
#### User.class
User constructor
#### Admin.class
User extension class
#### PointSystem
Deals with keeping up with the points of the teams
#### Team.class
Team constructor (one player of the game)
#### Soundfiles.class
Class that deals with the sound playback.
#### text.txt
Text file for saving usernames and passwords
#### StartUpSavingFolders.class
Called in the Main.java, moves files to user's directories.
#### CopyFolder.class
Used in StartUpSavingFolders.java
#### SaveFiles.class
Class that deals with uploading sounds to the game. Not implemented in actual game yet.
#### Questions.xml 
Database for questions
#### Questions.class
Class that deals with reading/saving questions from file, should create a random list that can be used in game.
#### Question.class
Constructor of class question, used in Questions.java
#### Sounds folder
All sounds are here
#### Plugin for StartUpSavingFolder folder
Plugin that you need to install
