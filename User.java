import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deals with keeping track of which user is logged in now.
 * Has true and false states for login.
 */
public class User extends Main {
    public String username;
    public String password;
    public int ID;
    public boolean isLoggedIn;


    public User() {

    }

    public User(String username, String password) {

    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.isLoggedIn = loggedIn;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassWord() {
        return password;
    }

    public void setPassWord(String passWord) {
        this.password = passWord;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void createHistoryFile() {
        // Section needs to be here so the time is checked every time method is called
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time = localDateTime.format(formatter);
        //
        File historyFile = new File(Constants.userHistoryPath + "/" + user.getUserName() + ".txt");

        if (!historyFile.exists() && !enteredUsername.equals(Constants.nameAdmin)) {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(Constants.userHistoryPath + "/" + user.getUserName() + ".txt"), "utf-8"))) {
                writer.write(time + " User: " + user.getUserName() + ". " +  + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeOnHistoryFile(String writeToFile) throws IOException {
        //Section needs to be here so the time is checked every time method is called
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time = localDateTime.format(formatter);

        if (admin.isLoggedIn() || user.isLoggedIn()) {
            BufferedWriter toUser = new BufferedWriter(new FileWriter(Constants.userHistoryPath + "/" + user.getUserName() + ".txt", true));
            toUser.write(time + ": " + user.getUserName() + ". " + writeToFile);
            toUser.newLine();
            toUser.close();

            /*
             * If admin is not logged in actions are still written to admin file
             * If admin is logged in, the activity is written as a normal user above, to not repeat same lines
             */
            if (!admin.isLoggedIn()) {
                BufferedWriter toAdmin = new BufferedWriter(new FileWriter(Constants.userHistoryPath + "/" + Constants.nameAdmin + ".txt", true));
                toAdmin.write(time + ": " + user.getUserName() + ". " + writeToFile);
                toAdmin.newLine();
                toAdmin.close();
            }
        }
    }

}
