import com.sun.tools.internal.jxc.ap.Const;
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
    private TextField questionText, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3;
    private Label questionLabel, correctAnswerLabel, wrongAnswerLabel, label, themeLabel;
    private ChoiceBox<String> themesChoice;
    Question question = new Question();
    private HBox answersHBox;
    private Button btn1, btn2;
    private VBox saveFilesLayout;

    public UploadQuestionScene() {

        actionStatus = new Text();
        questionLabel = new Label(Constants.textWriteQuestion + ": ");
        questionText = new TextField();
        correctAnswerLabel = new Label(Constants.textWriteCorrectAnswer + ": ");
        correctAnswer = new TextField();
        wrongAnswerLabel = new Label(Constants.textWriteWrongAnswers + ": ");
        wrongAnswer1 = new TextField();
        wrongAnswer2 = new TextField();
        wrongAnswer3 = new TextField();
        answersHBox = new HBox();
        label = new Label(Constants.textMakeYourQuestion);
        HBox labelHb = new HBox();
        themeLabel = new Label(Constants.textChooseTheme);
        themesChoice = new <String>ChoiceBox(FXCollections.observableArrayList(Constants.topicText1, Constants.topicText2));
        btn1 = new Button(Constants.textChooseFile);
        HBox buttonHb1 = new HBox(10);
        btn2 = new Button(Constants.textSaveQuestion);
        Button buttonBackToMain = new Button(Constants.textBackToMain);
        HBox buttonHb2 = new HBox(10);

        actionStatus.setFont(Font.font("Calibri", FontWeight.NORMAL, 18));
        actionStatus.setFill(Color.FIREBRICK);
        questionLabel.setStyle("-fx-padding: -30px");
        questionText.setStyle("-fx-max-width: 300px; -fx-min-width: 300px; -fx-max-height: 30px; -fx-min-height: 30px; -fx-font-size: 10pt;");
        correctAnswerLabel.setStyle("-fx-padding: -30px");
        correctAnswer.setStyle("-fx-max-width: 300px; -fx-min-width: 300px; -fx-max-height: 30px; -fx-min-height: 30px; -fx-font-size: 10pt;");
        wrongAnswerLabel.setStyle("-fx-padding: -30px");
        wrongAnswer1.setStyle("-fx-max-width: 100px; -fx-min-width: 100px; -fx-max-height: 30px; -fx-min-height: 30px; -fx-font-size: 10pt;");
        wrongAnswer2.setStyle("-fx-max-width: 100px; -fx-min-width: 100px; -fx-max-height: 30px; -fx-min-height: 30px; -fx-font-size: 10pt;");
        wrongAnswer3.setStyle("-fx-max-width: 100px; -fx-min-width: 100px; -fx-max-height: 30px; -fx-min-height: 30px; -fx-font-size: 10pt;");
        answersHBox.setAlignment(Pos.CENTER);
        label.setStyle("-fx-padding: -30px");
        label.setTextFill(Color.BLACK);
        label.setFont(Font.font("Calibri", FontWeight.BOLD, 22));
        themeLabel.setStyle("-fx-padding: -30px");
        btn1.getStyleClass().add("button-menu");
        btn2.getStyleClass().add("button-menu");
        buttonBackToMain.getStyleClass().add("button-menu");
        labelHb.setAlignment(Pos.CENTER);
        buttonHb1.setAlignment(Pos.CENTER);
        buttonHb2.setAlignment(Pos.CENTER);

        labelHb.getChildren().add(label);
        buttonHb1.getChildren().addAll(btn1);
        buttonHb2.getChildren().addAll(btn2);

        answersHBox.getChildren().addAll(wrongAnswer1, wrongAnswer2, wrongAnswer3);

        questionText.setOnKeyPressed((event) -> {
            question.setTextOfQuestion(questionText.getText());
        });

       correctAnswer.setOnKeyPressed((event) -> {
            question.setCorrectAnswer(correctAnswer.getText());
        });

        wrongAnswer1.setOnKeyPressed((event) -> {
            question.setwrongAnswers1(wrongAnswer1.getText());
        });

        wrongAnswer2.setOnKeyPressed((event) -> {
            question.setwrongAnswers2(wrongAnswer2.getText());
        });
        wrongAnswer3.setOnKeyPressed((event) -> {
            question.setwrongAnswers3(wrongAnswer3.getText());
        });

        btn1.setOnAction(new SaveButtonListener());

        btn2.setOnAction(new SaveQuestionListener());

        buttonBackToMain.setOnAction(e -> new FrontPageScene());
        HBox frontButton1 = new HBox(10);
        frontButton1.setAlignment(Pos.CENTER);
        frontButton1.getChildren().addAll(buttonBackToMain);

        saveFilesLayout = new VBox(30);
        saveFilesLayout.setPadding(new Insets(25, 25, 25, 25));
        saveFilesLayout.setAlignment(Pos.CENTER);
        saveFilesLayout.getChildren().addAll(questionLabel, questionText, correctAnswerLabel, correctAnswer, wrongAnswerLabel, answersHBox, themeLabel, themesChoice, buttonHb1, buttonHb2, frontButton1, actionStatus);

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
                wrongAnswer1.clear();
                wrongAnswer2.clear();
                wrongAnswer3.clear();
                question.setSoundFile(null);
                actionStatus.setText(null);
            } else {
                actionStatus.setText(Constants.warningChooseFile);
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
        File dest = new File(soundFolderPath + File.separator + dF);

        File source = new File(selectedFile.getPath());

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
                            actionStatus.setText(Constants.warningErrorSaving +
                                    selectedFile.toString());
                            return;
                        }
                        actionStatus.setText(Constants.warningFileSaved);
                    } else {
                        actionStatus.setText(Constants.warningFileSavedCanceled);
                    }
                } else {
                    actionStatus.setText(Constants.warningFileNameExists);
                }

            } else {
                actionStatus.setText(Constants.warningFileExists);

            }

        } else {
            actionStatus.setText(Constants.warningTypeNotSupported);
        }


    }


}
