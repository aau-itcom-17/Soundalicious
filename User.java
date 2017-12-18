/**
 * Deals with keeping track of which user is logged in now.
 * Has true and false states for login.
 */

public class User {
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

}
