import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.sun.activation.registries.LogSupport.log;
import static java.lang.System.out;


public class Login {


    public static boolean login(String enteredUsername, String enteredPass) throws IOException {
        FileReader fileReader = new FileReader("/Users/akroghp/IdeaProjects/P1 LoginUser/src/text.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        byte[] bytes = Files.readAllBytes(Paths.get("/Users/akroghp/IdeaProjects/P1 LoginUser/src/text.txt"));
        String s = new String(bytes);

        if (s.indexOf(enteredUsername + " " + enteredPass) == 0) {
            return true;
        } else {
            return false;
        }


    }
}
/**
    public static void main (String[] args ) throws IOException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("User: ");
        String user = scanner.next();
        System.out.println("Pass: ");
        String pass = scanner.next();


        FileReader fileReader = new FileReader("/Users/akroghp/IdeaProjects/P1 LoginUser/src/text.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);


        byte[] bytes = Files.readAllBytes(Paths.get("/Users/akroghp/IdeaProjects/P1 LoginUser/src/text.txt"));
        String s = new String(bytes);




        if (s.indexOf(user + " " + pass) == 0){
            System.out.println("You are logged in");
        } else{
            System.err.println("Username and password doesn't match");
        }



        bufferedReader.close();



    }

}

**/
