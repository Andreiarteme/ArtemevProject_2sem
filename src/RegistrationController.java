import javax.security.auth.login.LoginException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class RegistrationController {

    private LoginModel loginModel;

    public RegistrationController(LoginModel loginModel) {
        this.loginModel = loginModel;
    }
    public void authorization(){
      //  start();
        LoginController loginController = new LoginController(loginModel);
        LoginView loginView = new LoginView(loginController);


    }

}
