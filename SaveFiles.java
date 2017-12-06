import javafx.application.Application;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
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

    public static void main(String [] args) {

        Application.launch(args);
    }

    @Override // interface
    public void start(Stage primaryStage) {

        primaryStage.setTitle(titleTxt);

        // Window label
        Label label = new Label("Save File Chooser");
        label.setTextFill(Color.DARKBLUE);
        label.setFont(Font.font("Calibri", FontWeight.BOLD, 36));
        HBox labelHb = new HBox();
        labelHb.setAlignment(Pos.CENTER);
        labelHb.getChildren().add(label);

        // Button
        Button btn1 = new Button("Choose and save");
        btn1.setOnAction(new SaveButtonListener());
        HBox buttonHb1 = new HBox(10);
        buttonHb1.setAlignment(Pos.CENTER);
        buttonHb1.getChildren().addAll(btn1);

        // Status message text
        actionStatus = new Text();
        actionStatus.setFont(Font.font("Calibri", FontWeight.NORMAL, 20));
        actionStatus.setFill(Color.FIREBRICK);

        // Vbox
        VBox vbox = new VBox(30);
        vbox.setPadding(new Insets(25, 25, 25, 25));
        vbox.getChildren().addAll(labelHb, buttonHb1, actionStatus);

        // Scene
        Scene scene = new Scene(vbox, 600, 400); // w x h
        primaryStage.setScene(scene);
        primaryStage.show();

        savedStage = primaryStage;
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

        long destSize = FileUtils.sizeOf(dest);
        long sourceFile = FileUtils.sizeOf(source);
        System.out.println(destSize);
        System.out.println(sourceFile);


        if (sF.equals("au") || sF.equals("wav")) {
            if (destSize != sourceFile) {
                if (!dest.equals(source)) {
                    fileChooser.setTitle(selectedFile.getName());
                    fileChooser.setInitialDirectory(dest);
                    fileChooser.setInitialFileName(fileChooser.getTitle());

                    if (selectedFile != null) {
                        try {
                            CopyFile(source, dest);
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


