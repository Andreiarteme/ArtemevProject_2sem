import javax.security.auth.login.LoginException;
import javax.swing.*;
import java.awt.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


public class RegistrationController {

    private DataBase dataBase;
//    private LoginModel loginModel;

    public RegistrationController(/*LoginModel loginModel*/ DataBase dataBase) {
//        this.loginModel = loginModel;
        this.dataBase = dataBase;
    }
    public void authorization(){
      //  start();
        LoginController loginController = new LoginController(/*loginModel*/dataBase);
        LoginView loginView = new LoginView(loginController);
    }

    public boolean registration(String regLogin, String regPassword, JLabel info, String regGroup) throws LoginException, NoSuchAlgorithmException, SQLException, ClassNotFoundException {
        if (!regLogin.equals("") && !regPassword.equals("")){
            return regUser(regLogin,toHash(regPassword),info, regGroup);

        }else{
            info.setText("Заполните поля!");
            return false;
        }


    }

    private boolean regUser(String regLogin, String regHashPassword,JLabel info, String regGroup) throws SQLException, ClassNotFoundException {


        DataBase dataBase = new DataBase();
        if (dataBase.getUsers().userExists(regLogin)){
            User user = new User(regLogin,regHashPassword, "2", regGroup);//по умолчанию роль user
            dataBase.registrUser(user);
//            info.setText("Пользователь успешно зарегестрирован!");
//            goYoWiew(user);
            return true;
        }else{
            info.setText("Данный логин уже существует!");
            return false;
        }

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

    public void goYoWiew(String login) throws Exception {
        dataBase.getUser(login);

        //user




    }


}
