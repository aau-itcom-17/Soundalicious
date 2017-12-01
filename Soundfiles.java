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
            File soundFolder = new File(homePath + File.separator + "p1" + File.separator + "Sounds" + File.separator + Main.rQuestions.get(Main.n).getSoundfile());
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
}




