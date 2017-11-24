# P1

## Scene classes:

### Main.java
The main function that creates window.

### FrontPageScene.java 
Creates frontPageScene that can go to quickPlayScene, customGameScene, logInScene or signUpScene. Also, an option to delete your user and log out, then application goes to the first screen of frontPageScene.

### QuickPlayScene.java
Creates quickPlayScene with options to choose teams and questions number. Can go to to the playGameScene or back to the frontPageScene.

### CustomGameScene.java
Firstly redirects to logInScene, after login you can select topics. Before starting game goes to the quickPlayScene, howToPlayScene or can go back to the frontPageScene.

### LogInScene.java
Logs you in if the data is correct or can go back to the frontPageScene.

### SignUpScene.java
Can create a new user or go back to the frontPageScene.

### PlayGameScene.java
Start of the game.

### HowToPlayScene.java
Scene that goes from the quickPlayScene to show the rules. Goes back to the quickPlayScene.

## Other files:

### Constants.java
All not dynamic strings should be here and used through out other classes.

### Theme.css
Stylesheet

### Soundfiles.java
Class that deals with the sound playback.
