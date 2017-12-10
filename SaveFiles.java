import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.apache.commons.io.FileUtils;
import sun.applet.AppletListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;

public class SaveFiles
        extends Application {

    private Text actionStatus;
    private Stage savedStage;
    private TextArea txtArea;
    private static final String titleTxt = "Save your sounds";
    private TextField question, correctAnswer, wronganswer1, wronganswer2, wronganswer3;
    private Label questionLabel, correctAnswerLabel, wrongAnswerLabel, label, soundFile;
    UploadQuestions uploadQuestions = new UploadQuestions();
    private Button btn1, btn2;
    private int number = 1000;
    public static void main(String [] args) {

        Application.launch(args);
    }

    @Override // interface
    public void start(Stage primaryStage) {


        primaryStage.setTitle(titleTxt);

        questionLabel = new Label("Write question below: ");
        //Username input
        question = new TextField();
        question.setOnKeyPressed((event) -> {
            uploadQuestions.setQuestionFromScene(question.getText());
        });

        correctAnswerLabel = new Label ("Write the correct answer below: ");
        correctAnswer = new TextField();
        correctAnswer.setOnKeyPressed((event) -> {
            uploadQuestions.setCorrectAnswer(correctAnswer.getText());
        });

        wrongAnswerLabel = new Label ("Write the wrong answers below: ");
        wronganswer1 = new TextField();
        wronganswer1.setOnKeyPressed((event) -> {
            uploadQuestions.setWrongAnswer1(wronganswer1.getText());
        });
        wronganswer2 = new TextField();
        wronganswer2.setOnKeyPressed((event) -> {
            uploadQuestions.setWrongAnswer2(wronganswer2.getText());
        });
        wronganswer3 = new TextField();
        wronganswer3.setOnKeyPressed((event) -> {
            uploadQuestions.setWrongAnswer3(wronganswer3.getText());
        });


/*
        // The question for the sound
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
        actionStatus = new Text();
        actionStatus.setFont(Font.font("Calibri", FontWeight.NORMAL, 20));
        actionStatus.setFill(Color.FIREBRICK);

        // Vbox
        VBox vbox = new VBox(30);
        vbox.setPadding(new Insets(25, 25, 25, 25));
        vbox.getChildren().addAll(labelHb, questionLabel, question, correctAnswerLabel, correctAnswer, wrongAnswerLabel, wronganswer1, wronganswer2, wronganswer3, buttonHb1, buttonHb2, frontButton1, actionStatus);

        // Scene
        Scene scene = new Scene(vbox, 400, 700); // w x h
        primaryStage.setScene(scene);
        primaryStage.show();

        savedStage = primaryStage;
    }
    private class SaveQuestionListener implements  EventHandler<ActionEvent>{

        public void handle(ActionEvent e) {
            number--;
            System.out.println("number: " + number);
            try {
                uploadQuestions.writeToFile(number, uploadQuestions.getQuestionFromScene(), uploadQuestions.getSoundFileName(), uploadQuestions.getCorrectAnswer(), uploadQuestions.getWrongAnswer1(), uploadQuestions.getWrongAnswer2(), uploadQuestions.getWrongAnswer3());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            question.clear();
            correctAnswer.clear();
            wronganswer1.clear();
            wronganswer2.clear();
            wronganswer3.clear();
            actionStatus.setText(null);

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
                            uploadQuestions.setSoundFileName(source.getName());
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
