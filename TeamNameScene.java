import com.sun.xml.internal.bind.v2.model.annotation.Quick;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeamNameScene extends QuickPlayScene {

    Label labelSelectTeamName, labelHeadline, teamNumber, teamNameError;
    TextField selectYourTeamName;
    Button saveTeams;
    VBox teamNameSceneLayout;
    int teamNr = 1;


    public TeamNameScene() {

        labelHeadline = new Label(Constants.gameName);
        labelSelectTeamName = new Label("Customize your team name");
        teamNumber = new Label("Team " + 1);

        teamNameError = new Label("Please enter a team name.");
        teamNameError.setStyle("-fx-text-fill: red");
        teamNameError.setVisible(false);

        selectYourTeamName = new TextField();
        selectYourTeamName.setOnKeyPressed((event) -> {

        });


        for (int i = 1; i <= QuickPlayScene.numOfTeams; i++) {

            saveTeams = new Button("Save Team");
            saveTeams.setOnAction(e -> {
                Team teamClassTemp = new Team(null, 0, 0);


                    //checks if user type anything at all.
                    if (!selectYourTeamName.getText().equals("")){
                        teamNameError.setVisible(false);
                        //changes team number for player.
                        teamNr++;
                        teamNumber.setText("Team " + teamNr);



                        teamClassTemp.setID(teams.size() + 1);
                        teamClassTemp.setTeamName(selectYourTeamName.getText());
                        teams.add(teamClassTemp);
                        selectYourTeamName.clear();


                        System.out.println("number of teams chosen " + QuickPlayScene.numOfTeams);
                        System.out.println(teams.size());
                        System.out.println(QuickPlayScene.numOfTeams);

                    if (QuickPlayScene.numOfTeams == teams.size()) {
                        // method that show the user number of teams chosen in the quickplayScene.
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

            }else{
                teamNameError.setVisible(true);
            }
            });

        }


        teamNameSceneLayout = new VBox(20);
        teamNameSceneLayout.setAlignment(Pos.CENTER);
        teamNameSceneLayout.getChildren().addAll(labelHeadline, labelSelectTeamName, teamNumber, selectYourTeamName, saveTeams, teamNameError);
        logInPageScene = new Scene(teamNameSceneLayout, 400, 600);

        logInPageScene.getStylesheets().add("Theme.css");
        window.setScene(logInPageScene);


    }

}



