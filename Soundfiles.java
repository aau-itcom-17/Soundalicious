import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Soundfiles {
    private static int count = 0;

    private static AudioInputStream audio;
    private static Clip clip = null;

    // Makes the sound stop.
    public static void noSound() {
        clip.stop();
    }

    // Only this method has been changed to clip. The other methods remain.
    public static void readingSounds() {
        //Play sound
        try {
            String homePath = System.getProperty("user.home");
            //reading files from the path of in the program folder on the computer. Path:
            File soundFolder = new File(homePath + File.separator + "p1" + File.separator + "Sounds" + File.separator + Main.rQuestions.get(0).getSoundfile());
            String soundFolderName = soundFolder.getPath();
            System.out.println(soundFolderName);

            // Playing soundfile from file
            audio = AudioSystem.getAudioInputStream(new File(soundFolderName));
            clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();

        } catch (UnsupportedAudioFileException uae) {
            System.out.println(uae);
        } catch (IOException ioe) {
            System.out.println(ioe);
        } catch (LineUnavailableException lua) {
            System.out.println(lua);
        }


    }
}

