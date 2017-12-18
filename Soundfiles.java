import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Sound files class deals with sound playback that is used in the play game scene.
 */

public class Soundfiles
{
    private static int count = 0;
    
    private static AudioInputStream audio;
    private static Clip clip = null;

    /**
     * This method is activated when user has not played sound to indicate warning.
     */
    public static void noSound() throws NullPointerException {
        try {

            clip.stop();

        }
        catch (Exception e){
            System.out.println("No sound were played before pressing next");
        }
    }

    /**
     * Gets the right sound for the question and displays errors.
     */
    public static void readingSound() {
        //Play sound
        try {
             String homePath = System.getProperty("user.home");
            File soundFolder = new File(homePath + File.separator + "p1" + File.separator + "Sounds" + File.separator + Main.rQuestions.get(Main.counterOfQuestions).getSoundFile());
            String soundFolderName = soundFolder.getPath();
            System.out.println(soundFolderName);
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




