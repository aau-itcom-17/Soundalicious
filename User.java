import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class User {
    public String username;
    public String password;
    public int ID;
    public boolean ifLoggedIn;


    public User(String username, String password, int ID, boolean ifLoggedIn){
        this.username = username;
        this.password = password;
        this.ID = ID;
        this.ifLoggedIn = ifLoggedIn;

    }

    public boolean isIfLoggedIn() {
        return ifLoggedIn;
    }

    public void setIfLoggedIn(boolean ifLoggedIn) {
        this.ifLoggedIn = ifLoggedIn;
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

}
