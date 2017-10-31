import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.io.FileWriter;


public class LoginTest {

    public static void writeToFile(ArrayList<String> username, ArrayList<String> password) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("/Users/akroghp/IdeaProjects/P1 LoginUser/src/text.txt"));
        out.write(username + "#" + password);
        out.newLine();
        out.close();
    }


        public static void main(String[] args) throws IOException {

            ArrayList <String> username = new ArrayList<String>();
            ArrayList<String> password = new ArrayList<String>();

            Scanner scanner = new Scanner(System.in);
            String answer = "";

            File file = new File ("/Users/akroghp/IdeaProjects/P1 LoginUser/src/text.txt");
            Scanner s = new Scanner (file);

            while (s.hasNext()){
                username.add(s.next());
                password.add(s.next());
            }
            s.close();

            {
                do {
                    System.out.println("Please enter your username: ");
                    username.add(scanner.next());
                    System.out.println("Please enter your password: ");
                    password.add(scanner.next());
                    System.out.println("Create new user yes/no?");
                    answer = scanner.next();
                } while (answer.equals("yes"));

                if (answer.equals("yes")) {  //want it to go back to start another directory here
                }
                else {

                    System.out.println("Thanks for adding to the directory");
                    for (int i = 0; i < username.size(); i++) {
                        System.out.print(username.get(i) + "\t");
                        System.out.print(password.get(i));
                        System.out.println("");
                        LoginTest.writeToFile(username, password);

                    }
                }

            }
        }

    }

