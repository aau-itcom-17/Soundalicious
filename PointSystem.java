import javafx.scene.control.Alert;

public class PointSystem
{
    private static int count = 0;

    public static void countPoints()
    {
        count++;
    }

    public static void showPoints()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Finished!\n" + "You have " + count + " correct answers out of " + QuickPlayScene.numOfQuestions + "!");

        alert.showAndWait();
    }

    public static void resetCountPoints()
    {
        count = 0;
    }
}
