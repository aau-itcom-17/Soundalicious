import java.util.Scanner;
import java.io.*;
import sun.audio.*;

public class Quiz
{
    public static void main(String []args) throws IOException {
        int c = 0;
        Scanner scan = new Scanner ( System.in );

        System.out.println("Who is this?\n");
// Hello my name is Anton 
// hej Anton How are you

/**
 * A simple Java sound file example (i.e., Java code to play a sound file).
 * AudioStream and AudioPlayer code comes from a javaworld.com example.
 * @author alvin alexander, devdaily.com.
 */

                // open the sound file as a Java input stream
                String gongFile = "/Users/lasseskjotthansen/Documents/Quiz/K.au";
                InputStream in = new FileInputStream(gongFile);

                // create an audiostream from the inputstream
                AudioStream audioStream = new AudioStream(in);

                // play the audio clip with the audioplayer class
                AudioPlayer.player.start(audioStream);



        System.out.println("Kanye");
        System.out.println("Beyonce");
        System.out.println("Anton");
        System.out.println("Jay-Z\n");

        String i;
        i = scan.nextLine();


        if(i.equals("Kanye"))
        {
            System.out.println("Correct\n");
            c++;
        }

        else
        {
            System.out.println("Wrong\n");
        }

        System.out.println("What Language Is This Coded In?\n");

        System.out.println("C");
        System.out.println("C++");
        System.out.println("Java");
        System.out.println("PC\n");

        i=scan.nextLine();

        if(i.equals("Java"))
        {
            System.out.println("Correct\n");
            c++;
        }

        else
        {
            System.out.println("Wrong");
        }

        System.out.println(c + " Correct out of 2");

        System.out.println(100 * c/2 + "%");

    }
}
