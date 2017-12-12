import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.io.*;
public class DeleteUserScene {
    Label labelFront;
    Button deleteButton;
    VBox layoutDeleteUserScene ;
    TableView table;

    public DeleteUserScene() {
        table = new TableView();
        table.setEditable(true);
        TableColumn usernameCol = new TableColumn("Username");
        TableColumn passwordCol = new TableColumn("Password");
        table.getColumns().addAll(usernameCol, passwordCol);
    }


}
