import javax.security.auth.login.LoginException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginController {

    private LoginModel loginModel;

    public LoginController(LoginModel loginModel) {
        this.loginModel = loginModel;
    }

    public void methot(){
        System.out.println("\n"+"Controller");
        loginModel.modelMethod();
    }
    public String toHash (String str) throws LoginException, NoSuchAlgorithmException {
        MessageDigest shall = MessageDigest.getInstance("SHA-1");
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] bytes = md5.digest(str.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes)
            builder.append(String.format("%02X ", b));
        String hash = builder.toString().replaceAll(" ","").toLowerCase();
        return hash;
    }
    public void registration(){
        RegistrationController  registrationController = new RegistrationController(loginModel);
        RegistrationView registrationView = new RegistrationView(registrationController);


    }

    public Boolean isEmpty(String loginText, String passwordText) {
        Boolean result = false;
        if (!loginText.trim().equals("") && !passwordText.trim().equals("")){
            result = true;
        }
            return result;

    }

    public Boolean checkLoginAndPassword(String loginText, String passwordText) throws LoginException, NoSuchAlgorithmException {
        return loginModel.checkLoginAndPassword(loginText,toHash(passwordText));
    }

    public void goToView() {


    }
}
