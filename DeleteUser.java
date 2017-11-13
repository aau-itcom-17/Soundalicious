import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class DeleteUser {


    private void removeLineFromFile(String file, String lineToRemove) {
        try {
            File inFile = new File(file);

            //Construct the new file that will later be renamed to the original filename.

            // ".tmp makes sure we don't delete the entire .txt file"
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
            BufferedReader br = new BufferedReader(new FileReader(file));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
            String line;


            //Read from the original file and write to the new
            //unless content matches data to be removed.
            while ((line = br.readLine()) != null) {
                if (!line.trim().equals(lineToRemove)) {
                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();

            //Delete the original file
            if (!inFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }
            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(inFile))
                System.out.println("Could not rename file");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        DeleteUser util = new DeleteUser();
        Scanner scanner = new Scanner(System.in);

        System.out.println("If you want to delete your account write account name");
        String deleteUser = scanner.next();
        // username is deleted in lowercase
        deleteUser = deleteUser.toLowerCase();
        System.out.println(deleteUser);
        System.out.println("Are you sure?");
        System.out.println("Write password");
        String deletePass = scanner.next();


        byte[] bytes = Files.readAllBytes(Paths.get("/Users/akroghp/IdeaProjects/P1 LoginUser/src/text.txt"));
        String s = new String(bytes);


        String userAndPass = deleteUser + " " + deletePass;


        if (s.indexOf(userAndPass) != 0) {
            util.removeLineFromFile("/Users/akroghp/IdeaProjects/P1 LoginUser/src/text.txt", deleteUser + " " + deletePass);
            System.out.println("Your user has been deleted.");
        } else {
            System.out.println("Password was not identical with username");



        }

    }
}




