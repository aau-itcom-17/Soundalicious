import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.xml.sax.SAXException;

import java.util.Collections;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class PlayGameScene extends FrontPageScene {

    private int i = 0;
    private int counter =1;
    RadioButton radioBut1, radioBut2, radioBut3, radioBut4;
    ToggleGroup question1;
    Label whoIsThis;
    Button playSound, nextQuestion, frontPageButton5;
    VBox playGameLayout;
    String teamOrQuestion = null;


    public PlayGameScene() throws IOException, SAXException, ParserConfigurationException {
        //Buttons


        radioBut2 = new RadioButton(rQuestions.get(n).getDummyAnswers1());
        radioBut3 = new RadioButton(rQuestions.get(n).getDummyAnswers2());
        radioBut4 = new RadioButton(rQuestions.get(n).getDummyAnswers3());
        radioBut1 = new RadioButton(rQuestions.get(n).getCorrectAnswer());
        question1 = new ToggleGroup();

        radioBut1.setToggleGroup(question1);
        radioBut2.setToggleGroup(question1);
        radioBut3.setToggleGroup(question1);
        radioBut4.setToggleGroup(question1);

        answers.add(radioBut1);
        answers.add(radioBut2);
        answers.add(radioBut3);
        answers.add(radioBut4);

        Collections.shuffle(answers);

        whoIsThis = new Label("Who is this?");
        whoIsThis.getStyleClass().add("label-who");
        //Buttons
        //Play Sound

        playSound = new Button ("Play Sound");
        playSound.setOnAction(new EventHandler<ActionEvent>()  {
            @Override
            public void handle(ActionEvent event) {
                Soundfiles.readingSound();
            }
        });

        //Next Question button

        if (teams.size() > 1){
            teamOrQuestion = "Team";
        } else{
            teamOrQuestion = "Question";
        }
        nextQuestion = new Button("Next " + teamOrQuestion);
        nextQuestion.setOnAction(f -> {

            if (counter == teams.size()){
                teamOrQuestion = "Question";
            }



            System.out.println("n " + n);
            Soundfiles.noSound();
            answers.clear();

            if (radioBut1.isSelected()) {
                teams.get(i).setPointScore(teams.get(i).getPointScore() + 1);
            } else {
                /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Wrong Answer");
                alert.showAndWait();
                */
            }
            i++;
            if (counter == teams.size()) {
                n++;
                i = 0;
                new ScoreboardPageScene();
                /*
                try {
                    new PlayGameScene();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }
                */


            }
            for (int j = 0; j < teams.size(); j++) {
                System.out.println(teams.get(j).getPointScore());
            }

            counter++;




            if (n == QuickPlayScene.numOfQuestions){
                window.setScene(frontPageScene);
                n = 0;
                rQuestions.clear();
                answers.clear();
                try {
                    Questions.getRandomQuestions(questions, rQuestions);
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            radioBut1.setSelected(false);
            radioBut2.setSelected(false);
            radioBut3.setSelected(false);
            radioBut4.setSelected(false);

        });




            //Button back to front on custom game page
        frontPageButton5 = new Button(Constants.goToMainText);
        frontPageButton5.setOnAction(e -> window.setScene(frontPageScene));

        //Layout for playing the game
        playGameLayout = new VBox(20);
        playGameLayout.setAlignment(Pos.CENTER);
        playGameLayout.getChildren().addAll(whoIsThis, playSound, answers.get(0), answers.get(1), answers.get(2), answers.get(3), nextQuestion, frontPageButton5);
        playGameScene = new Scene(playGameLayout, 400, 600);

        playGameScene.getStylesheets().add("Theme.css");
        window.setScene(playGameScene);


    }

}
