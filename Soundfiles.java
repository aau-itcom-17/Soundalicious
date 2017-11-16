import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Soundfiles
{
    private static int count = 0;



    public static void kanyeSound()
    {
        //Play sound
        try
        {
            String gongFile = "K.au";

            InputStream in = new FileInputStream(gongFile);

            // create an audiostream from the inputstream
            AudioStream audioStream = new AudioStream(in);

            // play the audio clip with the audioplayer class
            AudioPlayer.player.start(audioStream);

        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }

    public static void darudeSound()
    {
        //Play sound
        try
        {
            String gongFile = "K.au";

            InputStream in = new FileInputStream(gongFile);

            // create an audiostream from the inputstream
            AudioStream audioStream = new AudioStream(in);

            // play the audio clip with the audioplayer class
            AudioPlayer.player.start(audioStream);

        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }

    public static void bMarsSound()
    {
        //Play sound
        try
        {
            String gongFile = "K.au";

            InputStream in = new FileInputStream(gongFile);

            // create an audiostream from the inputstream
            AudioStream audioStream = new AudioStream(in);

            // play the audio clip with the audioplayer class
            AudioPlayer.player.start(audioStream);

        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }

    public static void countPoints()
    {
        count++;

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Correct answer!\n" + "You have " + count + " correct answers");

        alert.showAndWait();
    }

    public static String getChoice(ChoiceBox<String> choiceBox2)
    {
        String questions = choiceBox2.getValue();
        //System.out.println(questions);
        return questions;
    }

}




