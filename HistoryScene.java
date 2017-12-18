import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HistoryScene extends FrontPageScene{

    Label historyLabel;
    VBox layoutHistory;
    Button frontPageButton;
    TextArea text;

    public HistoryScene(){
        historyLabel = new Label("Game History");
        historyLabel.getStyleClass().add("label-headline");

        text = new TextArea();
        //text.setPrefColumnCount(30);
        text.setPrefRowCount(25);
        String allLines = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(user.getUserName() + ".txt")))) {
            String line;
            while ((line = reader.readLine()) != null)
                allLines = allLines + line + "\n";




        } catch (IOException e) {
            e.printStackTrace();
        }
        text.appendText(allLines);

        text.setEditable(false);

        ScrollPane scrollPane = new ScrollPane();

        frontPageButton = new Button();
        frontPageButton.setText("Go back");
        frontPageButton.getStyleClass().add("button-menu");
        frontPageButton.setOnAction(e -> new FrontPageScene());

        layoutHistory = new VBox(20);
        layoutHistory.setAlignment(Pos.CENTER);
        historyScene = new Scene(layoutHistory, 400, 700);
        layoutHistory.getChildren().addAll(historyLabel , text, frontPageButton);
        historyScene.getStylesheets().add("Theme.css");
        window.setScene(historyScene);





    }
}
