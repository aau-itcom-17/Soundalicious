import javafx.application.Application;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;

public class SaveFiles
        extends Main {

    private Text actionStatus;
    private Stage savedStage;
    private TextArea txtArea;
    private static final String titleTxt = "Save your sounds";
    private TextField questionText, correctAnswer, wronganswer1, wronganswer2, wronganswer3;
    private Label questionLabel, correctAnswerLabel, wrongAnswerLabel, label, soundFile;
    Question question = new Question();
    private Button btn1, btn2;
    private int number = 1000;
    private VBox saveFilesLayout;

    // Make a method that makes sure the ID is different each time you upload a question!!!!!!!!!!!!!
    public SaveFiles() {

        actionStatus = new Text();
        actionStatus.setFont(Font.font("Calibri", FontWeight.NORMAL, 18));
        actionStatus.setFill(Color.FIREBRICK);


        //primaryStage.setTitle(titleTxt);

        questionLabel = new Label("Write questionText below: ");
        //Username input
        questionText = new TextField();
        questionText.setOnKeyPressed((event) -> {
            question.setTextOfQuestion(questionText.getText());
        });

        correctAnswerLabel = new Label("Write the correct answer below: ");
        correctAnswer = new TextField();
        correctAnswer.setOnKeyPressed((event) -> {
            question.setCorrectAnswer(correctAnswer.getText());
        });

        wrongAnswerLabel = new Label("Write the wrong answers below: ");
        wronganswer1 = new TextField();
        wronganswer1.setOnKeyPressed((event) -> {
            question.setDummyAnswers1(wronganswer1.getText());
        });
        wronganswer2 = new TextField();
        wronganswer2.setOnKeyPressed((event) -> {
            question.setDummyAnswers2(wronganswer2.getText());
        });
        wronganswer3 = new TextField();
        wronganswer3.setOnKeyPressed((event) -> {
            question.setDummyAnswers3(wronganswer3.getText());
        });


/*
        // The questionText for the sound
        Label Question = new Label("Question:");
        Question.setTextFill(Color.BLACK);
        Question.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        TextField textField = new TextField();
        HBox questionHb = new HBox(10);
        questionHb.getChildren().addAll(Question, textField);
        */
        // Window label
        label = new Label("Make your own questions");
        label.setTextFill(Color.BLACK);
        label.setFont(Font.font("Calibri", FontWeight.BOLD, 22));
        HBox labelHb = new HBox();
        labelHb.setAlignment(Pos.CENTER);
        labelHb.getChildren().add(label);

        /* Just a label
        soundFile = new Label("Upload sound (only .au and .wav files supported)");
        soundFile.setTextFill(Color.BLACK);
        soundFile.setFont(Font.font("Calibri", FontWeight.BOLD, 12));
        HBox soundFileHb = new HBox();
        soundFileHb.setAlignment(Pos.CENTER);
        soundFileHb.getChildren().add(soundFile);
        */


        // Button
        btn1 = new Button("Choose file");
        btn1.setOnAction(new SaveButtonListener());
        HBox buttonHb1 = new HBox(10);
        buttonHb1.setAlignment(Pos.CENTER);
        buttonHb1.getChildren().addAll(btn1);
        // Button 2
        System.out.println(questions.size());
        btn2 = new Button("Save Question");
        btn2.setOnAction(new SaveQuestionListener());

        HBox buttonHb2 = new HBox(10);
        buttonHb2.setAlignment(Pos.CENTER);
        buttonHb2.getChildren().addAll(btn2);

        Button frontPageButton = new Button("Back to frontpage");
        frontPageButton.setOnAction(e -> new FrontPageScene());
        HBox frontButton1 = new HBox(10);
        frontButton1.setAlignment(Pos.CENTER);
        frontButton1.getChildren().addAll(frontPageButton);


        // Status message text

        // Vbox
        saveFilesLayout = new VBox(30);
        saveFilesLayout.setPadding(new Insets(25, 25, 25, 25));
        saveFilesLayout.getChildren().addAll(labelHb, questionLabel, questionText, correctAnswerLabel, correctAnswer, wrongAnswerLabel, wronganswer1, wronganswer2, wronganswer3, buttonHb1, buttonHb2, frontButton1, actionStatus);

        // Scene
        saveFilesScene = new Scene(saveFilesLayout,400, 700); // w x h
        //saveFilesScene.getStylesheets().add("Theme.css");
        window.setScene(saveFilesScene);


    }
    private class SaveQuestionListener implements  EventHandler<ActionEvent>{

        public void handle(ActionEvent e) {
            if (question.getSoundFile() != null) {
                question.setId(Main.questions.size()+2);
                try {
                    question.writeToFile(question.getId(), question.getTextOfQuestion(), question.getSoundFile(), question.getCorrectAnswer(),
                            question.getDummyAnswers1(), question.getDummyAnswers2(), question.getDummyAnswers3());

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

    private class SaveButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            showSingleFileChooser();
        }
    }
    public static void CopyFile (File source, File dest) throws IOException {
        Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);


    }

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
                } else{
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
