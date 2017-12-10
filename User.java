import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class User {
    private String username;
    private String password;
    private int ID;
    private boolean ifLoggedIn;


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
