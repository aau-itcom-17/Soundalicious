import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

/**
 * This class and scenes lets user to upload a question with a sound to the game.
 * It can be accessed only when user is logged in.
 * It includes text fields for the question, right and wrong answers, choice box with theme options, upload button for a sound, submit button and go back to main button.
 */

public class UploadQuestionScene extends FrontPageScene {

    private Text actionStatus;
    private Stage savedStage;
    private TextArea txtArea;
    private static final String titleTxt = "Save your sounds";
    private TextField questionText, correctAnswer, wronganswer1, wronganswer2, wronganswer3;
    private Label questionLabel, correctAnswerLabel, wrongAnswerLabel, label, themeLabel;
    private ChoiceBox<String> themesChoice;
    Question question = new Question();
    private HBox answersHBox;
    private Button btn1, btn2;
    private int number = 1000;
    private VBox saveFilesLayout;

    // Make a method that makes sure the ID is different each time you upload a question!!!!!!!!!!!!!
    public UploadQuestionScene() {

        actionStatus = new Text();
        actionStatus.setFont(Font.font("Calibri", FontWeight.NORMAL, 18));
        actionStatus.setFill(Color.FIREBRICK);


        //primaryStage.setTitle(titleTxt);

        questionLabel = new Label("Write questionText below: ");
        //Username input
        questionText = new TextField();
        questionLabel.setStyle("-fx-padding: -30px");
        questionText.setStyle("-fx-max-width: 300px; -fx-min-width: 300px; -fx-max-height: 30px; -fx-min-height: 30px; -fx-font-size: 10pt;");
        questionText.setOnKeyPressed((event) -> {
            question.setTextOfQuestion(questionText.getText());
        });

        correctAnswerLabel = new Label("Write the correct answer below: ");
        correctAnswer = new TextField();
        correctAnswerLabel.setStyle("-fx-padding: -30px");
        correctAnswer.setStyle("-fx-max-width: 300px; -fx-min-width: 300px; -fx-max-height: 30px; -fx-min-height: 30px; -fx-font-size: 10pt;");
        correctAnswer.setOnKeyPressed((event) -> {
            question.setCorrectAnswer(correctAnswer.getText());
        });


        wrongAnswerLabel = new Label("Write the wrong answers below: ");
        wrongAnswerLabel.setStyle("-fx-padding: -30px");
        wronganswer1 = new TextField();
        wronganswer1.setStyle("-fx-max-width: 100px; -fx-min-width: 100px; -fx-max-height: 30px; -fx-min-height: 30px; -fx-font-size: 10pt;");
        wronganswer1.setOnKeyPressed((event) -> {
            question.setwrongAnswers1(wronganswer1.getText());
        });
        wronganswer2 = new TextField();
        wronganswer2.setStyle("-fx-max-width: 100px; -fx-min-width: 100px; -fx-max-height: 30px; -fx-min-height: 30px; -fx-font-size: 10pt;");
        wronganswer2.setOnKeyPressed((event) -> {
            question.setwrongAnswers2(wronganswer2.getText());
        });
        wronganswer3 = new TextField();
        wronganswer3.setStyle("-fx-max-width: 100px; -fx-min-width: 100px; -fx-max-height: 30px; -fx-min-height: 30px; -fx-font-size: 10pt;");
        wronganswer3.setOnKeyPressed((event) -> {
            question.setwrongAnswers3(wronganswer3.getText());
        });

        answersHBox = new HBox();
        answersHBox.setAlignment(Pos.CENTER);
        answersHBox.getChildren().addAll(wronganswer1, wronganswer2, wronganswer3);

        // Window label
        label = new Label("Make your own questions");
        label.setStyle("-fx-padding: -30px");
        label.setTextFill(Color.BLACK);
        label.setFont(Font.font("Calibri", FontWeight.BOLD, 22));
        HBox labelHb = new HBox();
        labelHb.setAlignment(Pos.CENTER);
        labelHb.getChildren().add(label);

        // theme choosing
        themeLabel = new Label("Choose theme for your question");
        themeLabel.setStyle("-fx-padding: -30px");
        themesChoice = new <String>ChoiceBox(FXCollections.observableArrayList(Constants.themeNames[0], Constants.themeNames[1], Constants.themeNames[2], Constants.themeNames[3], Constants.themeNames[4]));


        // Button
        btn1 = new Button("Choose file");
        btn1.getStyleClass().add("button-menu");
        btn1.setOnAction(new SaveButtonListener());
        HBox buttonHb1 = new HBox(10);
        buttonHb1.setAlignment(Pos.CENTER);
        buttonHb1.getChildren().addAll(btn1);
        // Button 2
        System.out.println(questions.size());
        btn2 = new Button("Save question");
        btn2.getStyleClass().add("button-menu");
        btn2.setOnAction(new SaveQuestionListener());
        HBox buttonHb2 = new HBox(10);
        buttonHb2.setAlignment(Pos.CENTER);
        buttonHb2.getChildren().addAll(btn2);

        Button frontPageButton = new Button("Back to frontpage");
        frontPageButton.getStyleClass().add("button-menu");
        frontPageButton.setOnAction(e -> new FrontPageScene());
        HBox frontButton1 = new HBox(10);
        frontButton1.setAlignment(Pos.CENTER);
        frontButton1.getChildren().addAll(frontPageButton);


        // Status message text

        // Vbox
        saveFilesLayout = new VBox(30);
        saveFilesLayout.setPadding(new Insets(25, 25, 25, 25));
        saveFilesLayout.setAlignment(Pos.CENTER);
        saveFilesLayout.getChildren().addAll(questionLabel, questionText, correctAnswerLabel, correctAnswer, wrongAnswerLabel, answersHBox, themeLabel, themesChoice, buttonHb1, buttonHb2, frontButton1, actionStatus);

        // Scene
        saveFilesScene = new Scene(saveFilesLayout, Constants.screenWidth, Constants.screenHeight); // w x h
        saveFilesScene.getStylesheets().add(Constants.StyleSheetPath);
        window.setScene(saveFilesScene);


    }

