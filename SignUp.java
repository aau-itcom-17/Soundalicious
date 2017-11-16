import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.*;
import java.io.FileWriter;


public class SignUp {
        
      //check if user already exists. True - if exists, false - does not.
      public static boolean userExists(String enteredUsername) throws IOException {
          BufferedReader bufferedReader = new BufferedReader(new FileReader("text.txt"));
          String existingUser;

          while ((existingUser = bufferedReader.readLine()) != null)
          {
                existingUser = existingUser.substring(0, existingUser.indexOf(" "));
                System.out.println(existingUser);
                if(existingUser.equals(enteredUsername)){
                  return true;
                }
          }
          return false;
      }

    public static void writeToFile(String username, String password) throws IOException {
        // append "true" saves the input to the text.txt file. Also when the application opens again.
        // We need to make a way for a user to delete itself.
        BufferedWriter out = new BufferedWriter(new FileWriter("text.txt", true));
        out.write(username + " " + password);
        out.newLine();
        out.close();

    }
/**
    public static void main (String[] args) throws IOException {



        Scanner scanner = new Scanner(System.in);

        String username = "";
        String password = "";

        /* System.out.println("Please enter your username: ");
        username = scanner.next();
        // saving username to lowercase
        username = username.toLowerCase();
        System.out.println("Please enter your password: ");
        password = scanner.next();

        System.out.println("Thanks for adding to the directory");

        LoginTest.writeToFile(username, password);
        */
}

