import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Soundfiles
{
    private static int count = 0;
    
    private static AudioInputStream audio;
    private static Clip clip = null;
// Makes the sound stop. 
    public static void noSound() {
        clip.stop();
    }

// Only this method has been changed to clip. The other methods remain.
    public static void kanyeSound() {
        //Play sound
        try {
             String homePath = System.getProperty("user.home");
            File soundFolder = new File(homePath + File.separator + "p1" + File.separator + "Sounds" + File.separator + Main.rQuestions.get(0).getSoundfile());
            String soundFolderName = soundFolder.getPath();
            audio = AudioSystem.getAudioInputStream(new File(soundFolderName));
            clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
            
        }
        
        
        catch(UnsupportedAudioFileException uae) {
            System.out.println(uae);
        }
        catch(IOException ioe) {
            System.out.println(ioe);
        }
        catch(LineUnavailableException lua) {
            System.out.println(lua);
        }


    }

    
/*    public static void kanyeSound()
    {

   

    private static AudioInputStream audio;
    private static Clip clip = null;
// Makes the sound stop.
    public static void noSound() {
        clip.stop();
    }

// Only this method has been changed to clip. The other methods remain.
    public static void kanyeSound() {
        //Play sound
        try {
            audio = AudioSystem.getAudioInputStream(new File("/Users/Nius/IdeaProjects/P1/src/K.au"));
            clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
            
        }
        
        
        catch(UnsupportedAudioFileException uae) {
            System.out.println(uae);
        }
        catch(IOException ioe) {
            System.out.println(ioe);
        }
        catch(LineUnavailableException lua) {
            System.out.println(lua);
        }


    }

        
        
/*       
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
*/

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

    public static void resetCountPoints()
    {
        count = 0;
    }

    public static String getChoice(ChoiceBox<String> choiceBox2)
    {
        String questions = choiceBox2.getValue();
        return questions;
    }

}




