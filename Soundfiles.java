import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Soundfiles
{
    public static void kanyeSound () throws IOException {
        String gongFile = "K.au";

        InputStream in = new FileInputStream(gongFile);

        // create an audiostream from the inputstream
        AudioStream audioStream = new AudioStream(in);

        // play the audio clip with the audioplayer class
        AudioPlayer.player.start(audioStream);
    }
}
