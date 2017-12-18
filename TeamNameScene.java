import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Class runs after the quick play scene to add team names.
 * After the first team saves its name, screen repeats until all teams have chosen team name.
 * It includes title with team number, text field for name and button for confirming.
 * After this, app proceeds to the play game scene.
 */

public class TeamNameScene extends QuickPlayScene {

    private Label labelTeamNumber;
    private TextField textfieldNewTeamName;
    private Button buttonSaveTeam;
    private VBox layoutTeamName;
    private int counterOfTeams = 1;

    public TeamNameScene() {

        labelTeamNumber = new Label(Constants.textNameYourTeam + 1 + ":");
        layoutTeamName = new VBox(Constants.vBoxSpacing);
        textfieldNewTeamName = new TextField();
        textfieldNewTeamName.requestFocus();
        buttonSaveTeam = new Button(Constants.textSaveTeam);
        layoutTeamName.getChildren().addAll(labelTeamNumber, textfieldNewTeamName, buttonSaveTeam);
        teamNameScene = new Scene(layoutTeamName, Constants.screenWidth, Constants.screenHeight);

        labelTeamNumber.getStyleClass().add("label-headline");
        layoutTeamName.setAlignment(Pos.CENTER);
        buttonSaveTeam.getStyleClass().add("button-continue");
        teamNameScene.getStylesheets().add(Constants.StyleSheetPath);


        for (int i = 1; i <= numOfTeams; i++) {

            textfieldNewTeamName.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    nextTeamName();
                }
            });

            buttonSaveTeam.setOnAction(e -> nextTeamName());
        }

        window.setScene(teamNameScene);
    }

    public void nextTeamName() {
        Team teamClassTemp = new Team(null, 0, 0);

        if (!textfieldNewTeamName.getText().equals("")) {
            buttonSaveTeam.setStyle(null);
            buttonSaveTeam.setText(Constants.textSaveTeam);

            counterOfTeams++;

            labelTeamNumber.setText(Constants.textNameYourTeam + " " + counterOfTeams + ":");

            teamClassTemp.setID(teams.size() + 1);
            teamClassTemp.setTeamName(textfieldNewTeamName.getText());
            teams.add(teamClassTemp);
            textfieldNewTeamName.clear();

            if (QuickPlayScene.numOfTeams == teams.size()) {
                try {
                    new PlayGameScene();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (SAXException e1) {
                    e1.printStackTrace();
                } catch (ParserConfigurationException e1) {
                    e1.printStackTrace();
                }
            }

        } else {
            buttonSaveTeam.setStyle("-fx-background-color: red");
            buttonSaveTeam.setText(Constants.warningEmptyField);
        }
    }
}