    /**
     * It calls the writing to file method from the Questions class.
     */

    private class SaveQuestionListener implements EventHandler<ActionEvent> {

        public void handle(ActionEvent e) {
            if (question.getSoundFile() != null) {
                question.setId(Main.questions.size() + 2);
                question.setTheme(themesChoice.getValue());
                try {
                    question.writeToFile(question.getId(), question.getTheme(), question.getTextOfQuestion(), question.getSoundFile(), question.getCorrectAnswer(),
                            question.getwrongAnswers1(), question.getwrongAnswers2(), question.getwrongAnswers3());

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                Main.questions = new ArrayList<>();
                try {
                    Questions.readQuestionsFromFile(questions, rQuestions);
                } catch (ParserConfigurationException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (SAXException e1) {
                    e1.printStackTrace();
                }
                questionText.clear();
                correctAnswer.clear();
                wronganswer1.clear();
                wronganswer2.clear();
                wronganswer3.clear();
                question.setSoundFile(null);
                actionStatus.setText(null);
            } else {
                actionStatus.setText("You need to choose a file");
            }

        }
    }

    /**
     *
     */

    private class SaveButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            showSingleFileChooser();
        }
    }

    /**
     * Method copies the new file to the user's personal documents.
     */


    public static void CopyFile(File source, File dest) throws IOException {
        Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * Method handles the file upload, checks if file is correct and does not repeat already.
     */

    private void showSingleFileChooser() {

        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);


        //fileChooser.getExtensionFilters().addAll(new ExtensionFilter(".au", "*.au"));
        String sF = selectedFile.getName().substring(selectedFile.getName().lastIndexOf('.') + 1);
        System.out.println(sF);

        // getting the specific filename used to copy into
        String dF = selectedFile.getName().substring(selectedFile.getName().lastIndexOf('/') + 1);
        System.out.println(dF);

        // getting path of folder to save the files in.
        String homePath = System.getProperty("user.home");
        String soundFolderPath = homePath + File.separator + "p1" + File.separator + "Sounds";
        System.out.println("User folder path: " + soundFolderPath);
        File dest = new File(soundFolderPath + File.separator + dF);


        File source = new File(selectedFile.getPath());
        System.out.println("Source: " + source);
        System.out.println("Destination " + dest);

        long destSize = 0;
        if (dest.exists()) {
            destSize = FileUtils.sizeOf(dest);
            System.out.println(destSize);
        }
        long sourceFileSize = FileUtils.sizeOf(source);

        System.out.println(sourceFileSize);


        if (sF.equals("au") || sF.equals("wav")) {
            if (destSize != sourceFileSize) {
                if (!dest.equals(source)) {
                    fileChooser.setTitle(selectedFile.getName());
                    fileChooser.setInitialDirectory(dest);
                    fileChooser.setInitialFileName(fileChooser.getTitle());

                    if (selectedFile != null) {
                        try {
                            CopyFile(source, dest);
                            question.setSoundFile(source.getName());
                        } catch (IOException e) {

                            e.printStackTrace();
                            actionStatus.setText("An ERROR occurred while saving the file!" +
                                    selectedFile.toString());
                            return;
                        }
                        actionStatus.setText("File is saved to Sound folder");
                    } else {
                        actionStatus.setText("File save cancelled.");
                    }
                } else {
                    actionStatus.setText("Filename already exists");
                }

            } else {
                actionStatus.setText("File already exist...");

            }

        } else {
            actionStatus.setText("Filetype is not supported");
        }


    }


}
