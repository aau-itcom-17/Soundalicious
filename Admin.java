public class Admin extends User {

    public Admin(String username, String password, int ID, boolean ifLoggedIn){
        super(username, password, ID, ifLoggedIn);
        this.username = username;
        this.password = password;
        this.ID = ID;
        this.ifLoggedIn = ifLoggedIn;

    }
    public void deleteUser(String username, String password, int ID){

    }

    public void deleteUserQuestion(){

    }

}
