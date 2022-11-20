import javax.security.auth.login.LoginException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws LoginException, NoSuchAlgorithmException {
        DataBase dataBase = new DataBase();
        LoginController  loginController = new LoginController(dataBase);
        LoginView loginView = new LoginView(loginController);
    }

}