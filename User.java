import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class User  extends Main{
    public String username;
    public String password;
    public int ID;
    public boolean isLoggedIn;



    public User(){

    }
    public User(String username, String password){
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

    public void createHistoryFile(){
        // Section needs to be here so the time is checked every time method is called
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time  = localDateTime.format(formatter);
        //
        File historyFile = new File (user.getUserName() + ".txt");

        if (!historyFile.exists()) {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(user.getUserName() + ".txt"), "utf-8"))) {
                writer.write(time +"\t"+ " User: " +  user.getUserName() +  ". " + "File has been created" + "\n");
                System.out.println("History file has been created");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
    public void writeOnHistoryFile(String writeToFile) throws IOException {
        //Section needs to be here so the time is checked every time method is called
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time  = localDateTime.format(formatter);
        //Copied from signUpScene¨
        BufferedWriter toUser = new BufferedWriter(new FileWriter(user.getUserName() + ".txt", true));
        toUser.write(time + "\t"+ " User: " + user.getUserName() + ". " + writeToFile);
        toUser.newLine();
        toUser.close();
        if (!user.getUserName().equals("admin")){
            BufferedWriter toAdmin = new BufferedWriter(new FileWriter("admin" + ".txt", true));
            toAdmin.write(time + "\t"+ " User: " + user.getUserName() + ". " + writeToFile);
            toAdmin.newLine();
            toAdmin.close();
        }


    }
}
