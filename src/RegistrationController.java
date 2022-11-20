import javax.security.auth.login.LoginException;
import javax.swing.*;
import java.awt.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


public class RegistrationController {

    private DataBase dataBase;

    public RegistrationController( DataBase dataBase) {
        this.dataBase = dataBase;
    }
    public void authorization(){
        LoginController loginController = new LoginController(/*loginModel*/dataBase);
        LoginView loginView = new LoginView(loginController);
    }

    public boolean registration(String regLogin, String regPassword, JLabel info) throws Exception {
        if (!regLogin.equals("") && !regPassword.equals("")){
            if (dataBase.getUsers().userExists(regLogin)){
                return true;
            }else{
                info.setText("Данный логин уже существует!");
                return false;
            }
        }else{
            info.setText("Заполните поля!");
            return false;
        }


    }

    public boolean regUser(String regLogin, String regHashPassword, String regGroup) throws Exception {
            String newUserId = dataBase.getNewUserId(regLogin);
            User user = new User(newUserId, regLogin,toHash(regHashPassword), "2", regGroup);//по умолчанию роль user
            dataBase.registrUser(user);
            goToWiew(regLogin);
            return true;
    }

        public String toHash (String str) throws LoginException, NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] bytes = md5.digest(str.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes)
            builder.append(String.format("%02X ", b));
        String hash = builder.toString().replaceAll(" ","").toLowerCase();
        return hash;
    }

    public void goToWiew(String login) throws Exception {
        //user
        User user = dataBase.getUser(login);
        UserController userController = new UserController(dataBase, user);
        UserView userView = new UserView(userController);



    }


}
